package com.example.clientapp;

import com.example.clientapp.model.Project;
import org.haulmont.rnd.data.repository.GraphQlDataRepository;

import java.util.List;


public interface ProjectRepository extends GraphQlDataRepository<Project, Long> {

    List<Project> findAllProjects();

}