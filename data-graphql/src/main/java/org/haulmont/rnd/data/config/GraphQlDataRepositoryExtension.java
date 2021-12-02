package org.haulmont.rnd.data.config;

import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;

public class GraphQlDataRepositoryExtension extends RepositoryConfigurationExtensionSupport {

    @Override
    protected String getModulePrefix() {
        return "spring-data-graphql";
    }

    @Override
    public String getRepositoryFactoryBeanClassName() {
        return null;
    }
}