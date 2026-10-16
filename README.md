# Assignment #4 — Facade Pattern

**Course:** ShP-2216 – Software Design Patterns (OP 6B06102)  
**Institution:** Astana IT University — School of Computer Engineering  
**Language:** Java 17

## Overview
This project demonstrates the Facade structural pattern through a video-conversion workflow. `VideoConversionFacade` exposes one simple method, `convert(filename, targetFormat)`, and coordinates `CodecFactory`, `BitrateReader`, and `AudioMixer`.

**Important scope note:** This is an educational model of the conversion pipeline. It analyzes the input file's metadata, models codec/bitrate/audio stages, and writes a conversion manifest. It does not encode or transform real video bytes, because no media-encoding library or FFmpeg dependency is included.

## Structure
- `VideoConversionFacade` — simplified public API and coordinator.
- `CodecFactory` — validates input/target formats and selects codec profiles.
- `BitrateReader` — estimates source bitrate and models target bitrate.
- `AudioMixer` — models audio-track preparation.
- `VideoFile`, `Codec`, `ConversionResult` — data objects.
- `Main` — client; it uses only the Facade.

## Run
Use JDK 17.

From the project root:
```bash
javac -d out src/main/java/com/ait/facade/*.java
java -cp out com.ait.facade.Main sample.mp4 mkv
```
Provide an existing file with one of these extensions: `mp4`, `avi`, `mov`, `mkv`. The program writes `<filename>_conversion_manifest.txt` next to the source file.

## UML
Open `UMLet_Facade_Diagram.uxf` in UMLet or UMLetino.

## Repository note
Create a public GitHub repository and replace the repository-link placeholder in the report with your own URL before submitting to Moodle.
