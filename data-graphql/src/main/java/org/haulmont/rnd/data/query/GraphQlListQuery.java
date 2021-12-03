package org.haulmont.rnd.data.query;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphQlListQuery extends GraphQlDataQuery {

    private static final Log log = LogFactory.getLog(GraphQlListQuery.class.getName());

    public GraphQlListQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, String defaultEndpointUrl) {
        super(method, metadata, factory, defaultEndpointUrl);
    }

    @Override
    protected Object doExecute(List<Object> parameters, List<String> paramNames) {
        try {
            String fieldList = Arrays.stream(metadata.getDomainType().getDeclaredFields())
                    .map(Field::getName)
                    .collect(Collectors.joining(" "));

            String params = Streams.zip(paramNames.stream(), parameters.stream(), (name, value) -> name+":\\\""+value+"\\\"").collect(Collectors.joining(", "));
            params = parameters.size() > 0 ? "("+params+")" : "";
            String query = "{\"query\":\"query { " + queryName + params+" {" + fieldList + "}}\"}";
            log.info("Sending query "+query);
            HttpRequest request = HttpRequest.newBuilder(new URI(defaultEndpointUrl))
                    .header("content-type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(query))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode data = new ObjectMapper().readTree(response.body()).findPath("data").findPath(queryName);
            List list = new ArrayList();
            for (JsonNode node : data) {
                list.add(new ObjectMapper().readValue(node.toString(), metadata.getDomainType()));
            }
            return list;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot execute GraphQL query", e);
        }
    }
}