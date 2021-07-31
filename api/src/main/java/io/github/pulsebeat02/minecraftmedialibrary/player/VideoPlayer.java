package io.github.pulsebeat02.minecraftmedialibrary.player;

import io.github.pulsebeat02.minecraftmedialibrary.LibraryInjectable;
import io.github.pulsebeat02.minecraftmedialibrary.callback.Callback;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.ResourceUrl;
import io.github.pulsebeat02.minecraftmedialibrary.utility.Dimension;
import org.jetbrains.annotations.NotNull;

public interface VideoPlayer extends LibraryInjectable, ResourceUrl, Dimension {

  @NotNull
  Callback getCallback();

  @NotNull
  String getSoundKey();

  @NotNull
  PlayerControls getPlayerState();

  void setPlayerState(@NotNull final PlayerControls controls);

  void onPlayerStateChange(@NotNull final PlayerControls status);

  int getFrameRate();

  long getElapsedTime();

  boolean isRepeated();

  void onRepeat(final boolean status);

  long getStartTime();

  void setStartTime(final long seconds);

  void initializePlayer(final long seconds);
}
