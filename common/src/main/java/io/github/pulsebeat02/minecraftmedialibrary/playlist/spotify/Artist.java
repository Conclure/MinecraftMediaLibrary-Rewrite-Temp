package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Identifier;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Namespace;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.ResourceUrl;
import org.jetbrains.annotations.NotNull;

public interface Artist
    extends Namespace, Identifier, ResourceUrl, ExternalUrl, FollowerCount, ImageResource {

  int getPopularity();

  @NotNull
  String[] getGenres();
}
