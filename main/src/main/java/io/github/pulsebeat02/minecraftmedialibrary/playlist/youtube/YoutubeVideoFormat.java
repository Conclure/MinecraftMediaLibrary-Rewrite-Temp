package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import org.jetbrains.annotations.NotNull;

public final class YoutubeVideoFormat implements VideoFormat {

  private final com.github.kiulian.downloader.model.videos.formats.VideoFormat format;

  YoutubeVideoFormat(
      @NotNull final com.github.kiulian.downloader.model.videos.formats.VideoFormat format) {
    this.format = format;
  }

  @Override
  public int getWidth() {
    return this.format.width();
  }

  @Override
  public int getHeight() {
    return this.format.height();
  }

  @Override
  public int getFPS() {
    return this.format.fps();
  }

  @Override
  public @NotNull String getQualityLabel() {
    return this.format.qualityLabel();
  }

  @Override
  public @NotNull VideoQuality getQuality() {
    return VideoQuality.ofKey(this.format.videoQuality().name());
  }
}
