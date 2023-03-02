package com.dmly.file_storage.controller;

import com.dmly.file_storage.model.FileDetails;
import com.dmly.file_storage.service.FileOperationService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "files")
@RequiredArgsConstructor
public class FileOperationsController {

    private final FileOperationService service;

    @GetMapping(path = "list-files")
    public ResponseEntity<List<FileDetails>> listFiles() {
        return ResponseEntity.ok().body(service.listFiles());
    }

    @PostMapping(path = "upload-files")
    public ResponseEntity<List<FileDetails>> uploadFiles(@RequestParam(name = "files") List<MultipartFile> files, ServletRequest request) {
        files.forEach(service::saveFile);
        return ResponseEntity.ok().body(service.listFiles());
    }

}
