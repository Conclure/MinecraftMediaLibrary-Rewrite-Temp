package io.github.pulsebeat02.minecraftmedialibrary;

import org.jetbrains.annotations.NotNull;

public interface LibraryInjectable {

  @NotNull
  MediaLibraryCore getCore();
}
