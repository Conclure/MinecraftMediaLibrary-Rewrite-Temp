package io.github.pulsebeat02.minecraftmedialibrary.image;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class StaticImage implements Image {

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
    return maps;
  }

  @Override
  public @NotNull Path getImagePath() {
    return image;
  }

  @Override
  public int getFrameHeight() {
    return height;
  }

  @Override
  public int getFrameWidth() {
    return width;
  }

  @Override
  public @NotNull MediaLibraryCore getLibrary() {
    return core;
  }
}
