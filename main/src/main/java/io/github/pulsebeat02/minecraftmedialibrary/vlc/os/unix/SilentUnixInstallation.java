package io.github.pulsebeat02.minecraftmedialibrary.vlc.os.unix;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.analysis.OSType;
import io.github.pulsebeat02.minecraftmedialibrary.vlc.os.SilentInstallation;
import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class SilentUnixInstallation extends SilentInstallation {

  public SilentUnixInstallation(
      @NotNull final MediaLibraryCore core, @NotNull final Path directory) {
    super(core, directory);
  }

  @Override
  public @NotNull OSType getOperatingSystem() {
    return OSType.UNIX;
  }

  @Override
  public void downloadBinaries() throws IOException, InterruptedException {}

  @Override
  public void loadNativeBinaries() throws IOException {
    super.loadNativeBinaries();
  }
}
