package io.github.pulsebeat02.minecraftmedialibrary.playlist;

public interface WebPlayerControls {

  void skipSong();

  void previousSong();

  void pauseSong();

  void resumeSong();

  void seekToTime(final int seconds);

  void randomize();

  void loopMode(final boolean mode);
}
