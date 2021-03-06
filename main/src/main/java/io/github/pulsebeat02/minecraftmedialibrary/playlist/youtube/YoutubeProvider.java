package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import com.github.kiulian.downloader.YoutubeDownloader;

public final class YoutubeProvider {

  private static final YoutubeDownloader YOUTUBE_DOWNLOADER;

  static {
    YOUTUBE_DOWNLOADER = new YoutubeDownloader();
  }

  protected static YoutubeDownloader getYoutubeDownloader() {
    return YOUTUBE_DOWNLOADER;
  }
}
