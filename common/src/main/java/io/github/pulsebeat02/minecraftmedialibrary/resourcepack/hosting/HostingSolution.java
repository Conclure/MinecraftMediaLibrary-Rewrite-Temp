package io.github.pulsebeat02.minecraftmedialibrary.resourcepack.hosting;

import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface HostingSolution {

  @NotNull
  Path createUrl(@NotNull final Path resourcepack);

  @NotNull
  default String getName() {
    return "MML Custom Solution";
  }
}
