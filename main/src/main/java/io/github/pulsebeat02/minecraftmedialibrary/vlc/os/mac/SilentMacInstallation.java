package io.github.pulsebeat02.minecraftmedialibrary.vlc.os.mac;

import io.github.pulsebeat02.minecraftmedialibrary.Logger;
import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import io.github.pulsebeat02.minecraftmedialibrary.analysis.OSType;
import io.github.pulsebeat02.minecraftmedialibrary.task.CommandTask;
import io.github.pulsebeat02.minecraftmedialibrary.utility.FileUtils;
import io.github.pulsebeat02.minecraftmedialibrary.vlc.os.SilentInstallation;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jetbrains.annotations.NotNull;

public class SilentMacInstallation extends SilentInstallation {

  public SilentMacInstallation(
      @NotNull final MediaLibraryCore core, @NotNull final Path directory) {
    super(core, directory);
  }

  @Override
  public @NotNull OSType getOperatingSystem() {
    return OSType.MAC;
  }

  @Override
  public void downloadBinaries() throws IOException, InterruptedException {

    final Path directory = getDirectory();
    final Path dmg = directory.resolve("VLC.dmg");
    final Path disk = Paths.get("/Volumes/VLC media player");
    final Path app = directory.resolve("VLC.app");

    FileUtils.copyURLToFile(getCore().getDiagnostics().getVlcUrl(), dmg);

    if (mountDiskImage(dmg) != 0) {
      throw new RuntimeException("A severe I/O error has occurred. Could not mount disk file!");
    }
    Logger.info("Successfully mounted disk!");

    org.apache.commons.io.FileUtils.copyDirectory(disk.resolve("VLC.app").toFile(), app.toFile());

    if (changePermissions(app) != 0) {
      throw new RuntimeException(
          "A severe permission error has occurred. Could not change permissions of VLC application!");
    }
    Logger.info("Successfully changed permissions for application!");

    if (unmountDiskImage(disk) != 0) {
      throw new RuntimeException("A severe I/O error has occurred. Could not unmount disk file!");
    }
    Logger.info("Successfully unmounted disk!");

    deleteArchive(dmg);

    loadNativeBinaries();
  }

  @Override
  public void loadNativeBinaries() throws IOException {
    super.loadNativeBinaries();
  }

  private int mountDiskImage(@NotNull final Path dmg) throws IOException, InterruptedException {

    final CommandTask t =
        new CommandTask(new String[] {"/usr/bin/hdiutil", "attach", dmg.toString()}, true);

    Logger.info("============= DMG INFORMATION =============");
    Logger.info(t.getResult());
    Logger.info("===========================================");

    return t.getProcess().waitFor();
  }

  private int unmountDiskImage(@NotNull final Path path) throws IOException, InterruptedException {

    final CommandTask t =
        new CommandTask(new String[] {"diskutil", "unmount", path.toString()}, true);

    Logger.info("=========== UNMOUNT INFORMATION ===========");
    Logger.info(t.getResult());
    Logger.info("===========================================");

    return t.getProcess().waitFor();
  }

  private int changePermissions(@NotNull final Path path) throws IOException, InterruptedException {
    return new CommandTask(new String[] {"chmod", "-R", "755", path.toString()}, true)
        .getProcess()
        .waitFor();
  }
}
