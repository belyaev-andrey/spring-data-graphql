package org.haulmont.rnd.serverapp.controllers;

import org.haulmont.rnd.serverapp.entities.Project;
import org.haulmont.rnd.serverapp.repositories.ProjectRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    private ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @QueryMapping("findProjectByName")
    public List<Project> findByName(@Argument("name") String name) {
        return projectRepository.findByName(name);
    }

    @QueryMapping("findAllProjects")
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @QueryMapping("findProjectById")
    public Optional<Project> findById(@Argument Long aLong) {
        return projectRepository.findById(aLong);
    }

    @QueryMapping("findByNameAndId")
    public List<Project> findByNameAndId(@Argument String name, @Argument Long id) {
        return projectRepository.findByNameAndId(name, id);
    }
}