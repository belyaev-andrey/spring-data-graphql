package org.haulmont.rnd.data.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.haulmont.rnd.data.config.GraphQlQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

public abstract class GraphQlDataQuery implements RepositoryQuery {

    private static final Log log = LogFactory.getLog(GraphQlDataQuery.class.getName());

    protected final Method method;
    protected final RepositoryMetadata metadata;
    protected final ProjectionFactory factory;
    protected final String queryName;

    public GraphQlDataQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory) {
        this.method = method;
        this.metadata = metadata;
        this.factory = factory;
        GraphQlQuery annotation = method.getDeclaredAnnotation(GraphQlQuery.class);
        queryName = annotation != null ? annotation.value() : method.getName();
    }

    @Override
    public Object execute(Object[] parameters) {
        log.debug(String.format("Query Parameters: \"%s\"", Arrays.toString(parameters)));
        return doExecute(parameters);
    }

    @Override
    public QueryMethod getQueryMethod() {
        return new QueryMethod(method, metadata, factory);
    }

    protected abstract Object doExecute(Object[] parameters);

}