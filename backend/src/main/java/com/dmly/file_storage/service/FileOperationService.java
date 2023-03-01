package com.dmly.file_storage.service;

import com.dmly.file_storage.model.FileDetails;

import java.util.List;

public interface FileOperationService {

    List<FileDetails> listFiles();

}
