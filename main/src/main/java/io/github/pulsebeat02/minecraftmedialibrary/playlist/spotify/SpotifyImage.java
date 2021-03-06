package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.utility.ImmutableDimension;
import org.jetbrains.annotations.NotNull;

public final class SpotifyImage implements Image {

  private final com.wrapper.spotify.model_objects.specification.Image image;
  private final ImmutableDimension dimension;

  SpotifyImage(@NotNull final com.wrapper.spotify.model_objects.specification.Image image) {
    this.image = image;
    this.dimension = new ImmutableDimension(image.getWidth(), image.getHeight());
  }

  @Override
  public @NotNull String getUrl() {
    return this.image.getUrl();
  }

  protected @NotNull com.wrapper.spotify.model_objects.specification.Image getImage() {
    return this.image;
  }

  @Override
  public @NotNull ImmutableDimension getDimensions() {
    return this.dimension;
  }
}
