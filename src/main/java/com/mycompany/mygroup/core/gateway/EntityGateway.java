package com.mycompany.mygroup.core.gateway;

public interface EntityGateway<T> {
    public Long save(T entity);
    public T findOne(Long id);
}
