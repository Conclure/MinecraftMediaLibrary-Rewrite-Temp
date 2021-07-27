package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public enum AudioQuality {
  UNKNOWN(0),
  NOAUDIO(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final Integer order;

  AudioQuality(final int order) {
    this.order = order;
  }

  public static AudioQuality ofKey(@NotNull final String key) {
    return Arrays.stream(AudioQuality.values())
        .filter(quality -> quality.name().equalsIgnoreCase(key))
        .findAny()
        .orElseThrow(AssertionError::new);
  }

  public Integer getOrder() {
    return this.order;
  }
}
