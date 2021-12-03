package org.haulmont.rnd.data.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphQlListQuery extends GraphQlDataQuery {

    public GraphQlListQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory) {
        super(method, metadata, factory);
    }

    @Override
    protected Object doExecute(Object[] parameters) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/graphql"))
                    .header("content-type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"query\":\"query { findAllProjects {id name}}\"}"))
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode data = mapper.readTree(response.body()).findPath("data").findPath("findAllProjects");
            List list = new ArrayList();
            for (JsonNode node : data) {
                list.add(new ObjectMapper().readValue(node.toString(), metadata.getDomainType()));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}