package org.haulmont.rnd.data.repository.support;

import org.haulmont.rnd.data.query.GraphQlQueryLookupStrategy;
import org.springframework.data.repository.config.DefaultRepositoryBaseClass;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;

import java.util.Optional;

public class GraphQlRepositoryFactory extends RepositoryFactorySupport {

    private String defaultEndpointUrl;

    public GraphQlRepositoryFactory(String defaultEndpointUrl) {
        this.defaultEndpointUrl = defaultEndpointUrl;
    }

    @Override
    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return new GraphQlEntityInformation<>(domainClass);
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation metadata) {
        return getTargetRepositoryViaReflection(metadata);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return DefaultRepositoryBaseClass.class;
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(QueryLookupStrategy.Key key, QueryMethodEvaluationContextProvider evaluationContextProvider) {
        return Optional.of(new GraphQlQueryLookupStrategy(defaultEndpointUrl));
    }
}