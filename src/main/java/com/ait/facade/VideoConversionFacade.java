package com.ait.facade;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VideoConversionFacade {
    private final CodecFactory codecFactory;
    private final BitrateReader bitrateReader;
    private final AudioMixer audioMixer;

    public VideoConversionFacade() {
        this.codecFactory = new CodecFactory();
        this.bitrateReader = new BitrateReader();
        this.audioMixer = new AudioMixer();
    }

    public ConversionResult convert(String filename, String targetFormat) {
        // The Facade coordinates the subsystem in one predictable sequence.
        VideoFile source = codecFactory.readVideo(filename);
        Codec sourceCodec = codecFactory.extractCodec(source);
        Codec targetCodec = codecFactory.createTargetCodec(targetFormat);

        long sourceBitrate = bitrateReader.calculateSourceBitrate(source);
        long targetBitrate = bitrateReader.calculateTargetBitrate(sourceBitrate, targetCodec.getFormat());
        String audioSummary = audioMixer.prepareAudioTrack(source, targetCodec);

        Path manifest = createManifestPath(source.getPath(), targetCodec.getFormat());
        writeManifest(manifest, source, sourceCodec, targetCodec, sourceBitrate, targetBitrate, audioSummary);

        return new ConversionResult(manifest, source.getFormat(), targetCodec.getFormat(),
                sourceCodec.getName(), targetCodec.getName(), sourceBitrate, targetBitrate, audioSummary);
    }

    private Path createManifestPath(Path sourcePath, String targetFormat) {
        String fileName = sourcePath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String baseName = dotIndex > 0 ? fileName.substring(0, dotIndex) : fileName;
        return sourcePath.resolveSibling(baseName + "_conversion_manifest.txt");
    }

    private void writeManifest(Path manifest, VideoFile source, Codec sourceCodec, Codec targetCodec,
                               long sourceBitrate, long targetBitrate, String audioSummary) {
        String content = """
                VIDEO CONVERSION WORKFLOW SUMMARY
                Source file: %s
                Source format: %s
                Source codec profile: %s
                Target format: %s
                Target codec profile: %s
                Estimated source bitrate: %d kbps
                Estimated target bitrate: %d kbps
                Audio stage: %s

                Note: This educational project demonstrates the Facade workflow.
                It creates a manifest and models conversion stages; it does not encode video bytes.
                """.formatted(source.getPath(), source.getFormat(), sourceCodec.getName(),
                targetCodec.getFormat(), targetCodec.getName(), sourceBitrate, targetBitrate, audioSummary);

        try {
            Files.writeString(manifest, content);
        } catch (IOException exception) {
            throw new IllegalStateException("Conversion workflow failed while writing the manifest.", exception);
        }
    }
}
