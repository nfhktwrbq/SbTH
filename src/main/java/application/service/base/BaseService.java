package application.service.base;


import java.io.Serializable;
import java.util.List;

public interface BaseService<T, I extends Serializable> {

    T save(T entity);

    T getById(I id);

    void delete(T entity);

    List<T> findAll();

}
