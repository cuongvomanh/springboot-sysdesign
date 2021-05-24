package com.mycompany.mygroup.core.gateway;

public interface EntityGateway<T> {
    public T save(T entity);
    public T findOne(Long id);
}
