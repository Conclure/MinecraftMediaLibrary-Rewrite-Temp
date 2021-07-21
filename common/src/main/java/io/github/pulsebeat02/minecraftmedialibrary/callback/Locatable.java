package io.github.pulsebeat02.minecraftmedialibrary.callback;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Locatable {

  @NotNull
  Location getLocation();
}
