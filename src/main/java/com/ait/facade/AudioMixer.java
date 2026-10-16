package com.ait.facade;

public class AudioMixer {
    public String prepareAudioTrack(VideoFile source, Codec targetCodec) {
        if (source.getFormat().equals(targetCodec.getFormat())) {
            return "Audio track checked; no format change was requested.";
        }
        return "Audio track prepared for " + targetCodec.getFormat().toUpperCase()
                + " output using the target container profile.";
    }
}
