package io.github.pulsebeat02.minecraftmedialibrary.callback;

import org.jetbrains.annotations.NotNull;

public interface ChatCallback extends FrameCallback {

  @NotNull
  String getChatCharacter();
}
