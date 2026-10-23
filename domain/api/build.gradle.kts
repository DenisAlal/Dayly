plugins {
    alias(libs.plugins.kotlin.jvm)
    id("java-library")
}

// Чистый Kotlin: без Android. Конфигурация api доступна благодаря java-library.
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    // Модели пока без зависимостей. Когда в контракте появится Flow, сюда добавится:
    // api("org.jetbrains.kotlinx:kotlinx-coroutines-core:...")
}
