package io.github.pulsebeat02.minecraftmedialibrary.player;

import io.github.pulsebeat02.minecraftmedialibrary.callback.FrameCallback;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.ResourceUrl;
import java.util.Set;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface VideoPlayer extends ResourceUrl, Dimension {

  @NotNull
  FrameCallback getCallback();

  @NotNull
  String getSoundKey();

  @NotNull
  PlayerControls getPlayerState();

  void setPlayerState(@NotNull final PlayerControls controls);

  void onPlayerStateChange(@NotNull final PlayerControls status);

  int getFrameRate();

  long getElapsedTime();

  boolean isRepeated();

  void setRepeat(final boolean repeat);

  void onRepeat(final boolean status);

  long getStartTime();

  void setStartTime(final long seconds);

  @NotNull
  Set<Player> getViewers();
}
