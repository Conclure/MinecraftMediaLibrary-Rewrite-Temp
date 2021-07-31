package io.github.pulsebeat02.minecraftmedialibrary.callback;

import org.jetbrains.annotations.NotNull;

public interface ScoreboardCallbackDispatcher extends Callback {

  @NotNull
  String getScoreboardName();

  int getScoreboardId();
}
