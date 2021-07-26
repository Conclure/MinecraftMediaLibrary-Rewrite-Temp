package io.github.pulsebeat02.minecraftmedialibrary.image;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class StaticImage implements MapImage {

  private final MediaLibraryCore core;
  private final List<Integer> maps;
  private final Path image;
  private final int width;
  private final int height;

  public StaticImage(
      @NotNull final MediaLibraryCore core,
      @NotNull final Path image,
      @NotNull final List<Integer> maps,
      final int width,
      final int height) {
    this.core = core;
    this.image = image;
    this.maps = maps;
    this.width = width;
    this.height = height;
  }

  @Override
  public void draw() {
    onDrawImage();
  }

  @Override
  public void onDrawImage() {}

  @Override
  public @NotNull List<Integer> getMapIDs() {
    return this.maps;
  }

  @Override
  public @NotNull Path getImagePath() {
    return this.image;
  }

  @Override
  public int getFrameHeight() {
    return this.height;
  }

  @Override
  public int getFrameWidth() {
    return this.width;
  }

  @Override
  public @NotNull MediaLibraryCore getLibrary() {
    return this.core;
  }
}
