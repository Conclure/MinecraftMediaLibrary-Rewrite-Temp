package io.github.pulsebeat02.minecraftmedialibrary.extraction;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public class AudioAttributes implements AudioConfiguration {

  private String codec;
  private int start;
  private int bitrate;
  private int channels;
  private int samplingRate;
  private int volume;

  public AudioAttributes(
      @NotNull final String codec,
      final int start,
      final int bitrate,
      final int channels,
      final int samplingRate,
      final int volume) {
    Preconditions.checkArgument(start >= 0, String.format("Invalid Start Time! (%d)", start));
    Preconditions.checkArgument(bitrate > 0, String.format("Invalid Bitrate! (%d)", bitrate));
    Preconditions.checkArgument(channels > 0, String.format("Invalid Channels! (%d)", channels));
    Preconditions.checkArgument(
        samplingRate > 0, String.format("Invalid Sampling Rate! (%d)", samplingRate));
    Preconditions.checkArgument(volume >= 0, String.format("Invalid Volume! (%d)", volume));
    this.start = start;
    this.codec = codec;
    this.bitrate = bitrate;
    this.channels = channels;
    this.samplingRate = samplingRate;
    this.volume = volume;
  }

  public AudioAttributes(
      @NotNull final String codec,
      final int bitrate,
      final int channels,
      final int samplingRate,
      final int volume) {
    this(codec, 0, bitrate, channels, samplingRate, volume);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof AudioAttributes)) {
      return false;
    }
    final AudioAttributes setting = (AudioAttributes) obj;
    return setting.getBitrate() == bitrate
        && setting.getChannels() == channels
        && setting.getSamplingRate() == samplingRate
        && setting.getVolume() == volume;
  }

  @Override
  public int getBitrate() {
    return bitrate;
  }

  @Override
  public void setBitrate(final int bitrate) {
    this.bitrate = bitrate;
  }

  @Override
  public int getChannels() {
    return channels;
  }

  @Override
  public void setChannels(final int channels) {
    this.channels = channels;
  }

  @Override
  public int getSamplingRate() {
    return samplingRate;
  }

  @Override
  public void setSamplingRate(final int samplingRate) {
    this.samplingRate = samplingRate;
  }

  @Override
  public String getCodec() {
    return codec;
  }

  @Override
  public void setCodec(@NotNull final String codec) {
    this.codec = codec;
  }

  @Override
  public int getVolume() {
    return volume;
  }

  @Override
  public void setVolume(final int volume) {
    this.volume = volume;
  }

  @Override
  public int getStartTime() {
    return start;
  }

  @Override
  public void setStartTime(final int time) {
    this.start = time;
  }
}
