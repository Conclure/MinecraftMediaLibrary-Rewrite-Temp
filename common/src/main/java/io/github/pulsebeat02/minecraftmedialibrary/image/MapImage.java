package io.github.pulsebeat02.minecraftmedialibrary.image;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.nio.file.Path;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface MapImage {

  void draw();

  void onDrawImage();

  @NotNull
  List<Integer> getMapIDs();

  @NotNull
  Path getImagePath();

  int getFrameHeight();

  int getFrameWidth();

  @NotNull
  MediaLibraryCore getLibrary();
}
