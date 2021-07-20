package io.github.pulsebeat02.minecraftmedialibrary.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class OperatingSystem {

  private final String osName;
  private final OSType type;
  private final String version;
  private final String linuxDistro;

  public OperatingSystem(
      @NotNull final String osName, @NotNull final OSType type, @NotNull final String version) {
    this.osName = osName;
    this.type = type;
    this.version = version;
    this.linuxDistro = type == OSType.LINUX ? retrieveLinuxDistribution() : "";
  }

  private String retrieveLinuxDistribution() {
    final String[] cmd = {"/bin/sh", "-c", "cat /etc/*-release"};
    final StringBuilder concat = new StringBuilder();
    try {
      final Process p = Runtime.getRuntime().exec(cmd);
      final BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = bri.readLine()) != null) {
        concat.append(line);
        concat.append(" ");
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return concat.toString();
  }

  public String getOSName() {
    return osName;
  }

  public OSType getOSType() {
    return type;
  }

  public String getLinuxDistribution() {
    return linuxDistro;
  }

  public String getVersion() {
    return version;
  }

  @Override
  public String toString() {
    return String.format(
        "{os=%s,type=%s,linux-distro=%s}",
        osName, type.name().toLowerCase(Locale.ROOT), linuxDistro);
  }
}
