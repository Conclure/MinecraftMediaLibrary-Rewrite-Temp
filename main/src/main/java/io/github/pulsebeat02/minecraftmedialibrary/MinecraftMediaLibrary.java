package io.github.pulsebeat02.minecraftmedialibrary;

import io.github.pulsebeat02.minecraftmedialibrary.analysis.Diagnostic;
import io.github.pulsebeat02.minecraftmedialibrary.analysis.SystemDiagnostics;
import io.github.pulsebeat02.minecraftmedialibrary.listener.RegistrationListener;
import io.github.pulsebeat02.minecraftmedialibrary.nms.PacketHandler;
import io.github.pulsebeat02.minecraftmedialibrary.reflect.NMSReflectionHandler;
import io.github.pulsebeat02.minecraftmedialibrary.reflect.TinyProtocol;
import io.github.pulsebeat02.minecraftmedialibrary.utility.PluginUsageTips;
import io.netty.channel.Channel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Stream;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class MinecraftMediaLibrary implements MediaLibraryCore {

  private final Plugin plugin;
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
  private boolean disabled;

  MinecraftMediaLibrary(
      @NotNull final Plugin plugin,
      @NotNull final Path libraryPath,
      @NotNull final Path dependencyPath,
      @NotNull final Path httpServerPath,
      @NotNull final Path vlcPath,
      @NotNull final Path imagePath,
      @NotNull final Path audioPath,
      @NotNull final Path videoPath,
      @NotNull final VideoPlayerOption option) {

    this.plugin = plugin;
    this.libraryPath = libraryPath;
    this.dependencyPath = dependencyPath;
    this.httpServerPath = httpServerPath;
    this.vlcPath = vlcPath;
    this.imagePath = imagePath;
    this.audioPath = audioPath;
    this.videoPath = videoPath;
    Stream.of(libraryPath, dependencyPath, httpServerPath, vlcPath, imagePath, audioPath, videoPath)
        .forEach(
            p -> {
              try {
                Files.createDirectories(p);
              } catch (IOException e) {
                plugin
                    .getLogger()
                    .log(
                        Level.SEVERE,
                        "[MinecraftMediaLibrary]: A severe I/O exception occurred while trying to create library folders!");
                e.printStackTrace();
              }
            });

    Logger.init(this);

    this.option = option;
    this.registrationListener = new RegistrationListener(this);
    this.diagnostics = new SystemDiagnostics(this);

    final Optional<PacketHandler> optional = NMSReflectionHandler.getNewPacketHandlerInstance();
    if (optional.isPresent()) {
      this.handler = optional.orElseThrow(AssertionError::new);
      new TinyProtocol(plugin) {
        @Override
        public Object onPacketOutAsync(
            final Player player, final Channel channel, final Object packet) {
          return handler.onPacketInterceptOut(player, packet);
        }

        @Override
        public Object onPacketInAsync(
            final Player player, final Channel channel, final Object packet) {
          return handler.onPacketInterceptIn(player, packet);
        }
      };
    }

    PluginUsageTips.sendWarningMessage();
    PluginUsageTips.sendPacketCompressionTip();
  }

  @Override
  public void shutdown() {
    Logger.info("Shutting Down");
    disabled = true;
    HandlerList.unregisterAll(registrationListener);
    Logger.info("Good Bye! :(");
  }

  @Override
  public @NotNull Plugin getPlugin() {
    return plugin;
  }

  @Override
  public @NotNull PacketHandler getHandler() {
    return handler;
  }

  @Override
  public @NotNull VideoPlayerOption getVideoPlayerAlgorithm() {
    return option;
  }

  @Override
  public @NotNull Path getLibraryPath() {
    return libraryPath;
  }

  @Override
  public @NotNull Path getHttpServerPath() {
    return httpServerPath;
  }

  @Override
  public @NotNull Path getDependencyPath() {
    return dependencyPath;
  }

  @Override
  public @NotNull Path getVlcPath() {
    return vlcPath;
  }

  @Override
  public @NotNull Path getImagePath() {
    return imagePath;
  }

  @Override
  public @NotNull Path getAudioPath() {
    return audioPath;
  }

  @Override
  public @NotNull Path getVideoPath() {
    return videoPath;
  }

  @Override
  public boolean isDisabled() {
    return disabled;
  }

  @Override
  public @NotNull Listener getRegistrationHandler() {
    return registrationListener;
  }

  @Override
  public void setRegistrationHandler(@NotNull final Listener listener) {
    this.registrationListener = listener;
  }

  @Override
  public void setVideoPlayerAlgorithm(@NotNull final VideoPlayerOption option) {
    this.option = option;
  }

  @Override
  public @NotNull Diagnostic getDiagnostics() {
    return diagnostics;
  }
}
