description = "Concrete implementation module for MinecraftMediaLibrary"

dependencies {

    compileOnlyApi("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    implementation("io.github.slimjar:slimjar:1.2.0")
    compileOnly("uk.co.caprica:vlcj:4.7.1")
    compileOnly("uk.co.caprica:vlcj-natives:4.1.0")
    compileOnly("uk.co.caprica:native-streams:1.0.0")
    compileOnly("com.github.sealedtx:java-youtube-downloader:3.0.1")
    compileOnly("ws.schild:jave-core:3.1.1")
    compileOnly("io.netty:netty-all:5.0.0.Alpha2")
    compileOnly("com.mojang:authlib:1.5.25")
    compileOnly("org.jetbrains:annotations:21.0.1")
    compileOnly("com.github.pulsebeat02:jarchivelib:master-SNAPSHOT")
    compileOnly("org.tukaani:xz:1.0")
    compileOnly("com.alibaba:fastjson:1.2.76")
    compileOnly("net.java.dev.jna:jna:5.8.0")
    compileOnly("net.java.dev.jna:jna-platform:5.8.0")
    compileOnly("se.michaelthelin.spotify:spotify-web-api-java:6.5.4")
    compileOnly("com.github.kokorin.jaffree:jaffree:2021.05.31")
    compileOnly("com.google.guava:guava:30.1.1-jre")
    compileOnly("org.apache.commons:commons-lang3:3.12.0")
    compileOnly("org.jcodec:jcodec:0.2.5")
    compileOnly("net.dv8tion:JDA:4.3.0_277")
    compileOnly("com.github.ben-manes.caffeine:caffeine:3.0.3")

    api(project(":api"))
    api(project(":v1_16_R3"))

}