package io.github.pulsebeat02.minecraftmedialibrary.analysis;

import org.jetbrains.annotations.NotNull;

public interface CpuInfo {

  @NotNull
  String getArchitecture();

  boolean isBits64();
}
