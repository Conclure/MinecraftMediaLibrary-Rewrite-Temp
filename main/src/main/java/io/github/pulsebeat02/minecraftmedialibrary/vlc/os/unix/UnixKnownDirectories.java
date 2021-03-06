package io.github.pulsebeat02.minecraftmedialibrary.vlc.os.unix;

import com.google.common.collect.ImmutableSet;
import io.github.pulsebeat02.minecraftmedialibrary.analysis.OSType;
import io.github.pulsebeat02.minecraftmedialibrary.vlc.os.WellKnownDirectoryProvider;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public class UnixKnownDirectories implements WellKnownDirectoryProvider {
  @Override
  public @NotNull OSType getOperatingSystem() {
    return OSType.UNIX;
  }

  @Override
  public @NotNull Collection<String> getSearchDirectories() {
    return ImmutableSet.of(
        "/usr/lib/x86_64-linux-gnu",
        "/usr/lib64",
        "/usr/local/lib64",
        "/usr/lib/i386-linux-gnu",
        "/usr/lib",
        "/usr/local/lib");
  }
}
