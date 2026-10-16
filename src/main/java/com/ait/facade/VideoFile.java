package com.ait.facade;

import java.nio.file.Path;

public class VideoFile {
    private final Path path;
    private final String format;
    private final long sizeInBytes;

    public VideoFile(Path path, String format, long sizeInBytes) {
        this.path = path;
        this.format = format;
        this.sizeInBytes = sizeInBytes;
    }

    public Path getPath() {
        return path;
    }

    public String getFormat() {
        return format;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }
}
