package io.github.pulsebeat02.minecraftmedialibrary.image;

import com.google.common.base.Preconditions;
import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.decoder.GifDecoder;
import io.github.pulsebeat02.minecraftmedialibrary.utility.ImageUtils;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;

import static io.github.pulsebeat02.minecraftmedialibrary.decoder.GifDecoder.GifImage;

public class DynamicImage implements io.github.pulsebeat02.minecraftmedialibrary.image.GifImage {

  private final MediaLibraryCore core;
  private final EnhancedMapRenderer renderer;
  private final Path imagePath;
  private final GifImage image;

  private final int[][] maps;
  private final int width;
  private final int height;
  private final int frameCount;

  private CompletableFuture<Void> future;
  private int frame;

  public DynamicImage(
      @NotNull final MediaLibraryCore core,
      @NotNull final Path image,
      final int[][] maps,
      final int width,
      final int height)
      throws IOException {
    Preconditions.checkArgument(maps.length >= 1 && maps[0].length >= 1, "Invalid Map Matrix!");
    this.core = core;
    this.renderer = new EnhancedMapRenderer(maps);
    this.imagePath = image;
    this.image = GifDecoder.read(new FileInputStream(image.toFile()));
    this.maps = maps;
    this.width = width;
    this.height = height;
    this.frameCount = this.image.getFrameCount();
  }

  @Override
  public void draw(final boolean resize) throws IOException {
    onStartDrawImage();
    this.future =
        CompletableFuture.runAsync(
            () -> {
              for (; this.frame < this.frameCount; this.frame++) {
                BufferedImage img = this.image.getFrame(this.frame);
                final int delay = this.image.getDelay(this.frame); // hundredth of second

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

                try {
                  Thread.sleep(delay);
                } catch (final InterruptedException e) {
                  e.printStackTrace();
                }
              }
            });
    onFinishDrawImage();
  }

  @Override
  public void stopDrawing() {
    onStopDrawing();
  }

  @Override
  public void onStopDrawing() {}

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
    return this.imagePath;
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

  @Override
  public int getCurrentFrame() {
    return this.frame;
  }

  @Override
  public int getFrameCount() {
    return this.frameCount;
  }

  @Override
  public int getWidth() {
    return this.image.getWidth();
  }

  @Override
  public int getHeight() {
    return this.image.getHeight();
  }
}
