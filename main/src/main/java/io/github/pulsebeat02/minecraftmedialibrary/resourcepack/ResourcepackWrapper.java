package io.github.pulsebeat02.minecraftmedialibrary.resourcepack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.pulsebeat02.minecraftmedialibrary.Logger;
import io.github.pulsebeat02.minecraftmedialibrary.json.GsonProvider;
import io.github.pulsebeat02.minecraftmedialibrary.throwable.InvalidPackFormatException;
import io.github.pulsebeat02.minecraftmedialibrary.throwable.InvalidPackResourceException;
import io.github.pulsebeat02.minecraftmedialibrary.utility.FileUtils;
import io.github.pulsebeat02.minecraftmedialibrary.utility.PathUtils;
import io.github.pulsebeat02.minecraftmedialibrary.utility.ResourcepackUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResourcepackWrapper implements PackWrapper {

  private final Path path;
  private final Path audio;
  private final Path icon;
  private final String soundKey;
  private final String description;
  private final int format;

  public ResourcepackWrapper(
      @NotNull final String name,
      @NotNull final Path path,
      @NotNull final Path audio,
      @Nullable final Path icon,
      @NotNull final String description,
      final int format) {
    this.soundKey = name;
    this.path = path;
    this.audio = audio;
    this.icon = icon;
    this.description = description;
    this.format = format;
    if (!ResourcepackUtils.validatePackFormat(format)) {
      throw new InvalidPackFormatException(format);
    }
    if (icon != null && !ResourcepackUtils.validateResourcepackIcon(icon)) {
      throw new InvalidPackResourceException(
          String.format("Invalid Pack Icon! Must be PNG (%s)", PathUtils.getName(icon)));
    }
    Logger.info(String.format("New Resourcepack (%s) was Initialized", path));
  }

  @Override
  public void wrap() throws IOException {
    onPackStartWrap();

    Logger.error("Wrapping the Resourcepack");
    try (final ZipOutputStream out =
        new ZipOutputStream(new FileOutputStream(this.path.toFile()))) {

      FileUtils.createFileIfNotExists(this.path);

      final ZipEntry config = new ZipEntry("pack.mcmeta");
      out.putNextEntry(config);
      out.write(getPackMcmeta().getBytes());
      out.closeEntry();

      final ZipEntry sound = new ZipEntry("assets/minecraft/sounds.json");
      out.putNextEntry(sound);
      out.write(getSoundJson().getBytes());
      out.closeEntry();

      final ZipEntry soundFile = new ZipEntry("assets/minecraft/sounds/audio.ogg");
      out.putNextEntry(soundFile);
      out.write(Files.readAllBytes(Paths.get(this.audio.toString())));
      out.closeEntry();

      if (this.icon != null && Files.exists(this.icon)) {
        final ZipEntry iconFile = new ZipEntry("pack.png");
        out.putNextEntry(iconFile);
        out.write(Files.readAllBytes(Paths.get(this.icon.toString())));
        out.closeEntry();
      }
    }
    Logger.info("Finished Wrapping Resourcepack");

    onPackFinishWrap();
  }

  @Override
  public void onPackStartWrap() {}

  @Override
  public void onPackFinishWrap() {}

  @Override
  public @NotNull String getSoundName() {
    return this.soundKey;
  }

  @Override
  public @NotNull Path getResourcepackFilePath() {
    return this.path;
  }

  @Override
  public @NotNull Path getAudioPath() {
    return this.audio;
  }

  @Override
  public @NotNull Path getIconPath() {
    return this.icon;
  }

  @Override
  public @NotNull String getDescription() {
    return this.description;
  }

  @Override
  public @NotNull String getSoundJson() {
    final JsonObject category = new JsonObject();
    final JsonObject type = new JsonObject();
    final JsonArray sounds = new JsonArray();
    sounds.add("audio");
    category.add("sounds", sounds);
    type.add(this.soundKey, category);
    return GsonProvider.getPretty().toJson(type);
  }

  @Override
  public @NotNull String getPackMcmeta() {
    final JsonObject mcmeta = new JsonObject();
    final JsonObject pack = new JsonObject();
    pack.addProperty("pack_format", this.format);
    pack.addProperty("description", this.description);
    mcmeta.add("pack", pack);
    return GsonProvider.getSimple().toJson(mcmeta);
  }

  @Override
  public int getPackFormat() {
    return this.format;
  }
}
