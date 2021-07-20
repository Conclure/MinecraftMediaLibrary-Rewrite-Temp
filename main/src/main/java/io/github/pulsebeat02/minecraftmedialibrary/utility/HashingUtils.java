package io.github.pulsebeat02.minecraftmedialibrary.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class HashingUtils {

  private HashingUtils() {}

  public static Optional<byte[]> createHashSHA(@NotNull final Path file) {
    try {
      final MessageDigest digest = MessageDigest.getInstance("SHA-1");
      final InputStream fis = new FileInputStream(file.toFile());
      int n = 0;
      final byte[] buffer = new byte[8192];
      while (n != -1) {
        n = fis.read(buffer);
        if (n > 0) {
          digest.update(buffer, 0, n);
        }
      }
      return Optional.of(digest.digest());
    } catch (final IOException | NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
