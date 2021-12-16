package com.example.clientapp;

import com.example.clientapp.model.Project;
import com.example.clientapp.services.ProjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.haulmont.rnd.data.config.EnableGraphQlDataRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableGraphQlDataRepositories(defaultEndpointUrl = "http://localhost:8080/graphql")
public class ClientAppApplication implements CommandLineRunner {

    private static final Log log = LogFactory.getLog(ClientAppApplication.class.getName());

    @Autowired
    private ProjectService service;

    public static void main(String[] args) {
        SpringApplication.run(ClientAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Spring Data GraphQL application...");
        for (Project project : service.findAllProjects()) {
            log.info(project);
        }

        for (Project project : service.findProjectByName("JUG.EKB Preparation")) {
            log.info(project);
        }

        for (Project project : service.findProjectByNameAndId("JUG.EKB Preparation", 2L)) {
            log.info(project);
        }

    }
}