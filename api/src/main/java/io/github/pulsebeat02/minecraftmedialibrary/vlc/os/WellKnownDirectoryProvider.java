package io.github.pulsebeat02.minecraftmedialibrary.vlc.os;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface WellKnownDirectoryProvider extends HostOperator {

  @NotNull
  Collection<String> getSearchDirectories();
}
