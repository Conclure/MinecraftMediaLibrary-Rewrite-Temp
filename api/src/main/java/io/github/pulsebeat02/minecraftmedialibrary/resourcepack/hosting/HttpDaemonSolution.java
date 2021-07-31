package io.github.pulsebeat02.minecraftmedialibrary.resourcepack.hosting;

import io.github.pulsebeat02.minecraftmedialibrary.http.HttpDaemon;
import org.jetbrains.annotations.NotNull;

public interface HttpDaemonSolution extends HostingSolution {

  void startServer();

  void stopServer();

  @NotNull
  HttpDaemon getDaemon();

  @Override
  @NotNull
  default String getName() {
    return "MML HTTP Integrated Server";
  }
}
