package io.github.pulsebeat02.minecraftmedialibrary.callback;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityCallback extends FrameCallback, Locatable {

    @NotNull Entity[] getEntities();

    @NotNull String getStringName();

}
