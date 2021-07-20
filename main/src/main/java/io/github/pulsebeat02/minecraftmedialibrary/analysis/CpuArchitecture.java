package io.github.pulsebeat02.minecraftmedialibrary.analysis;

import org.jetbrains.annotations.NotNull;

public class CpuArchitecture {

  private final String architecture;
  private final boolean bits64;

  public CpuArchitecture(@NotNull final String architecture, final boolean bits64) {
    this.architecture = architecture;
    this.bits64 = bits64;
  }

  public String getArchitecture() {
    return architecture;
  }

  public boolean isBits64() {
    return bits64;
  }

  @Override
  public String toString() {
    return String.format("{arch=%s,64bits=%s}", architecture, bits64);
  }
}
