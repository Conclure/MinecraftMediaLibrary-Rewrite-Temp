package io.github.pulsebeat02.minecraftmedialibrary.utility;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {

  static <T, E extends Throwable> Consumer<T> unchecked(
      @NotNull final ThrowingConsumer<T, E> consumer,
      @NotNull final Logger logger,
      @NotNull final Level level,
      @NotNull final String message) {
    return (t) -> {
      try {
        consumer.accept(t);
      } catch (final Throwable e) {
        logger.log(level, message);
        e.printStackTrace();
      }
    };
  }

  static <T, E extends Throwable> Consumer<T> unchecked(
      @NotNull final ThrowingConsumer<T, E> consumer, @NotNull final String message) {
    return (t) -> {
      try {
        consumer.accept(t);
      } catch (final Throwable e) {
        io.github.pulsebeat02.minecraftmedialibrary.Logger.info(message);
        throw new RuntimeException(e);
      }
    };
  }

  void accept(final T t) throws E;
}
