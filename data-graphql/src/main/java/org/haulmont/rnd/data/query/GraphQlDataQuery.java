package org.haulmont.rnd.data.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.haulmont.rnd.data.config.GraphQlQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GraphQlDataQuery implements RepositoryQuery {

    private static final Log log = LogFactory.getLog(GraphQlDataQuery.class.getName());

    protected final Method method;
    protected final RepositoryMetadata metadata;
    protected final ProjectionFactory factory;
    protected final String queryName;
    protected final String defaultEndpointUrl;

    public GraphQlDataQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, String defaultEndpointUrl) {
        this.method = method;
        this.metadata = metadata;
        this.factory = factory;
        GraphQlQuery annotation = method.getDeclaredAnnotation(GraphQlQuery.class);
        queryName = annotation != null ? annotation.value() : method.getName();
        this.defaultEndpointUrl = defaultEndpointUrl;
    }

    @Override
    public Object execute(Object[] parameters) {
        List<String> paramNames = new ArrayList<>();
        if(parameters.length > 0) {
            log.debug(String.format("Query Parameters: \"%s\"", Arrays.toString(parameters)));
            PartTree tree = new PartTree(method.getName(), metadata.getReturnedDomainClass(method));
            if (tree.iterator().hasNext()) {
                for (PartTree.OrPart orPart : tree) {
                    for (Part part : orPart) {
                        paramNames.add(part.getProperty().getLeafProperty().getSegment());
                    }
                }
            }
        }
        return doExecute(Arrays.asList(parameters), paramNames);
    }

    @Override
    public QueryMethod getQueryMethod() {
        return new QueryMethod(method, metadata, factory);
    }

    protected abstract Object doExecute(List<Object> parameters, List<String> paramNames);

}