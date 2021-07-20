package io.github.pulsebeat02.minecraftmedialibrary.playlist;

import org.jetbrains.annotations.NotNull;

public interface WebPlayer extends WebPlayerControls {

    @NotNull String getCurrentSong();

    @NotNull String getUrl();

    @NotNull PlaylistType getType();

    int getIndex();

    void setIndex(final int index);

}
