package com.cfstarter.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T, K> {

	public Optional<T> getById(K id);

	public List<T> getAll();

	public void save(T entity) throws SQLException;

	public void delete(K id);

	public void update(T entity);

}
