package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Identifier;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.ResourceUrl;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface Video extends ResourceUrl, Identifier {

  @NotNull
  String getLiveUrl();

  @NotNull
  Collection<String> getKeywords();

  @NotNull
  Collection<VideoFormat> getVideoFormats();

  @NotNull
  Collection<AudioFormat> getAudioFormats();

  long getViewCount();

  int getAverageRating();

  boolean isLiveContent();
}
