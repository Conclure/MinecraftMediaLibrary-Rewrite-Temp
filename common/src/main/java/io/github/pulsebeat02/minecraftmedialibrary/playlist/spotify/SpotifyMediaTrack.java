package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.ExternalUrl;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Identifier;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Namespace;
import java.util.Collection;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface SpotifyMediaTrack extends Namespace, Identifier, ExternalUrl {

  @NotNull
  Collection<Artist> getArtists();

  @NotNull
  Map<String, String> getExternalUrls();

  @NotNull
  String getPreviewUrl();

  int getDiscNumber();

  int getDuration();

  boolean isExplicit();

  boolean isPlayable();
}
