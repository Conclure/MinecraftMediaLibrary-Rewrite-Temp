package io.github.pulsebeat02.minecraftmedialibrary.playlist;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public class WebsiteControls implements WebPlayerControls {

  private final String url;
  private final List<String> songs;
  private final PlaylistType type;
  private int index;

  public WebsiteControls(final String url, @NotNull final PlaylistType type) {
    this.url = url;
      this.songs = getSongs();
    this.type = type;
  }

  private List<String> getSongs() {
    if (this.type == PlaylistType.YOUTUBE) {
      return new YoutubePlaylistHelper(this.url).getAlbumSongs();
    } else if (this.type == PlaylistType.SPOTIFY) {
      return new SpotifyPlaylistHelper(this.url).getAlbumSongs();
    } else {
      throw new UnsupportedOperationException("Unsupported Playlist!");
    }
  }

  @Override
  public void skipSong() {}

  @Override
  public void previousSong() {}

  @Override
  public void pauseSong() {}

  @Override
  public void resumeSong() {}

  @Override
  public void seekToTime(final int seconds) {}

  @Override
  public void randomize() {}

  @Override
  public void loopMode(final boolean mode) {}
}
