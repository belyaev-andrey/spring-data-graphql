package org.haulmont.rnd.data.repository.support;

import org.haulmont.rnd.data.repository.GraphQlDataRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class GraphQlDataRepositoryImpl<T,ID> implements GraphQlDataRepository<T,ID> {

    private Class<T> domainClass;

    public GraphQlDataRepositoryImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }


}