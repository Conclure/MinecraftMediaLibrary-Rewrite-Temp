package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public enum Subscription {
  BASIC_DESKTOP("basic-desktop"),
  DAYPASS("daypass"),
  FREE("free"),
  OPEN("open"),
  PREMIUM("premium");

  private static final Map<String, Subscription> map = new HashMap<>();

  static {
    for (final Subscription productType : Subscription.values()) {
      map.put(productType.type, productType);
    }
  }

  private final String type;

  Subscription(@NotNull final String type) {
    this.type = type;
  }

  public static Subscription keyOf(@NotNull final String type) {
    return map.get(type);
  }

  public String getType() {
    return this.type;
  }
}
