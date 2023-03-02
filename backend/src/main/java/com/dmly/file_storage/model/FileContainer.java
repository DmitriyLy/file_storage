package com.dmly.file_storage.model;

import org.springframework.core.io.Resource;

public record FileContainer(Resource resource, String contentType) {
}
