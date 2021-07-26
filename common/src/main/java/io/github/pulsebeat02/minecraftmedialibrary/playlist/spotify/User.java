package io.github.pulsebeat02.minecraftmedialibrary.playlist.spotify;

import io.github.pulsebeat02.minecraftmedialibrary.playlist.ResourceUrl;
import org.jetbrains.annotations.NotNull;

public interface User extends ExternalUrl, FollowerCount, ImageResource, ResourceUrl {

  @NotNull
  String getBirthday();

  @NotNull
  String getDisplayName();

  @NotNull
  String getEmail();

  @NotNull
  Subscription getSubscription();
}
