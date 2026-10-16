package com.ait.facade;

public class BitrateReader {
    public long calculateSourceBitrate(VideoFile file) {
        // Educational estimate: file size is used to model a bitrate analysis stage.
        return Math.max(128, file.getSizeInBytes() / 1024);
    }

    public long calculateTargetBitrate(long sourceBitrate, String targetFormat) {
        return switch (targetFormat) {
            case "mp4" -> Math.max(128, Math.round(sourceBitrate * 0.80));
            case "avi" -> Math.max(128, Math.round(sourceBitrate * 1.10));
            case "mov" -> Math.max(128, Math.round(sourceBitrate * 0.90));
            case "mkv" -> Math.max(128, Math.round(sourceBitrate * 0.85));
            default -> throw new IllegalArgumentException("Cannot calculate bitrate for: " + targetFormat);
        };
    }
}
