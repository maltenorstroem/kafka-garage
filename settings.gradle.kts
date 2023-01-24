rootProject.name = "kafka-lab"

//
// Import Sub Projects
//
apply(from = "subprojects.settings.gradle.kts")

//
// Initialize Sub Projects
//
run {
    for (project in rootProject.children) {
        project.projectDir = file("subprojects/${project.name}")
        project.buildFileName = "${project.name}.gradle.kts"
    }
}
