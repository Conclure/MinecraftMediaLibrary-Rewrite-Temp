package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Album;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface YoutubeVideoPlaylist extends Album {

  @NotNull
  Collection<YoutubeVideo> getVideos();

  long getViewCount();
}
