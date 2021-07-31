package io.github.pulsebeat02.minecraftmedialibrary;

import java.lang.ref.WeakReference;
import java.nio.file.Path;
import java.util.Map;
import java.util.WeakHashMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MediaLibraryProvider {

  private static final Map<Plugin, WeakReference<MediaLibraryCore>> instantiated;

  static {
    instantiated = new WeakHashMap<>();
  }

  private MediaLibraryProvider() {}

  public static MediaLibraryCore create(final Plugin plugin) {
    return checkValidity(plugin, new MinecraftMediaLibrary(plugin));
  }

  public static MediaLibraryCore create(
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
    return checkValidity(
        plugin,
        new MinecraftMediaLibrary(
            plugin,
            loader,
            libraryPath,
            dependencyPath,
            httpServerPath,
            vlcPath,
            imagePath,
            audioPath,
            videoPath,
            option));
  }

  public static boolean getLibraryStatus(@NotNull final Plugin plugin) {
    return instantiated.containsKey(plugin);
  }

  public static <T extends JavaPlugin> boolean getLibraryStatus(@NotNull final Class<T> clazz) {
    return instantiated.containsKey(JavaPlugin.getPlugin(clazz));
  }

  public static MediaLibraryCore getLibraryInstance(@NotNull final Plugin plugin) {
    return instantiated.get(plugin).get();
  }

  private static MediaLibraryCore checkValidity(
      final Plugin plugin, final MediaLibraryCore library) {
    final String id = plugin.getName();
    if (instantiated.containsKey(plugin)) {
      return instantiated.get(plugin).get();
    }
    instantiated.put(plugin, new WeakReference<>(library));
    return library;
  }
}
