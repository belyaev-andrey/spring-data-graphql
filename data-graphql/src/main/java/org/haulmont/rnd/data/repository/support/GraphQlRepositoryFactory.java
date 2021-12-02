package org.haulmont.rnd.data.repository.support;

import org.haulmont.rnd.data.repository.GraphQlDataRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class GraphQlRepositoryFactory extends RepositoryFactorySupport {

    @Override
    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return new GraphQlEntityInformation<>(domainClass);
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation metadata) {
        Object domainClass = metadata.getDomainType();
        return getTargetRepositoryViaReflection(metadata, domainClass);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return GraphQlDataRepositoryImpl.class;
    }
}