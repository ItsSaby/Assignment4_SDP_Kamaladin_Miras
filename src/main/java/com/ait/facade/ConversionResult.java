package com.ait.facade;

import java.nio.file.Path;

public class ConversionResult {
    private final Path manifestPath;
    private final String sourceFormat;
    private final String targetFormat;
    private final String sourceCodec;
    private final String targetCodec;
    private final long sourceBitrate;
    private final long targetBitrate;
    private final String audioSummary;

    public ConversionResult(Path manifestPath, String sourceFormat, String targetFormat,
                            String sourceCodec, String targetCodec, long sourceBitrate,
                            long targetBitrate, String audioSummary) {
        this.manifestPath = manifestPath;
        this.sourceFormat = sourceFormat;
        this.targetFormat = targetFormat;
        this.sourceCodec = sourceCodec;
        this.targetCodec = targetCodec;
        this.sourceBitrate = sourceBitrate;
        this.targetBitrate = targetBitrate;
        this.audioSummary = audioSummary;
    }

    public Path getManifestPath() { return manifestPath; }
    public String getSourceFormat() { return sourceFormat; }
    public String getTargetFormat() { return targetFormat; }
    public String getSourceCodec() { return sourceCodec; }
    public String getTargetCodec() { return targetCodec; }
    public long getSourceBitrate() { return sourceBitrate; }
    public long getTargetBitrate() { return targetBitrate; }
    public String getAudioSummary() { return audioSummary; }
}
