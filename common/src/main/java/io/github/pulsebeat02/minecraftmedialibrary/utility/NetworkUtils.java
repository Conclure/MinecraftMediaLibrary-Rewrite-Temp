package io.github.pulsebeat02.minecraftmedialibrary.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class NetworkUtils {

  private static String PUBLIC_IP_ADDRESS;

  static {
    try (final BufferedReader in =
        new BufferedReader(
            new InputStreamReader(new URL("https://checkip.amazonaws.com").openStream()))) {
      PUBLIC_IP_ADDRESS = in.readLine();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private NetworkUtils() {}

  public static String getPublicIPAdress() {
    return PUBLIC_IP_ADDRESS;
  }
}
