package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import org.jetbrains.annotations.NotNull;

public interface AudioFormat {

  int getBitrate();

  int getSamplingRate();

  @NotNull
  AudioQuality getAudioQuality();
}
