package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Album;
import org.jetbrains.annotations.NotNull;

public interface SpotifyAlbum extends Album, ExternalUrl {

  @NotNull
  User getAuthor();
}
