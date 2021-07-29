package io.github.pulsebeat02.minecraftmedialibrary.image;

public interface GifImage extends MapImage {

    void stopDrawing();

    void onStopDrawing();

    int getCurrentFrame();

    int getFrameCount();

    int getWidth();

    int getHeight();
}
