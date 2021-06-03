import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val kotlin_version: String by project
val logback_version: String by project
val spring_boot_version: String by project
val slf4j_version: String by project
val spring_cloud_version: String by project

plugins {
//    application
//    java
    id("org.springframework.boot") version "2.4.6"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.spring") version "1.5.10"
}

/* 插件的配置需要在buildscript元素中 */
buildscript {
    /* 插件仓库 */
//    repositories {
//        maven { url = uri("https://plugins.gradle.org/m2/") }
//    }
    /* 插件依赖 */
//    dependencies {
//        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.2.5.RELEASE")
//        classpath("io.spring.gradle:dependency-management-plugin:1.0.9.RELEASE")
//    }
}


allprojects {
    group = "io.kuark"
    version = "1.0.0-SNAPSHOT"

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://kotlin.bintray.com/ktor") }
        maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers") }
        mavenLocal()
        jcenter()
    }

    apply {
        plugin("kotlin")
        plugin("idea")
//        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
//        plugin("maven-publish")
    }
}

subprojects {

    java.sourceCompatibility = JavaVersion.VERSION_11
    java.targetCompatibility = JavaVersion.VERSION_11

    ext {

    }

    configurations {
        // 所有需要忽略的包定义在此
//        all*.exclude group: 'commons-httpclient'
//        all*.exclude group: 'commons-logging'
//        all*.exclude group: 'commons-beanutils', module: 'commons-beanutils'
    }

    // 显示当前项目下所有用于 compile 的 jar.
//    tasks.listJars(description: 'Display all compile jars.') << {
//       configurations.compile.each { File file -> println file.name }
//    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<BootJar> {
        enabled = false
    }

    tasks.withType<Jar> {
        enabled = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    kotlin.sourceSets["main"].kotlin.srcDirs("src")
    kotlin.sourceSets["test"].kotlin.srcDirs("test")

    sourceSets["main"].resources.srcDirs("resources")
    sourceSets["test"].resources.srcDirs("testresources")

    dependencies {
        // kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.9")
    }

    dependencyManagement {
//        imports {
//            mavenBom("org.springframework.boot:spring-boot-dependencies:$spring_boot_version")
//        }
        dependencies {


            // springboot
            dependency("org.springframework.boot:spring-boot-starter-aop:$spring_boot_version")
            dependency("org.springframework.boot:spring-boot-starter-web:$spring_boot_version")
            dependency("org.springframework.boot:spring-boot-starter-webflux:$spring_boot_version")
            dependency("org.springframework.boot:spring-boot-starter-actuator:$spring_boot_version")
            dependency("org.springframework.boot:spring-boot-starter-data-redis-reactive:$spring_boot_version") // 限流用
            dependency("de.codecentric:spring-boot-admin-starter-server:2.3.0")
            dependency("org.springframework.boot:spring-boot-starter-test:$spring_boot_version") {
                exclude("org.junit.vintage:junit-vintage-engine")
            }


            // spring cloud
            dependency("org.springframework.cloud:spring-cloud-commons:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-context:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-config-server:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-config-client:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-config:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-openfeign:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-gateway:$spring_cloud_version")
            dependency("org.springframework.cloud:spring-cloud-starter-bus-amqp:2.2.3.RELEASE")

            // seata
            dependency("io.seata:seata-all:1.3.0")
            dependency("com.alibaba.cloud:spring-cloud-alibaba-seata:2.2.0.RELEASE")
        }
    }

}

