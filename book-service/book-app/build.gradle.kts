plugins {
    id("com.airwallex.grpc-spring") version "1.1.3"
}

dependencies {
    implementation(project(":book-api"))
    runtimeOnly("org.springframework.boot:spring-boot-starter-webflux")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    runtimeOnly("org.springframework.cloud:spring-cloud-sleuth-zipkin")
}
