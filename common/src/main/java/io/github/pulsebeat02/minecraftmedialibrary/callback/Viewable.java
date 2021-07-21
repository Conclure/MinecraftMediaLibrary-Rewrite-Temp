package io.github.pulsebeat02.minecraftmedialibrary.callback;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public interface Viewable {

  @NotNull
  UUID[] getViewers();
}
