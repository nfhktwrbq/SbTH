package application.repository.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends CrudRepository<T, ID> {
    void flush();

    T getOne(ID id);

    List<T> findAll();
}
