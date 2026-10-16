package com.ait.facade;

import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;

public class CodecFactory {
    private static final Set<String> SUPPORTED_FORMATS = Set.of("mp4", "avi", "mov", "mkv");

    public VideoFile readVideo(String filename) {
        Path path = Path.of(filename).toAbsolutePath().normalize();

        if (!java.nio.file.Files.exists(path) || !java.nio.file.Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Input file does not exist: " + filename);
        }

        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0 || dotIndex == fileName.length() - 1) {
            throw new IllegalArgumentException("Input file must have a video extension.");
        }

        String format = fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT);
        if (!SUPPORTED_FORMATS.contains(format)) {
            throw new IllegalArgumentException("Unsupported input format: " + format);
        }

        try {
            return new VideoFile(path, format, java.nio.file.Files.size(path));
        } catch (java.io.IOException exception) {
            throw new IllegalStateException("Could not read input file metadata.", exception);
        }
    }

    public Codec extractCodec(VideoFile file) {
        String codecName = switch (file.getFormat()) {
            case "mp4" -> "H.264";
            case "avi" -> "DivX";
            case "mov" -> "QuickTime";
            case "mkv" -> "Matroska";
            default -> throw new IllegalArgumentException("No codec available for " + file.getFormat());
        };
        return new Codec(codecName, file.getFormat());
    }

    public Codec createTargetCodec(String targetFormat) {
        String format = normalizeFormat(targetFormat);
        String codecName = switch (format) {
            case "mp4" -> "H.264";
            case "avi" -> "DivX";
            case "mov" -> "QuickTime";
            case "mkv" -> "Matroska";
            default -> throw new IllegalArgumentException("Unsupported target format: " + targetFormat);
        };
        return new Codec(codecName, format);
    }

    private String normalizeFormat(String format) {
        if (format == null || format.isBlank()) {
            throw new IllegalArgumentException("Target format cannot be empty.");
        }
        String normalized = format.trim().toLowerCase(Locale.ROOT);
        if (normalized.startsWith(".")) {
            normalized = normalized.substring(1);
        }
        if (!SUPPORTED_FORMATS.contains(normalized)) {
            throw new IllegalArgumentException("Unsupported target format: " + format);
        }
        return normalized;
    }
}
