package io.github.pulsebeat02.minecraftmedialibrary.vlc.os;

import io.github.pulsebeat02.minecraftmedialibrary.Logger;
import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public abstract class SilentInstallation implements SilentInstallationProvider {

  private final MediaLibraryCore core;
  private final Path directory;
  private Path installation;

  public SilentInstallation(@NotNull final MediaLibraryCore core, @NotNull final Path directory) {
    this.core = core;
    this.directory = directory;
  }

  @Override
  public @NotNull Path getDirectory() {
    return this.directory;
  }

  @Override
  public @NotNull Path getInstallationPath() {
    return this.installation;
  }

  @Override
  public void setInstallationPath(@NotNull final Path path) {
    this.installation = path;
  }

  @Override
  public @NotNull MediaLibraryCore getCore() {
    return this.core;
  }

  @Override
  public void deleteArchive(@NotNull final Path archive) {
    try {
      Files.delete(archive);
      Logger.info("Archive successfully deleted.");
    } catch (final IOException e) {
      Logger.error("A severe I/O error occurred from deleting the archive file!");
      e.printStackTrace();
    }
  }
}
