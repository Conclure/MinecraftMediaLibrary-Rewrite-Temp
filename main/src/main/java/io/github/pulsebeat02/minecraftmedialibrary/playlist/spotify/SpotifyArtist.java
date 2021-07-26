package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import io.github.pulsebeat02.minecraftmedialibrary.throwable.UnknownArtistException;
import io.github.pulsebeat02.minecraftmedialibrary.utility.MediaExtractionUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.apache.hc.core5.http.ParseException;
import org.jetbrains.annotations.NotNull;

public class SpotifyArtist implements Artist {

  private final String url;
  private final com.wrapper.spotify.model_objects.specification.Artist artist;

  public SpotifyArtist(@NotNull final String url)
      throws IOException, ParseException, SpotifyWebApiException {
    this.url = url;
    this.artist =
        SpotifyProvider.getSpotifyApi()
            .getArtist(
                MediaExtractionUtils.getSpotifyID(url)
                    .orElseThrow(() -> new UnknownArtistException(url)))
            .build()
            .execute();
  }

  public SpotifyArtist(@NotNull final ArtistSimplified simplified)
      throws IOException, ParseException, SpotifyWebApiException {
    final String url = simplified.getUri();
    this.url = url;
    this.artist =
        SpotifyProvider.getSpotifyApi()
            .getArtist(
                MediaExtractionUtils.getSpotifyID(url)
                    .orElseThrow(() -> new UnknownArtistException(url)))
            .build()
            .execute();
  }

  @Override
  public @NotNull String getId() {
    return this.artist.getId();
  }

  @Override
  public @NotNull String getName() {
    return this.artist.getName();
  }

  @Override
  public @NotNull Map<String, String> getExternalUrls() {
    return this.artist.getExternalUrls().getExternalUrls();
  }

  @Override
  public int getTotalFollowers() {
    return this.artist.getFollowers().getTotal();
  }

  @Override
  public int getPopularity() {
    return this.artist.getPopularity();
  }

  @Override
  public @NotNull String[] getGenres() {
    return this.artist.getGenres();
  }

  @Override
  public @NotNull SpotifyMediaImage[] getImages() {
    return Arrays.stream(this.artist.getImages())
        .map(image -> new SpotifyImage(image.getUrl(), image.getWidth(), image.getHeight()))
        .toArray(SpotifyImage[]::new);
  }

  @Override
  public @NotNull String getUrl() {
    return this.url;
  }
}
