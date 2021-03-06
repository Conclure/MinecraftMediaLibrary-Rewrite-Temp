package io.github.pulsebeat02.minecraftmedialibrary.extraction;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube.Video;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube.VideoQuality;
import org.jetbrains.annotations.NotNull;

public interface VideoDownloader {

  void downloadVideo(@NotNull final VideoQuality format, final boolean overwrite);

  void onStartVideoDownload();

  void onFinishVideoDownload();

  @NotNull
  Video getVideo();
}
