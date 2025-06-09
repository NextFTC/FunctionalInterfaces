plugins {
    java
    `java-library`
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.sam.with.receiver)
    alias(libs.plugins.deployer)
}

group = "dev.nextftc"
version = property("version") as String

repositories {
    mavenCentral()
}

samWithReceiver {
    annotation("dev.nextftc.functionalInterfaces.ConfiguratorMarker")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 8
}

dependencies {
    dokkaPlugin(libs.dokka.java.plugin)
}

val dokkaJar = tasks.register<Jar>("dokkaJar") {
    dependsOn(tasks.named("dokkaGenerate"))
    from(dokka.basePublicationsDirectory.dir("html"))
    archiveClassifier = "html-docs"
}

deployer {
    projectInfo {
        name = "Functional Interfaces"
        description = "Action and Configurator functional interfaces that work in both Java and Kotlin"
        url = "https://github.com/NextFTC/FunctionalInterfaces"
        scm {
            fromGithub("NextFTC", "FunctionalInterfaces")
        }
        license("GNU General Public License, version 3", "https://www.gnu.org/licenses/gpl-3.0.html")
        developer("Davis Luxenberg", "davis.luxenberg@outlook.com", url = "https://github.com/BeepBot99")
    }

    signing {
        key = secret("MVN_GPG_KEY")
        password = secret("MVN_GPG_PASSWORD")
    }

    content {
        component {
            fromJava()
            javaSources()
            docs(dokkaJar)
        }
    }

    centralPortalSpec {
        auth {
            user = secret("SONATYPE_USERNAME")
            password = secret("SONATYPE_PASSWORD")
        }
        allowMavenCentralSync = (property("automaticMavenCentralSync") as String).toBoolean()
    }
}