package io.github.pulsebeat02.minecraftmedialibrary.vlc;

import io.github.pulsebeat02.minecraftmedialibrary.LibraryInjectable;
import io.github.pulsebeat02.minecraftmedialibrary.vlc.os.SilentInstallationProvider;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

public interface BinaryInstaller extends LibraryInjectable {

  @NotNull
  SilentInstallationProvider getProvider();

  void download() throws IOException, InterruptedException;
}
