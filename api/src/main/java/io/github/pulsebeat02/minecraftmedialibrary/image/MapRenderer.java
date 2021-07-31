package io.github.pulsebeat02.minecraftmedialibrary.image;

import java.awt.image.BufferedImage;
import org.jetbrains.annotations.NotNull;

public interface MapRenderer {

  void drawMap(@NotNull final BufferedImage[][] images);
}
