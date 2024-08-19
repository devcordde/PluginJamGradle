import io.papermc.paperweight.userdev.ReobfArtifactConfiguration

plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("io.papermc.paperweight.userdev") version "1.7.2"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://eldonexus.de/repository/maven-releases/")
}

group = "club.devcord.gamejam"
version = "1.0.0"
description = "gamejam"

dependencies {
    testImplementation("junit:junit:4.13.2")
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    compileOnly("de.chojo.pluginjam:plugin-paper:1.0.3")
}

paperweight {
    reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    runServer {
        minecraftVersion("1.21.1")
    }
}

bukkit {
    name = "GameJamPlugin"
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "club.devcord.gamejam.JamPlugin"
    apiVersion = "1.21"
}
