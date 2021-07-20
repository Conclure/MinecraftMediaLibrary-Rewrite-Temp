package io.github.pulsebeat02.minecraftmedialibrary.ffmpeg;

import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface FFmpegProcess {

  @NotNull
  Path getInputPath();

  @NotNull
  Path getOutputPath();

  boolean isCompleted();

  @NotNull
  List<String> getArguments();
}
