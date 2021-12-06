package org.haulmont.rnd.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface GraphQlDataRepository<T, ID> extends Repository<T, ID> {



}