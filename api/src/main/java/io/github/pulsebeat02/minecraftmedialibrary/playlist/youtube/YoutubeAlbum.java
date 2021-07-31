package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Album;
import org.jetbrains.annotations.NotNull;

public interface YoutubeAlbum extends Album {

  @NotNull
  String getAuthor();
}
