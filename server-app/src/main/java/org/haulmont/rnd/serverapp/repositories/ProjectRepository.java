package org.haulmont.rnd.serverapp.repositories;

import org.haulmont.rnd.serverapp.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByName(String name);

    List<Project> findByNameIsLike(String name);

}