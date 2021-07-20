package io.github.pulsebeat02.minecraftmedialibrary.playlist;

import org.jetbrains.annotations.NotNull;

public interface Album {

  @NotNull
  String getUrl();

  @NotNull
  String getAuthor();

  @NotNull
  String getTitle();

  int getVideoCount();
}
