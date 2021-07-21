package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.Identifier;
import io.github.pulsebeat02.minecraftmedialibrary.playlist.Namespace;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface SpotifyArtist extends Namespace, Identifier {

  @NotNull
  Map<String, String> getExternalUrls();

}
