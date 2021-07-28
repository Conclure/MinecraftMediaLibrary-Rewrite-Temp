package io.github.pulsebeat02.minecraftmedialibrary.callback;

import org.jetbrains.annotations.NotNull;

public interface ChatCallbackDispatcher extends Callback {

  @NotNull
  String getChatCharacter();
}
