package io.github.pulsebeat02.minecraftmedialibrary.extraction;

import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public interface VideoDownloader {

  @NotNull
  Path downloadVideo();

  void onStartVideoDownload();

  void onFinishVideoDownload();
}
