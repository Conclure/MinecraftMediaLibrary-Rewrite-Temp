package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ExternalUrl {

  @NotNull
  Map<String, String> getExternalUrls();
}
