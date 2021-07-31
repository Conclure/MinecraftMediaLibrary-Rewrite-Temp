package io.github.pulsebeat02.minecraftmedialibrary.vlc.os;

import io.github.pulsebeat02.minecraftmedialibrary.analysis.OSType;
import org.jetbrains.annotations.NotNull;

public interface HostOperator {

  @NotNull
  OSType getOperatingSystem();
}
