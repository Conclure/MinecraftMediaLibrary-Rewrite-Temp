package io.github.pulsebeat02.minecraftmedialibrary.ffmpeg;

import io.github.pulsebeat02.minecraftmedialibrary.MediaLibraryCore;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface FFmpegArgumentPreparation extends EnhancedExecution {

  @NotNull
  FFmpegArgumentPreparation addArgument(@NotNull final String arg);

  @NotNull
  FFmpegArgumentPreparation addArguments(@NotNull final String key, @NotNull final String value);

  @NotNull
  FFmpegArgumentPreparation addArgument(@NotNull final String arg, final int index);

  @NotNull
  FFmpegArgumentPreparation addArguments(
      @NotNull final String key, @NotNull final String value, final int index);

  @NotNull
  FFmpegArgumentPreparation removeArgument(@NotNull final String arg);

  @NotNull
  FFmpegArgumentPreparation removeArgument(final int index);

  @NotNull
  FFmpegArgumentPreparation addMultipleArguments(@NotNull final String[] arguments);

  @NotNull
  FFmpegArgumentPreparation addMultipleArguments(@NotNull final Collection<String> arguments);

  @NotNull
  MediaLibraryCore getCore();

  void clearArguments();

  void onBeforeExecution();

  void onAfterExecution();
}
