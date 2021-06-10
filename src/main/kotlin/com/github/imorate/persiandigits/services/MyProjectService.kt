package com.github.imorate.persiandigits.services

import com.github.imorate.persiandigits.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
