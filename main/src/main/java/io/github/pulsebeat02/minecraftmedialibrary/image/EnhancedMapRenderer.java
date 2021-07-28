package io.github.pulsebeat02.minecraftmedialibrary.image;

import com.google.common.base.Preconditions;
import java.awt.image.BufferedImage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

public class EnhancedMapRenderer implements MapRenderer {

  private final BufferedImage[][] images;
  private final MapView[][] maps;

  public EnhancedMapRenderer(@NotNull final BufferedImage[][] images, final int[][] maps) {
    final int length = images.length;
    final int width = images[0].length;
    Preconditions.checkArgument(
        length == maps.length && width == maps[0].length,
        "Length and Width for 2D arrays passed in are not the same!");
    this.images = images;
    this.maps = new MapView[length][width];
    for (int i = 0; i < maps.length; i++) {
      for (int j = 0; j < maps[i].length; j++) {
        this.maps[i][j] = Bukkit.getMap(maps[i][j]);
      }
    }
  }

  @Override
  public void drawMap() {
    for (int i = 0; i < this.maps.length; i++) {
      for (int j = 0; j < this.maps[i].length; j++) {
        final MapView view = this.maps[i][j];
        view.getRenderers().clear();
        final int x = i;
        final int y = j;
        view.addRenderer(
            new org.bukkit.map.MapRenderer() {
              @Override
              public void render(
                  @NotNull final MapView map,
                  @NotNull final MapCanvas canvas,
                  @NotNull final Player player) {
                canvas.drawImage(0, 0, EnhancedMapRenderer.this.images[x][y]);
              }
            });
      }
    }
  }
}
