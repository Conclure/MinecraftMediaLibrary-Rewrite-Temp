package io.github.pulsebeat02.minecraftmedialibrary.resourcepack;

import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

// https://gist.github.com/ItsPepperpot/c927e635fb9609cfa2a97c93327f33f2 for example

public interface PackWrapper {

  @NotNull
  String getSoundName();

  @NotNull
  Path getResourcepackFilePath();

  @NotNull
  Path getAudioPath();

  @NotNull
  Path getIconPath();

  @NotNull
  String getDescription();

  int getPackFormat();
}
