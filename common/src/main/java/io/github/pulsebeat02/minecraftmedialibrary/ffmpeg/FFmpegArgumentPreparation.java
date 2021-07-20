package io.github.pulsebeat02.minecraftmedialibrary.ffmpeg;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public interface FFmpegArgumentPreparation {

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
  List<String> getArguments();

  void clearArguments();

  void execute();

  void executeWithLogging(@NotNull final Consumer<String> logger);

  void executeAsync();

  @NotNull
  CompletableFuture<Void> executeAsync(@NotNull final Executor executor);

  @NotNull
  CompletableFuture<Void> executeAsyncWithLogging(@NotNull final Consumer<String> logger);

  void onBeforeExecution();

  void onAfterExecution();
}
