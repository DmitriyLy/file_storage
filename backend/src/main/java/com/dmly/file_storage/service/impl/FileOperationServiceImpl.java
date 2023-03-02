package com.dmly.file_storage.service.impl;

import com.dmly.file_storage.model.FileDetails;
import com.dmly.file_storage.properties.PropertiesHolder;
import com.dmly.file_storage.service.FileOperationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileOperationServiceImpl implements FileOperationService {

    private final PropertiesHolder propertiesHolder;
    private final Path storageDirectory;

    public FileOperationServiceImpl(PropertiesHolder propertiesHolder) {
        this.propertiesHolder = propertiesHolder;
        storageDirectory = Path.of(System.getProperty("user.dir"), propertiesHolder.getStorageDirectory());

        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectory(storageDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<FileDetails> listFiles() {
        try {
            return Files.walk(storageDirectory, FileVisitOption.FOLLOW_LINKS)
                    .filter(path ->  !Files.isDirectory(path))
                    .map(this::convertFileDetails)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path saveFile(MultipartFile receivedFile) {

        Path filePath = Path.of(storageDirectory.toAbsolutePath().toString(), receivedFile.getOriginalFilename());

        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }

            Files.write(filePath, receivedFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filePath;
    }

    private FileDetails convertFileDetails(Path path) {
        try {
            return new FileDetails(path.getFileName().toString(), Files.size(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
