package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import org.jetbrains.annotations.NotNull;

public class SpotifyImage implements Image {

  private final String url;
  private final int width;
  private final int height;

  public SpotifyImage(@NotNull final String url, final int width, final int height) {
    this.url = url;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public @NotNull String getUrl() {
    return this.url;
  }
}
