package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.model.videos.VideoDetails;
import io.github.pulsebeat02.minecraftmedialibrary.throwable.DeadResourceLinkException;
import io.github.pulsebeat02.minecraftmedialibrary.utility.MediaExtractionUtils;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public class YoutubeVideo implements Video {

  private final String url;
  private final VideoDetails details;

  public YoutubeVideo(@NotNull final String url) {
    this.url = url;
    this.details =
        YoutubeProvider.getYoutubeDownloader()
            .getVideoInfo(
                new RequestVideoInfo(
                    MediaExtractionUtils.getYoutubeID(url)
                        .orElseThrow(() -> new DeadResourceLinkException(url))))
            .data()
            .details();
  }

  @Override
  public @NotNull String getUrl() {
    return this.url;
  }

  @Override
  public @NotNull String getId() {
    return this.details.videoId();
  }

  @Override
  public @NotNull String getLiveUrl() {
    return this.details.liveUrl();
  }

  @Override
  public @NotNull Collection<String> getKeywords() {
    return this.details.keywords();
  }

  @Override
  public long getViewCount() {
    return this.details.viewCount();
  }

  @Override
  public int getAverageRating() {
    return this.details.averageRating();
  }

  @Override
  public boolean isLiveContent() {
    return this.details.isLiveContent();
  }
}
