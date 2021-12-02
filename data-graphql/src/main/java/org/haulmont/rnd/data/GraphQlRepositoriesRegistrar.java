package org.haulmont.rnd.data;

import org.haulmont.rnd.data.config.EnableGraphQlDataRepositories;
import org.haulmont.rnd.data.config.GraphQlDataRepositoryExtension;
import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

import java.lang.annotation.Annotation;

public class GraphQlRepositoriesRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableGraphQlDataRepositories.class;
    }

    @Override
    protected RepositoryConfigurationExtension getExtension() {
        return new GraphQlDataRepositoryExtension();
    }

}