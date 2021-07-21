package io.github.pulsebeat02.minecraftmedialibrary.playlist;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Identifier {

  @NotNull
  String getId();
}
