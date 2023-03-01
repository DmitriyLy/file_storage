package com.dmly.file_storage.controller;

import com.dmly.file_storage.model.FileDetails;
import com.dmly.file_storage.service.FileOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
