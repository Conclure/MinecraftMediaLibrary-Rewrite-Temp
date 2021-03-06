package io.github.pulsebeat02.minecraftmedialibrary.http.request;

import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public interface FileRequest extends Request {

  @NotNull
  Path requestFileCallback(@NotNull final String request);

  @NotNull
  ZipHeader getHeader();
}
