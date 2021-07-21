package io.github.pulsebeat02.minecraftmedialibrary.listener;

import io.github.pulsebeat02.minecraftmedialibrary.Logger;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public final class ForcefulResourcepackListener implements Listener {

  private final Plugin plugin;
  private final Set<UUID> uuids;
  private final String url;
  private final byte[] hash;

  public ForcefulResourcepackListener(
          @NotNull final Plugin plugin,
          @NotNull final Set<UUID> uuids,
          @NotNull final String url,
          final byte @NotNull [] hash) {
    this.plugin = plugin;
    this.uuids = uuids;
    this.url = url;
    this.hash = hash;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
    sendResourcepack();
  }

  private void sendResourcepack() {
    for (final UUID uuid : uuids) {
      final Player player = Bukkit.getPlayer(uuid);
      if (player != null) {
        player.setResourcePack(url, hash);
      } else {
        Logger.info(String.format("Could not set the resourcepack for %s! (%s)", uuid, url));
      }
    }
  }

  public void start(@NotNull final Plugin plugin) {
    new BukkitRunnable() {
      @Override
      public void run() {
        if (!uuids.isEmpty()) {
          Logger.info(
              String.format("Could not force all players to load resourcepack! (%s)", uuids));
          PlayerResourcePackStatusEvent.getHandlerList().unregister(plugin);
        }
      }
    }.runTaskLater(plugin, 6000L);
  }

  @EventHandler
  public void onResourcepackStatus(final PlayerResourcePackStatusEvent event) {
    final Player player = event.getPlayer();
    final UUID uuid = player.getUniqueId();
    if (!uuids.contains(uuid)) {
      return;
    }
    switch (event.getStatus()) {
      case FAILED_DOWNLOAD:
        player.setResourcePack(url, hash);
        break;
      case DECLINED:
      case SUCCESSFULLY_LOADED:
      case ACCEPTED:
        uuids.remove(uuid);
        if (uuids.isEmpty()) {
          PlayerResourcePackStatusEvent.getHandlerList().unregister(this);
        }
        break;
    }
  }
}
