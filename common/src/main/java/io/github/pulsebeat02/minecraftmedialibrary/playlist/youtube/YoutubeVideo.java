package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface YoutubeVideo {

  @NotNull
  String getUrl();

  @NotNull
  String getVideoId();

  @NotNull
  String getLiveUrl();

  @NotNull
  Collection<String> getKeywords();

  long getViewCount();

  int getAverageRating();

  boolean isLiveContent();
}
