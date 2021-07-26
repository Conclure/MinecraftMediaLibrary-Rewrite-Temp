package io.github.pulsebeat02.minecraftmedialibrary.resourcepack;

import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

// https://gist.github.com/ItsPepperpot/c927e635fb9609cfa2a97c93327f33f2 for example

public interface PackWrapper {

  void wrap() throws IOException;

  void onPackStartWrap();

  void onPackFinishWrap();

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

  @NotNull
  String getSoundJson();

  @NotNull
  String getPackMcmeta();

  int getPackFormat();
}
