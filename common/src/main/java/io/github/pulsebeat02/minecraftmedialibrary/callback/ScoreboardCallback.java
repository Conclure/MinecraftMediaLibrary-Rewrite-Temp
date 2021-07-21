package io.github.pulsebeat02.minecraftmedialibrary.callback;

import org.jetbrains.annotations.NotNull;

public interface ScoreboardCallback extends FrameCallback {

  @NotNull
  String getScoreboardName();

  int getScoreboardId();
}
