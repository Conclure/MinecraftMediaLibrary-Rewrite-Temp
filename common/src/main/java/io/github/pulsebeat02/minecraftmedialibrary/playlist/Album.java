package io.github.pulsebeat02.minecraftmedialibrary.playlist;

import org.jetbrains.annotations.NotNull;

public interface Album extends ExternalUrl, Namespace {

  @NotNull
  String getAuthor();

  int getVideoCount();
}
