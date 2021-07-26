package io.github.pulsebeat02.minecraftmedialibrary.ffmpeg;

public interface AudioTrimmer extends FFmpegArgumentPreparation, IOProvider {

  long getStartTime();
}
