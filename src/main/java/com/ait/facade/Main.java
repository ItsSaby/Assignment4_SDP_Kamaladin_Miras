package com.ait.facade;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java com.ait.facade.Main <input-video-file> <target-format>");
            System.out.println("Example: java com.ait.facade.Main sample.mp4 mkv");
            return;
        }

        VideoConversionFacade converter = new VideoConversionFacade();

        try {
            ConversionResult result = converter.convert(args[0], args[1]);
            System.out.println("Conversion workflow completed.");
            System.out.println("Format: " + result.getSourceFormat() + " -> " + result.getTargetFormat());
            System.out.println("Codec: " + result.getSourceCodec() + " -> " + result.getTargetCodec());
            System.out.println("Estimated bitrate: " + result.getSourceBitrate()
                    + " -> " + result.getTargetBitrate() + " kbps");
            System.out.println("Audio: " + result.getAudioSummary());
            System.out.println("Manifest saved to: " + result.getManifestPath());
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.err.println("Could not complete conversion workflow: " + exception.getMessage());
        }
    }
}
