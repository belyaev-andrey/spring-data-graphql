package com.example.clientapp.services;

import com.example.clientapp.ProjectRepository;
import com.example.clientapp.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private ProjectRepository projectRepository;

    public PersonService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAllProjects();
    }
}