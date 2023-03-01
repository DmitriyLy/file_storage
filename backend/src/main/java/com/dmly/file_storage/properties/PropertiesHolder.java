package com.dmly.file_storage.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertiesHolder {

    @Value("${file.storage.directory}")
    private String storageDirectory;

}
