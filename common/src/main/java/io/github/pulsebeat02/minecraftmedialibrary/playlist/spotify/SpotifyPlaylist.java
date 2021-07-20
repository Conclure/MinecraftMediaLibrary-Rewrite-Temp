package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Album;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface SpotifyPlaylist extends Album {

  @NotNull
  Collection<SpotifyTrack> getTracks();

  @NotNull
  String getDescription();

  int getFollowerCount();
}
