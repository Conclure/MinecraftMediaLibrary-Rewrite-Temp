package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import io.github.pulsebeat02.minecraftmedialibrary.throwable.UnknownPlaylistException;
import io.github.pulsebeat02.minecraftmedialibrary.utility.MediaExtractionUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.hc.core5.http.ParseException;
import org.jetbrains.annotations.NotNull;

public class SpotifyPlaylist implements TrackPlaylist {

  private final Playlist playlist;
  private final String url;

  public SpotifyPlaylist(@NotNull final String url)
      throws IOException, ParseException, SpotifyWebApiException {
    this.url = url;
    this.playlist =
        SpotifyProvider.getSpotifyApi()
            .getPlaylist(
                MediaExtractionUtils.getSpotifyID(url)
                    .orElseThrow(() -> new UnknownPlaylistException(url)))
            .build()
            .execute();
  }

  @Override
  public @NotNull SpotifyUser getAuthor() {
    return new SpotifyUser(this.playlist.getOwner());
  }

  @Override
  public int getVideoCount() {
    return this.playlist.getTracks().getTotal();
  }

  @Override
  public @NotNull String getUrl() {
    return this.url;
  }

  @Override
  public @NotNull String getName() {
    return this.playlist.getName();
  }

  @Override
  public @NotNull Collection<PlaylistTrack> getTracks() {
    return Arrays.stream(this.playlist.getTracks().getItems())
        .map(SpotifyPlaylistTrack::new)
        .collect(Collectors.toList());
  }

  @Override
  public @NotNull String getDescription() {
    return this.playlist.getDescription();
  }

  @Override
  public int getTotalFollowers() {
    return this.playlist.getFollowers().getTotal();
  }

  @Override
  public boolean isCollaborative() {
    return this.playlist.getIsCollaborative();
  }

  @Override
  public boolean isPubliclyAccessible() {
    return this.playlist.getIsPublicAccess();
  }

  @Override
  public @NotNull Map<String, String> getExternalUrls() {
    return this.playlist.getExternalUrls().getExternalUrls();
  }

  protected @NotNull Playlist getPlaylist() {
    return this.playlist;
  }
}
