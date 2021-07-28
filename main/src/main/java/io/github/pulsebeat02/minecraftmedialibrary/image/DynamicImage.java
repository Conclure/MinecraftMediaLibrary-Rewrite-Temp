package io.github.pulsebeat02.minecraftmedialibrary.image;

import com.google.common.base.Preconditions;
import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class DynamicImage implements MapImage {

  private final MediaLibraryCore core;
  private final int[][] maps;
  private final int width;
  private final int height;

  public DynamicImage(
      @NotNull final MediaLibraryCore core,
      @NotNull final Path image,
      final int[][] maps,
      final int width,
      final int height) {
    Preconditions.checkArgument(maps.length >= 1 && maps[0].length >= 1, "Invalid Map Matrix!");
    this.core = core;
    this.image = image;
    this.maps = maps;
    this.width = width;
    this.height = height;
  }

  @Override
  public void draw(final boolean resize) throws IOException {}

  @Override
  public void onStartDrawImage() {}

  @Override
  public void onFinishDrawImage() {}

  @Override
  public int[][] getMapMatrix() {
    return new int[0][];
  }

  @Override
  public @NotNull Path getImagePath() {
    return null;
  }

  @Override
  public int getFrameHeight() {
    return 0;
  }

  @Override
  public int getFrameWidth() {
    return 0;
  }

  @Override
  public @NotNull MediaLibraryCore getLibrary() {
    return null;
  }
}
