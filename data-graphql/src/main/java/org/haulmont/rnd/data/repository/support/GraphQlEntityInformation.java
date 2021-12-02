package org.haulmont.rnd.data.repository.support;

import org.springframework.data.repository.core.support.AbstractEntityInformation;

public class GraphQlEntityInformation<T, ID> extends AbstractEntityInformation<T,ID> {

    public GraphQlEntityInformation(Class<T> domainClass) {
        super(domainClass);
    }

    @Override
    public ID getId(T entity) {
        return null;
    }

    @Override
    public Class<ID> getIdType() {
        return null;
    }

    @Override
    public ID getRequiredId(T entity) throws IllegalArgumentException {
        return super.getRequiredId(entity);
    }
}