package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public enum VideoQuality {
  UNKNOWN(0),
  NOVIDEO(0),
  TINY(1),
  SMALL(2), // 240p
  MEDIUM(3), // 360p
  LARGE(4), // 480p
  HD720(5),
  HD1080(6),
  HD1440(7),
  HD2160(8),
  HD2880P(9),
  HIGHRES(10); // 3072p

  private final int order;

  VideoQuality(final int order) {
    this.order = order;
  }

  public static VideoQuality ofKey(@NotNull final String key) {
    return Arrays.stream(VideoQuality.values())
        .filter(quality -> quality.name().equalsIgnoreCase(key))
        .findAny()
        .orElseThrow(AssertionError::new);
  }

  public int getOrder() {
    return this.order;
  }
}
