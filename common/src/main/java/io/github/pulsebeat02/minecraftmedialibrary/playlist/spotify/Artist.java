package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.ExternalUrl;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Identifier;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Namespace;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface Artist extends Namespace, Identifier, ExternalUrl {

  @NotNull
  Map<String, String> getExternalUrls();

  int getTotalFollowers();

  int getPopularity();

  @NotNull
  String[] getGenres();

  @NotNull
  SpotifyMediaImage[] getImages();
}
