package application.service.impl;


import application.repository.base.BaseRepository;
import application.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T, I extends Serializable>
        implements BaseService<T, I>
{

    protected abstract BaseRepository<T, I> getRepository();

    @Transactional
    public T save(@NotNull @Valid T data) {
        return (T) this.getRepository().save(data);
    }

    public T getById(I id) {
        return (T) this.getRepository().findOne(id);
    }

    @Transactional()
    public void delete(T entity) {
        this.getRepository().delete(entity);
    }

    public List<T> findAll() {
        return this.getRepository().findAll();
    }

}



