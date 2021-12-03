package org.haulmont.rnd.data.repository.support;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class GraphQlRepositoryFactoryBean <T extends Repository<S, ID>, S, ID extends Serializable>
        extends RepositoryFactoryBeanSupport<T, S, ID> implements BeanFactoryAware {

    private String defaultEndpointUrl;

    public GraphQlRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    public String getDefaultEndpointUrl() {
        return defaultEndpointUrl;
    }

    public void setDefaultEndpointUrl(String defaultEndpointUrl) {
        this.defaultEndpointUrl = defaultEndpointUrl;
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        return new GraphQlRepositoryFactory(getDefaultEndpointUrl());
    }
}