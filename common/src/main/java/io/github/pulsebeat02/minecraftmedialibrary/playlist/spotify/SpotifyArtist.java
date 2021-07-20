package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface SpotifyArtist {

  @NotNull
  Map<String, String> getExternalUrls();

  @NotNull
  String getId();

  @NotNull
  String getName();
}
