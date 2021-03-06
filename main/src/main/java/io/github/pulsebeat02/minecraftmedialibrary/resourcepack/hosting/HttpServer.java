package io.github.pulsebeat02.minecraftmedialibrary.resourcepack.hosting;

import io.github.pulsebeat02.minecraftmedialibrary.http.HttpDaemon;
import io.github.pulsebeat02.minecraftmedialibrary.http.HttpServerDaemon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class HttpServer implements HttpDaemonSolution {

  private static String HTTP_SERVER_IP;

  static {
    try (final BufferedReader in =
        new BufferedReader(
            new InputStreamReader(new URL("https://checkip.amazonaws.com").openStream()))) {
      HTTP_SERVER_IP = in.readLine();
    } catch (final IOException e) {
      HTTP_SERVER_IP = "127.0.0.1"; // fallback ip
      e.printStackTrace();
    }
  }

  private final HttpDaemon daemon;

  public HttpServer(@NotNull final String path, final int port) throws IOException {
    this(path, HTTP_SERVER_IP, port);
  }

  public HttpServer(@NotNull final String path, @NotNull final String ip, final int port)
      throws IOException {
    this.daemon = new HttpServerDaemon(path, ip, port);
  }

  @Override
  public @NotNull Path createUrl(@NotNull final Path file) {
    return this.daemon.getRelativePath(file);
  }

  @Override
  public void startServer() {
    this.daemon.start();
  }

  @Override
  public void stopServer() {
    this.daemon.stop();
  }

  @Override
  public @NotNull HttpDaemon getDaemon() {
    return this.daemon;
  }
}
