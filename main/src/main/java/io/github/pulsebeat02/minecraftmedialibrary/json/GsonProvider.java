package io.github.pulsebeat02.minecraftmedialibrary.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {

  private static final Gson GSON;

  static {
    GSON = new GsonBuilder().setPrettyPrinting().create();
  }

  public static Gson getGson() {
    return GSON;
  }
}
