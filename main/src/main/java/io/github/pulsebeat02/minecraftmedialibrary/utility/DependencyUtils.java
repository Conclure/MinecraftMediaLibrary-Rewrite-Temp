package io.github.pulsebeat02.minecraftmedialibrary.utility;

/*............................................................................................
. Copyright © 2021 Brandon Li                                                               .
.                                                                                           .
. Permission is hereby granted, free of charge, to any person obtaining a copy of this      .
. software and associated documentation files (the “Software”), to deal in the Software     .
. without restriction, including without limitation the rights to use, copy, modify, merge, .
. publish, distribute, sublicense, and/or sell copies of the Software, and to permit        .
. persons to whom the Software is furnished to do so, subject to the following conditions:  .
.                                                                                           .
. The above copyright notice and this permission notice shall be included in all copies     .
. or substantial portions of the Software.                                                  .
.                                                                                           .
. THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,                           .
.  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF                       .
.   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND                                   .
.   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS                     .
.   BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN                      .
.   ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN                       .
.   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE                        .
.   SOFTWARE.                                                                               .
............................................................................................*/

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.github.pulsebeat02.minecraftmedialibrary.dependency.DependencyInfo;
import io.github.pulsebeat02.minecraftmedialibrary.dependency.Repositories;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.LongConsumer;
import org.jetbrains.annotations.NotNull;

/**
 * Special dependency utilities used throughout the library and also open to users. Used for easier
 * dependency management.
 */
public final class DependencyUtils {

  private DependencyUtils() {}

  /**
   * Download Maven Dependency.
   *
   * @param dependency the dependency
   * @param parent the parent
   * @return the file
   * @throws IOException the io exception
   */
  @NotNull
  public static Path downloadMavenDependency(
      @NotNull final DependencyInfo dependency, @NotNull final String parent) throws IOException {
    return downloadFile(dependency, getRepoUrl(dependency), parent);
  }

  /**
   * Download Jitpack Dependency.
   *
   * @param dependency the dependency
   * @param parent the parent
   * @return the file
   * @throws IOException the io exception
   */
  @NotNull
  public static Path downloadJitpackDependency(
      @NotNull final DependencyInfo dependency, @NotNull final String parent) throws IOException {
    return downloadFile(dependency, getRepoUrl(dependency), parent);
  }

  /**
   * Gets Dependency Repository URL of Maven/Jitpack Dependency.
   *
   * @param dependency the dependency
   * @return the jitpack url
   */
  @NotNull
  public static String getRepoUrl(@NotNull final DependencyInfo dependency) {
    return getDependencyUrl(dependency);
  }

  /**
   * Constructs dependency URL of MavenDependency.
   *
   * @param dependency the dependency
   * @return the dependency url
   */
  @NotNull
  public static String getDependencyUrl(@NotNull final DependencyInfo dependency) {
    return String.format(
        "%s/%s/%s/%s/",
        dependency.getResolution().getUrl(),
        dependency.getGroup().replaceAll("\\.", "/"),
        dependency.getArtifact(),
        dependency.getVersion());
  }

