package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.ExternalUrl;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Identifier;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface YoutubeVideoMedia extends ExternalUrl, Identifier {

  @NotNull
  String getLiveUrl();

  @NotNull
  Collection<String> getKeywords();

  long getViewCount();

  int getAverageRating();

  boolean isLiveContent();
}
