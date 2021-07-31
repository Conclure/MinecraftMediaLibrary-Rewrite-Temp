package io.github.pulsebeat02.minecraftmedialibrary;

import io.github.pulsebeat02.minecraftmedialibrary.analysis.Diagnostic;
import io.github.pulsebeat02.minecraftmedialibrary.analysis.SystemDiagnostics;
import io.github.pulsebeat02.minecraftmedialibrary.listener.RegistrationListener;
import io.github.pulsebeat02.minecraftmedialibrary.nms.PacketHandler;
import io.github.pulsebeat02.minecraftmedialibrary.reflect.NMSReflectionHandler;
import io.github.pulsebeat02.minecraftmedialibrary.reflect.TinyProtocol;
import io.github.pulsebeat02.minecraftmedialibrary.sneaky.ThrowingConsumer;
import io.github.pulsebeat02.minecraftmedialibrary.utility.PluginUsageTips;
import io.netty.channel.Channel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.stream.Stream;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MinecraftMediaLibrary implements MediaLibraryCore {

  private final Plugin plugin;
  private final LibraryLoader loader;
  private final Diagnostic diagnostics;
  private final Path libraryPath;
  private final Path httpServerPath;
  private final Path dependencyPath;
  private final Path vlcPath;
  private final Path imagePath;
  private final Path audioPath;
  private final Path videoPath;

  private PacketHandler handler;
  private Listener registrationListener;
  private VideoPlayerOption option;
  private Path ffmpegExecutable;
  private boolean disabled;

  MinecraftMediaLibrary(@NotNull final Plugin plugin) {
    this(plugin, null, null, null, null, null, null, null, null, null);
  }

  MinecraftMediaLibrary(
      @NotNull final Plugin plugin,
      @Nullable final LibraryLoader loader,
      @Nullable final Path libraryPath,
      @Nullable final Path dependencyPath,
      @Nullable final Path httpServerPath,
      @Nullable final Path vlcPath,
      @Nullable final Path imagePath,
      @Nullable final Path audioPath,
      @Nullable final Path videoPath,
      @Nullable final VideoPlayerOption option) {

    this.plugin = plugin;
    this.libraryPath =
        libraryPath == null ? plugin.getDataFolder().toPath().resolve("mml") : libraryPath;
    this.dependencyPath = dependencyPath == null ? libraryPath.resolve("libs") : dependencyPath;
    this.httpServerPath = httpServerPath == null ? libraryPath.resolve("http") : httpServerPath;
    this.vlcPath = vlcPath == null ? dependencyPath.resolve("vlc") : vlcPath;
    this.imagePath = imagePath == null ? libraryPath.resolve("image") : imagePath;
    this.audioPath = audioPath == null ? libraryPath.resolve("audio") : audioPath;
    this.videoPath = videoPath == null ? libraryPath.resolve("video") : videoPath;
    this.option = option == null ? VideoPlayerOption.NOT_SPECIFIED : option;

    Stream.of(libraryPath, dependencyPath, httpServerPath, vlcPath, imagePath, audioPath, videoPath)
        .forEach(
            ThrowingConsumer.unchecked(
                Files::createDirectories,
                plugin.getLogger(),
                Level.SEVERE,
                "[MinecraftMediaLibrary]: A severe I/O exception occurred while trying to create library folders!"));

    Logger.init(this);

    this.registrationListener = new RegistrationListener(this);
    this.diagnostics = new SystemDiagnostics(this);

    final Optional<PacketHandler> optional = NMSReflectionHandler.getNewPacketHandlerInstance();
    if (optional.isPresent()) {
      this.handler = optional.orElseThrow(AssertionError::new);
      new TinyProtocol(plugin) {
        @Override
        public Object onPacketOutAsync(
            final Player player, final Channel channel, final Object packet) {
          return MinecraftMediaLibrary.this.handler.onPacketInterceptOut(player, packet);
        }

        @Override
        public Object onPacketInAsync(
            final Player player, final Channel channel, final Object packet) {
          return MinecraftMediaLibrary.this.handler.onPacketInterceptIn(player, packet);
        }
      };
    }

    this.loader = loader == null ? new DependencyLoader(this) : loader;
    try {
      this.loader.start();
    } catch (final ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }

    PluginUsageTips.sendWarningMessage();
    PluginUsageTips.sendPacketCompressionTip();
  }

  @Override
  public void shutdown() {
    Logger.info("Shutting Down");
    this.disabled = true;
    HandlerList.unregisterAll(this.registrationListener);
    Logger.info("Good Bye! :(");
  }

  @Override
  public @NotNull Plugin getPlugin() {
    return this.plugin;
  }

  @Override
  public @NotNull PacketHandler getHandler() {
    return this.handler;
  }

  @Override
  public @NotNull VideoPlayerOption getVideoPlayerAlgorithm() {
    return this.option;
  }

  @Override
  public void setVideoPlayerAlgorithm(@NotNull final VideoPlayerOption option) {
    this.option = option;
  }

  @Override
  public @NotNull Path getLibraryPath() {
    return this.libraryPath;
  }

  @Override
  public @NotNull Path getHttpServerPath() {
    return this.httpServerPath;
  }

  @Override
  public @NotNull Path getDependencyPath() {
    return this.dependencyPath;
  }

  @Override
  public @NotNull Path getVlcPath() {
    return this.vlcPath;
  }

  @Override
  public @NotNull Path getImagePath() {
    return this.imagePath;
  }

  @Override
  public @NotNull Path getAudioPath() {
    return this.audioPath;
  }

  @Override
  public @NotNull Path getVideoPath() {
    return this.videoPath;
  }

  @Override
  public @NotNull Path getFFmpegPath() {
    return this.ffmpegExecutable;
  }

  @Override
  public void setFFmpegPath(@NotNull final Path path) {
    this.ffmpegExecutable = path;
  }

  @Override
  public boolean isDisabled() {
    return this.disabled;
  }

  @Override
  public @NotNull Listener getRegistrationHandler() {
    return this.registrationListener;
  }

  @Override
  public void setRegistrationHandler(@NotNull final Listener listener) {
    this.registrationListener = listener;
  }

  @Override
  public @NotNull LibraryLoader getLibraryLoader() {
    return this.loader;
  }

  @Override
  public @NotNull Diagnostic getDiagnostics() {
    return this.diagnostics;
  }
}
