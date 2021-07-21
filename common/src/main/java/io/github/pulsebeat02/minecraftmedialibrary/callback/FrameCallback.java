package io.github.pulsebeat02.minecraftmedialibrary.callback;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.nms.PacketHandler;
import io.github.pulsebeat02.minecraftmedialibrary.player.Dimension;
import org.jetbrains.annotations.NotNull;

public interface FrameCallback extends Dimension, Viewable {

  void process(final int[] data);

  // width in pixels
  int getVideoWidth();

  int getFrameDelay();

  long getLastUpdated();

  void setLastUpdated(long lastUpdated);

  @NotNull
  MediaLibraryCore getLibrary();

  @NotNull
  PacketHandler getPacketHandle();
}
