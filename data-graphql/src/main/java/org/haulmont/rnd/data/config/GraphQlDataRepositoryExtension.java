package org.haulmont.rnd.data.config;

import org.haulmont.rnd.data.repository.support.GraphQlRepositoryFactoryBean;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;

public class GraphQlDataRepositoryExtension extends RepositoryConfigurationExtensionSupport {

    @Override
    protected String getModulePrefix() {
        return "spring-data-graphql";
    }

    @Override
    public String getRepositoryFactoryBeanClassName() {
        return GraphQlRepositoryFactoryBean.class.getName();
    }
}