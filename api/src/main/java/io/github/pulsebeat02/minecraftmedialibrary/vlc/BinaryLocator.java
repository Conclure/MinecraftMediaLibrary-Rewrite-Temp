package io.github.pulsebeat02.minecraftmedialibrary.vlc;

import io.github.pulsebeat02.minecraftmedialibrary.LibraryInjectable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface BinaryLocator extends LibraryInjectable {

  @NotNull
  Optional<Path> locate() throws IOException, InterruptedException;

  @NotNull
  Path getPath();

  @NotNull
  BinaryInstaller getInstaller();

  @NotNull
  BinarySearcher getSearcher();
}
