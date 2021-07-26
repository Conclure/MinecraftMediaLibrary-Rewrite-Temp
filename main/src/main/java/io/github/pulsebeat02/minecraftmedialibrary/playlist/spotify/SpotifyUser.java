package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import io.github.pulsebeat02.minecraftmedialibrary.throwable.UnknownPlaylistException;
import io.github.pulsebeat02.minecraftmedialibrary.utility.MediaExtractionUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.apache.hc.core5.http.ParseException;
import org.jetbrains.annotations.NotNull;

public class SpotifyUser implements User {

  private final String url;
  private final com.wrapper.spotify.model_objects.specification.User user;

  public SpotifyUser(@NotNull final String url)
      throws IOException, ParseException, SpotifyWebApiException {
    this.url = url;
    this.user =
        SpotifyProvider.getSpotifyApi()
            .getUsersProfile(
                MediaExtractionUtils.getSpotifyID(url)
                    .orElseThrow(() -> new UnknownPlaylistException(url)))
            .build()
            .execute();
  }

  public SpotifyUser(@NotNull final com.wrapper.spotify.model_objects.specification.User user) {
    this.url = user.getUri();
    this.user = user;
  }

  @Override
  public @NotNull String getUrl() {
    return this.url;
  }

  @Override
  public @NotNull Map<String, String> getExternalUrls() {
    return this.user.getExternalUrls().getExternalUrls();
  }

  @Override
  public int getTotalFollowers() {
    return this.user.getFollowers().getTotal();
  }

  @Override
  public @NotNull Image[] getImages() {
    return Arrays.stream(this.user.getImages())
        .map(image -> new SpotifyImage(image.getUrl(), image.getWidth(), image.getHeight()))
        .toArray(SpotifyImage[]::new);
  }

  @Override
  public @NotNull String getBirthday() {
    return this.user.getBirthdate();
  }

  @Override
  public @NotNull String getDisplayName() {
    return this.user.getDisplayName();
  }

  @Override
  public @NotNull String getEmail() {
    return this.user.getEmail();
  }

  @Override
  public @NotNull Subscription getSubscription() {
    return Subscription.keyOf(this.user.getProduct().type);
  }
}
