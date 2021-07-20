package io.github.pulsebeat02.minecraftmedialibrary.reflect;

import io.github.pulsebeat02.minecraftmedialibrary.Logger;
import io.github.pulsebeat02.minecraftmedialibrary.nms.PacketHandler;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

public class NMSReflectionHandler {

  public static final String VERSION;

  static {
    VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
  }

  @Nullable
  public static PacketHandler getNewPacketHandlerInstance() {
    try {
      Logger.info(String.format("Loading NMS Class for Version %s", VERSION));
      final Class<?> clazz =
          Class.forName(
              String.format(
                  "io.github.pulsebeat02.minecraftmedialibrary.nms.impl.%s.NMSMapPacketIntercepter",
                  VERSION));
      return (PacketHandler) clazz.getDeclaredConstructor().newInstance();
    } catch (final ClassNotFoundException
        | InstantiationException
        | IllegalAccessException
        | NoSuchMethodException
        | InvocationTargetException e) {
      Logger.error(
          String.format(
              "The Server Version you are using (%s) is not yet supported by MinecraftMediaLibrary! Shutting down due to the Fatal Error",
              VERSION));
      return null;
    }
  }
}
