package io.github.pulsebeat02.minecraftmedialibrary.http;

import com.google.common.base.Preconditions;
import io.github.pulsebeat02.minecraftmedialibrary.Logger;
import io.github.pulsebeat02.minecraftmedialibrary.http.request.ZipHeader;
import io.github.pulsebeat02.minecraftmedialibrary.http.request.ZipRequest;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class HttpServer implements HttpDaemon, ZipRequest {

  private static final ExecutorService EXECUTOR_SERVICE;

  static {
    EXECUTOR_SERVICE = Executors.newCachedThreadPool();
  }

  private final Path directory;
  private final boolean verbose;
  private final int port;

  private ServerSocket socket;
  private ZipHeader header;
  private boolean running;

  public HttpServer(final int port, @NotNull final String path) throws IOException {

    this.running = true;
    this.port = port;
    this.directory = Paths.get(path);
    this.header = ZipHeader.ZIP;
    this.verbose = true;

    try {
      this.socket = new ServerSocket(port);
      this.socket.setReuseAddress(true);
    } catch (final BindException e) {
      Logger.error(
          "The port specified is being used by another process. Please make sure to port-forward the port first and make sure it is open.");
      Logger.error(e.getMessage());
      return;
    }

    Logger.info("========================================");
    Logger.info("Started HTTP Server: ");
    Logger.info("========================================");
    Logger.info(String.format("IP: %s", Bukkit.getIp()));
    Logger.info(String.format("PORT: %d", port));
    Logger.info(String.format("DIRECTORY: %s", path));
    Logger.info("========================================");
  }

  @Override
  public void start() {
    onServerStart();
    Preconditions.checkState(!Bukkit.isPrimaryThread());
    while (this.running) {
      EXECUTOR_SERVICE.submit(
          () -> new FileRequestHandler(this, this.socket, this.header).handleIncomingRequest());
    }
  }

  @Override
  public void onServerStart() {}

  @Override
  public void stop() {
    onServerTermination();
    this.running = false;
    if (!this.socket.isClosed()) {
      try {
        this.socket.close();
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void onServerTermination() {}

  @Override
  public void onClientConnection(@NotNull final Socket client) {}

  @Override
  public void onRequestFailure(@NotNull final Socket client) {}

  @Override
  public boolean isVerbose() {
    return this.verbose;
  }

  @Override
  public @NotNull Path getServerPath() {
    return this.directory;
  }

  @Override
  public int getPort() {
    return this.port;
  }

  @Override
  public @NotNull ZipHeader getHeader() {
    return this.header;
  }

  @Override
  public void setZipHeader(@NotNull final ZipHeader header) {
    this.header = header;
  }
}
