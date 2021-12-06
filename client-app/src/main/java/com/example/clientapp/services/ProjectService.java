package com.example.clientapp.services;

import com.example.clientapp.ProjectRepository;
import com.example.clientapp.model.Project;
import org.haulmont.rnd.data.config.GraphQlQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAllProjects();
    }

    public List<Project> findProjectByName(String name) {
        return projectRepository.findProjectByName(name);
    }

    public List<Project> findProjectByNameAndId(String name, Long id) {
        return projectRepository.findProjectsByAllData(name, id);
    }
}