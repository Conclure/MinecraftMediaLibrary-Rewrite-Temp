package io.github.pulsebeat02.minecraftmedialibrary.callback;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.nms.PacketHandler;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Dimension;
import org.jetbrains.annotations.NotNull;

public interface Callback extends Dimension, Viewable {

  void process(final int[] data);

  // width in pixels
  int getBlockWidth();

  int getFrameDelay();

  long getLastUpdated();

  void setLastUpdated(long lastUpdated);

  @NotNull
  MediaLibraryCore getLibrary();

  @NotNull
  PacketHandler getPacketHandler();
}
