package io.github.pulsebeat02.minecraftmedialibrary.callback;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.dither.DitherAlgorithm;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public class MapCallback extends FrameCallback implements MapCallbackDispatcher {

  private final DitherAlgorithm algorithm;
  private final int map;

  public MapCallback(
      @NotNull final MediaLibraryCore core,
      final UUID[] viewers,
      final DitherAlgorithm algorithm,
      final int map,
      final int width,
      final int height,
      final int blockWidth,
      final int delay) {
    super(core, viewers, width, height, blockWidth, delay);
    this.algorithm = algorithm;
    this.map = map;
  }

  @Override
  public void process(final int[] data) {
    final long time = System.currentTimeMillis();
    if (time - getLastUpdated() >= getFrameDelay()) {
      setLastUpdated(time);
      final int width = getBlockWidth();
      getPacketHandler()
          .displayMaps(
              getViewers(),
              this.map,
              getWidth(),
              getHeight(),
              this.algorithm.ditherIntoMinecraft(data, width),
              width);
    }
  }

  @Override
  public long getMapId() {
    return this.map;
  }

  @Override
  public @NotNull DitherAlgorithm getAlgorithm() {
    return this.algorithm;
  }
}
