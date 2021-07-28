package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import org.jetbrains.annotations.NotNull;

public final class SpotifyImage implements Image {

  private final com.wrapper.spotify.model_objects.specification.Image image;

  SpotifyImage(@NotNull final com.wrapper.spotify.model_objects.specification.Image image) {
    this.image = image;
  }

  @Override
  public int getWidth() {
    return this.image.getWidth();
  }

  @Override
  public int getHeight() {
    return this.image.getHeight();
  }

  @Override
  public @NotNull String getUrl() {
    return this.image.getUrl();
  }

  protected @NotNull com.wrapper.spotify.model_objects.specification.Image getImage() {
    return this.image;
  }
}
