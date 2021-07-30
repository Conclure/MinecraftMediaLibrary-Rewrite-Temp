package io.github.pulsebeat02.minecraftmedialibrary.image;

import io.github.pulsebeat02.minecraftmedialibrary.LibraryInjectable;
import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public interface MapImage extends LibraryInjectable {

  void draw(final boolean resize) throws IOException;

  void onStartDrawImage();

  void onFinishDrawImage();

  int[][] getMapMatrix();

  @NotNull
  Path getImagePath();

  int getFrameHeight();

  int getFrameWidth();

}
