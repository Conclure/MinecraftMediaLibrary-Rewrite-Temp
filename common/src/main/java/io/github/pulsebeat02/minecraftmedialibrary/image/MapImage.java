package io.github.pulsebeat02.minecraftmedialibrary.image;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public interface MapImage {

  void draw(final boolean resize) throws IOException;

  void onStartDrawImage();

  void onFinishDrawImage();

  int[][] getMapMatrix();

  @NotNull
  Path getImagePath();

  int getFrameHeight();

  int getFrameWidth();

  @NotNull
  MediaLibraryCore getLibrary();
}
