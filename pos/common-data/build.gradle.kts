@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.kotlin.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

dependencies {
    api(libs.bundles.measure)
    api(libs.libphonenumber)
    api(libs.kotlinxCoroutines)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.bundles.room)
    kapt(libs.roomCompiler)


}
kapt {
    correctErrorTypes = true
}