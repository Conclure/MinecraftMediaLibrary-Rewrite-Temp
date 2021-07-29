package io.github.pulsebeat02.minecraftmedialibrary.image;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;

import static io.github.pulsebeat02.minecraftmedialibrary.decoder.GifDecoder.GifImage;

public class GifPlayer {


  private final GifImage image;
  private final int[][] maps;
  private final int frameCount;
  private CompletableFuture future;
  private int frame;

  public GifPlayer(@NotNull final GifImage image, final int[][] maps) {
    this.image = image;
    this.maps = maps;
    this.frameCount = image.getFrameCount();
  }

  public void start() {


  }

  public int getCurrentFrame() {
    return this.frame;
  }

  public int getFrameCount() {
    return this.frameCount;
  }

  public int getWidth() {
    return this.image.getWidth();
  }

  public int getHeight() {
    return this.image.getHeight();
  }
}
