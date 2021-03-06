package io.github.pulsebeat02.minecraftmedialibrary.resourcepack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.pulsebeat02.minecraftmedialibrary.json.GsonProvider;
import io.github.pulsebeat02.minecraftmedialibrary.sneaky.ThrowingBiConsumer;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResourcepackSoundWrapper extends ResourcepackWrapper implements SoundPackWrapper {

  private final Map<String, Path> sounds;

  {
    this.sounds = new HashMap<>();
  }

  public ResourcepackSoundWrapper(
      @NotNull final Path path, @NotNull final String description, final int format) {
    super(path, description, format);
  }

  public ResourcepackSoundWrapper(
      @NotNull final Path path,
      @NotNull final String description,
      final int format,
      @Nullable final Path icon) {
    super(path, description, format, icon);
  }

  @Override
  public void wrap() throws IOException {

    onPackStartWrap();

    this.sounds.forEach(
        ThrowingBiConsumer.sneaky(
            (key, value) -> addFile("assets/minecraft/sounds/" + key + ".ogg", value)));
    addFile("assets/minecraft/sounds.json", createSoundJson());

    internalWrap();

    onPackFinishWrap();
  }

  @Override
  public void addSound(@NotNull final String key, @NotNull final Path path) {
    this.sounds.put(key, path);
  }

  @Override
  public void removeSound(@NotNull final String key) {
    this.sounds.remove(key);
  }

  @Override
  public byte[] createSoundJson() {
    final JsonObject category = new JsonObject();
    final JsonObject type = new JsonObject();
    final JsonArray sounds = new JsonArray();
    this.sounds.forEach(
        (key, value) -> {
          sounds.add(key);
          type.add("mml", category);
        });
    category.add("sounds", sounds);
    return GsonProvider.getPretty().toJson(type).getBytes(StandardCharsets.UTF_8);
  }

  @Override
  public @NotNull Map<String, Path> listSounds() {
    return this.sounds;
  }
}
