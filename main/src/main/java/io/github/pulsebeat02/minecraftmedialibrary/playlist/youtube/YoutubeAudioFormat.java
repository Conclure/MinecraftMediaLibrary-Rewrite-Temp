package io.github.pulsebeat02.minecraftmedialibrary.playlist.youtube;

import org.jetbrains.annotations.NotNull;

public final class YoutubeAudioFormat implements AudioFormat {

  private final com.github.kiulian.downloader.model.videos.formats.AudioFormat format;

  YoutubeAudioFormat(
      @NotNull final com.github.kiulian.downloader.model.videos.formats.AudioFormat format) {
    this.format = format;
  }

  @Override
  public int getBitrate() {
    return this.format.averageBitrate();
  }

  @Override
  public int getSamplingRate() {
    return this.format.audioSampleRate();
  }

  @Override
  public @NotNull AudioQuality getAudioQuality() {
    return AudioQuality.ofKey(this.format.audioQuality().name());
  }
}
