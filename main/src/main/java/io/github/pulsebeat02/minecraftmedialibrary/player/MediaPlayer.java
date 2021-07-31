package io.github.pulsebeat02.minecraftmedialibrary.player;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.callback.Callback;
import io.github.pulsebeat02.minecraftmedialibrary.callback.FrameCallback;
import io.github.pulsebeat02.minecraftmedialibrary.utility.ImmutableDimension;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public abstract class MediaPlayer implements VideoPlayer {

  private final MediaLibraryCore core;
  private final FrameCallback callback;
  private final ImmutableDimension dimensions;
  private final String soundKey;
  private final String url;
  private final int frameRate;
  private final boolean repeat;

  private long elapsed;
  private long start;

  private PlayerControls controls;

  public MediaPlayer(
      @NotNull final MediaLibraryCore core,
      @NotNull final FrameCallback callback,
      @NotNull final ImmutableDimension dimensions,
      @NotNull final String url,
      final int frameRate,
      final boolean repeat) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "URL cannot be empty or null!");
    Preconditions.checkArgument(dimensions.getWidth() >= 0, "Width must be above or equal to 0!");
    Preconditions.checkArgument(dimensions.getHeight() >= 0, "Height must be above or equal to 0!");
    this.core = core;
    this.callback = callback;
    this.dimensions = dimensions;
    this.soundKey = core.getPlugin().getName().toLowerCase(Locale.ROOT);
    this.url = url;
    this.frameRate = frameRate;
    this.repeat = repeat;
  }

  @Override
  public @NotNull Callback getCallback() {
    return this.callback;
  }

  @Override
  public @NotNull String getSoundKey() {
    return this.soundKey;
  }

  @Override
  public @NotNull PlayerControls getPlayerState() {
    return this.controls;
  }

  @Override
  public void setPlayerState(@NotNull final PlayerControls controls) {
    onPlayerStateChange(controls);
    this.controls = controls;
  }

  @Override
  public void onPlayerStateChange(@NotNull final PlayerControls status) {}

  @Override
  public int getFrameRate() {
    return this.frameRate;
  }

  @Override
  public long getElapsedTime() {
    return this.elapsed;
  }

  @Override
  public boolean isRepeated() {
    onRepeat(this.repeat);
    return this.repeat;
  }

  @Override
  public void onRepeat(final boolean status) {}

  @Override
  public long getStartTime() {
    return this.start;
  }

  @Override
  public void setStartTime(final long seconds) {
    this.start = seconds;
  }

  @Override
  public @NotNull String getUrl() {
    return this.url;
  }

  @Override
  public @NotNull ImmutableDimension getDimensions() {
    return this.dimensions;
  }

  @Override
  public @NotNull MediaLibraryCore getCore() {
    return this.core;
  }
}
