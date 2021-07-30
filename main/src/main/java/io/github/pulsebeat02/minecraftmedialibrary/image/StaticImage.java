package io.github.pulsebeat02.minecraftmedialibrary.image;

import com.google.common.base.Preconditions;
import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.utility.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;

public class StaticImage implements MapImage {

  private final MediaLibraryCore core;
  private final EnhancedMapRenderer renderer;
  private final int[][] maps;

  /*

  [0] [1] [2]
  [3] [4] [5]  // for map matrix

   */

  private final Path image;
  private final int width;
  private final int height;

  public StaticImage(
      @NotNull final MediaLibraryCore core,
      @NotNull final Path image,
      final int[][] maps,
      final int width,
      final int height) {
    Preconditions.checkArgument(maps.length >= 1 && maps[0].length >= 1, "Invalid Map Matrix!");
    this.core = core;
    this.renderer = new EnhancedMapRenderer(maps);
    this.image = image;
    this.maps = maps;
    this.width = width;
    this.height = height;
  }

  @Override
  public void draw(final boolean resize) throws IOException {

    onStartDrawImage();

    BufferedImage img = ImageIO.read(this.image.toFile());
    final int itemframeWidth = this.maps.length;
    final int itemframeHeight = this.maps[0].length;
    final int width = itemframeWidth * 128;
    final int height = itemframeHeight * 128;

    if (resize) {
      img = ImageUtils.resize(img, width, height);
    }
    final BufferedImage[][] matrix = new BufferedImage[itemframeWidth][itemframeHeight];
    for (int rows = 0; rows < matrix.length; rows++) {
      for (int cols = 0; cols < matrix[rows].length; cols++) {
        matrix[rows][cols] = new BufferedImage(128, 128, img.getType());
        ImageUtils.trimForMapSize(matrix[rows][cols], rows, cols);
      }
    }

    this.renderer.drawMap(matrix);

    onFinishDrawImage();
  }

  @Override
  public void onStartDrawImage() {}

  @Override
  public void onFinishDrawImage() {}

  @Override
  public int[][] getMapMatrix() {
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
  public @NotNull MediaLibraryCore getCore() {
    return this.core;
  }
}
