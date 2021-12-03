package org.haulmont.rnd.data.config;

import org.haulmont.rnd.data.repository.support.GraphQlRepositoryFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import org.springframework.data.repository.config.RepositoryConfigurationSource;

public class GraphQlDataRepositoryExtension extends RepositoryConfigurationExtensionSupport {

    @Override
    protected String getModulePrefix() {
        return "spring-data-graphql";
    }

    @Override
    public String getRepositoryFactoryBeanClassName() {
        return GraphQlRepositoryFactoryBean.class.getName();
    }

    @Override
    public void postProcess(BeanDefinitionBuilder builder, RepositoryConfigurationSource source) {
        super.postProcess(builder, source);
        builder.addPropertyValue("defaultEndpointUrl", source.getAttribute("defaultEndpointUrl").orElseThrow(IllegalStateException::new));
    }
}