  /**
   * Constructs dependency URL directly based on parameters.
   *
   * @param groupId the group id
   * @param artifactId the artifact id
   * @param version the version
   * @param base the base
   * @return the dependency url
   */
  @NotNull
  public static String getDependencyUrl(
      @NotNull final String groupId,
      @NotNull final String artifactId,
      @NotNull final String version,
      @NotNull final String base) {
    Preconditions.checkArgument(!groupId.isEmpty(), "Group ID cannot be empty!");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(groupId), "Group ID cannot be empty!");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(artifactId), "Artifact ID cannot be empty!");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(version), "Version cannot be empty!");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(base), "Base URL cannot be empty or null!");
    return String.format("%s/%s/%s/%s/", base, groupId.replaceAll("\\.", "/"), artifactId, version);
  }

  /**
   * Download dependency file.
   *
   * @param dependency the dependency
   * @param link the link
   * @param parent the parent
   * @return the file
   * @throws IOException the io exception
   */
  @NotNull
  public static Path downloadFile(
      @NotNull final DependencyInfo dependency,
      @NotNull final String link,
      @NotNull final String parent)
      throws IOException {
    final String file =
        String.format("%s-%s.jar", dependency.getArtifact(), dependency.getVersion());
    final String url = link + file;
    return downloadFile(Paths.get(String.format("%s/%s", parent, file)), url);
  }

  /**
   * Download dependency file with consumer.
   *
   * @param dependency the dependency
   * @param link the link
   * @param parent the parent
   * @param consumer the consumer
   * @return the file
   * @throws IOException the io exception
   */
  @NotNull
  public static Path downloadFile(
      @NotNull final DependencyInfo dependency,
      @NotNull final String link,
      @NotNull final String parent,
      @NotNull final LongConsumer consumer)
      throws IOException {
    final String file =
        String.format("%s-%s.jar", dependency.getArtifact(), dependency.getVersion());
    final String url = link + file;
    return downloadFile(Paths.get(String.format("%s/%s", parent, file)), url, consumer);
  }

  /**
   * Download dependency file.
   *
   * @param groupId the group id
   * @param artifactId the artifact id
   * @param version the version
   * @param parent the parent
   * @param resolution the resolution
   * @return the file
   * @throws IOException the io exception
   */
  @NotNull
  public static Path downloadFile(
      @NotNull final String groupId,
      @NotNull final String artifactId,
      @NotNull final String version,
      @NotNull final String parent,
      @NotNull final Repositories resolution)
      throws IOException {
    final String file = String.format("%s-%s.jar", artifactId, version);
    final String url = getDependencyUrl(groupId, artifactId, version, resolution.getUrl()) + file;
    return downloadFile(Paths.get(parent, file), url);
  }

  /**
   * Download dependency file with consumer.
   *
   * @param groupId the group id
   * @param artifactId the artifact id
   * @param version the version
   * @param parent the parent
   * @param consumer the consumer
   * @param resolution the resolution
   * @return the file
   * @throws IOException the io exception
   */
  @NotNull
  public static Path downloadFile(
      @NotNull final String groupId,
      @NotNull final String artifactId,
      @NotNull final String version,
      @NotNull final String parent,
      @NotNull final Repositories resolution,
      @NotNull final LongConsumer consumer)
      throws IOException {
    final String file = String.format("%s-%s.jar", artifactId, version);
    final String url = getDependencyUrl(groupId, artifactId, version, resolution.getUrl()) + file;
    return downloadFile(Paths.get(String.format("%s/%s", parent, file)), url, consumer);
  }

  @NotNull
  public static Path downloadFile(@NotNull final Path p, @NotNull final String url)
      throws IOException {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "URL cannot be empty or null!");
    try (final ReadableByteChannel readableByteChannel =
            Channels.newChannel(new URL(url).openStream());
        final FileChannel channel = new FileOutputStream(p.toFile()).getChannel()) {
      channel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }
    return p;
  }

  @NotNull
  public static Path downloadFile(
      @NotNull final Path p, @NotNull final String url, @NotNull final LongConsumer progress)
      throws IOException {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "URL cannot be empty or null!");
    try (final BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        final FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(p))) {
      final byte[] dataBuffer = new byte[8192];
      int bytesRead;
      while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        progress.accept(bytesRead);
        fileOutputStream.write(dataBuffer, 0, bytesRead);
      }
    }
    return p;
  }

  public static long getFileSize(@NotNull final String url) throws IOException {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "URL cannot be empty or null!");
    final URL download = new URL(url);
    final HttpURLConnection conn = (HttpURLConnection) download.openConnection();
    conn.setRequestMethod("HEAD");
    return conn.getContentLengthLong();
  }
}
