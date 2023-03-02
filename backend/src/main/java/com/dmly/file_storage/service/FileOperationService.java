package com.dmly.file_storage.service;

import com.dmly.file_storage.model.FileContainer;
import com.dmly.file_storage.model.FileDetails;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileOperationService {

    List<FileDetails> listFiles();

    Path saveFile(MultipartFile receivedFile);

    FileContainer getFileResource(String fileName);

}
