package io.github.pulsebeat02.minecraftmedialibrary.callback;

import io.github.pulsebeat02.minecraftmedialibrary.dither.DitherAlgorithm;
import org.jetbrains.annotations.NotNull;

public interface MapCallback extends FrameCallback {

    long getMapId();

    @NotNull DitherAlgorithm getAlgorithm();

}
