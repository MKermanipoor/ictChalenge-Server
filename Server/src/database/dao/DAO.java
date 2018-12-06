package database.dao;

import java.util.List;

/**
 * Created by saeidbahmani on 12/5/18.
 */
public interface DAO<T> {
    long add(T data);

    T get(long id);

    void delete(T object);



    List<T> search(String selection, String order);
}
