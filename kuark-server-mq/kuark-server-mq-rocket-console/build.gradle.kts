dependencies {
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":kuark-server-mq:kuark-server-mq-rocket"))
    implementation(fileTree("dir" to "libs", "include" to arrayOf("rocketmq-console-ng-2.0.0.jar")))
}