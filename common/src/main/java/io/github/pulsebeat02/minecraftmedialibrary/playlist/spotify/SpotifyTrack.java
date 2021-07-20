package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import java.util.Collection;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface SpotifyTrack {

  @NotNull
  Collection<SpotifyArtist> getArtists();

  @NotNull
  Map<String, String> getExternalUrls();

  @NotNull
  String getPreviewUrl();

  @NotNull
  String getName();

  @NotNull
  String getId();

  int getDiscNumber();

  int getDuration();

  boolean isExplicit();

  boolean isPlayable();
}
