package org.haulmont.rnd.data.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.data.repository.query.parser.PartTree;

import java.lang.reflect.Method;

public class GraphQlQueryLookupStrategy implements QueryLookupStrategy {

    private static final Log log = LogFactory.getLog(GraphQlQueryLookupStrategy.class.getName());


    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
        log.debug(String.format("Resolving query for %s", method));
        return new GraphQlListQuery(method, metadata, factory);
    }
}