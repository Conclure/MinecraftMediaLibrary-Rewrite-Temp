package io.github.pulsebeat02.minecraftmedialibrary.dependency;

import org.jetbrains.annotations.NotNull;

public enum DependencyInfo {

  VLCJ("uk{}co{}caprica", "vlcj", "4{}7{}1", Repositories.MAVEN),
  VLCJ_NATIVES("uk{}co{}caprica", "vlcj-natives", "4{}1{}0", Repositories.MAVEN),
  VLCJ_NATIVE_STREAMS("uk{}co{}caprica", "native-streams", "1{}0{}0", Repositories.MAVEN),
  YOUTUBE_DOWNLOADER(
      "com{}github{}sealedtx", "java-youtube-downloader", "3{}0{}1", Repositories.JITPACK),
  JAVE_CORE("ws{}schild", "jave-core", "3{}1{}1", Repositories.MAVEN),
  COMMONS_COMPRESSION("org{}apache{}commons", "commons-compress", "1{}20", Repositories.MAVEN),
  COMPRESSION("com{}github{}PulseBeat02", "jarchivelib", "master-SNAPSHOT", Repositories.JITPACK),
  XZ("org{}tukaani", "xz", "1{}0", Repositories.MAVEN),
  JNA("net{}java{}dev{}jna", "jna", "5{}8{}0", Repositories.MAVEN),
  JNA_PLATFORM("net{}java{}dev{}jna", "jna-platform", "5{}8{}0", Repositories.MAVEN),
  FAST_JSON("com{}alibaba", "fastjson", "1{}2{}76", Repositories.MAVEN),
  SPOTIFY("se{}michaelthelin{}spotify", "spotify-web-api-java", "6{}5{}4", Repositories.MAVEN),
  JAFFREE("com{}github{}kokorin{}jaffree", "jaffree", "2021{}05{}31", Repositories.MAVEN),
  JCODEC("org{}jcodec", "jcodec", "0{}2{}5", Repositories.MAVEN),
  CAFFEINE("com{}github{}ben-manes{}caffeine", "caffeine", "3{}0{}3", Repositories.MAVEN);

  private final String group;
  private final String artifact;
  private final String version;
  private final Repositories resolution;

  DependencyInfo(
      @NotNull final String group,
      @NotNull final String artifact,
      @NotNull final String version,
      @NotNull final Repositories resolution) {
    this.group = group.replaceAll("\\{}", ".");
    this.artifact = artifact.replaceAll("\\{}", ".");
    this.version = version.replaceAll("\\{}", ".");
    this.resolution = resolution;
  }

  public String getGroup() {
    return this.group;
  }

  public String getArtifact() {
    return this.artifact;
  }

  public String getVersion() {
    return this.version;
  }

  public Repositories getResolution() {
    return this.resolution;
  }
}
