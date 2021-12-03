package com.example.clientapp;

import com.example.clientapp.model.Project;
import com.example.clientapp.services.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.haulmont.rnd.data.config.EnableGraphQlDataRepositories;
import org.haulmont.rnd.data.query.GraphQlQueryLookupStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@EnableGraphQlDataRepositories
public class ClientAppApplication implements CommandLineRunner {

    private static final Log log = LogFactory.getLog(ClientAppApplication.class.getName());

    @Autowired
    private PersonService service;

    public static void main(String[] args) {
        SpringApplication.run(ClientAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Spring Data GraphQL application...");
        for (Project project : service.findAllProjects()) {
            System.out.println(project);
        }

    }
}