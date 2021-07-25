package ru.bulldog.hiberapp.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DAO<T, ID extends Serializable> {
	Optional<T> getOne(ID id);
	List<T> findAll();
	T save(T entity);
	void delete(T entity);
	void delete(ID id);
}
