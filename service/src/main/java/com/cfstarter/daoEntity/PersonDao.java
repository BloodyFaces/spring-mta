package com.cfstarter.daoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfstarter.dao.IPersonDao;
import com.cfstarter.domain.Person;

@Repository
public class PersonDao implements IPersonDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDao.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public Optional<Person> getById(Long id) {
		Optional<Person> entity = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn.prepareStatement(
						"SELECT SINGLE \"id\", \"name\", \"surname\", \"age\" FROM \"JAVADB\".\"javaCFMTA::Person\" WHERE \"id\" = ?")) {
			stmnt.setLong(1, id);
			ResultSet result = stmnt.executeQuery();
			if (result.next()) {
				Person person = new Person();
				person.setId(id);
				person.setName(result.getString("name"));
				person.setSurname(result.getString("surname"));
				person.setAge(result.getInt("age"));
				entity = Optional.of(person);
			} else {
				entity = Optional.empty();
			}
		} catch (SQLException e) {
			logger.error("Error while trying to get entity by Id: " + e.getMessage());
		}
		return entity;
	}

	@Override
	public List<Person> getAll() {
		List<Person> personList = new ArrayList<Person>();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn
						.prepareStatement("SELECT \"id\", \"name\", \"surname\", \"age\" FROM \"JAVADB\".\"javaCFMTA::Person\"")) {
			ResultSet result = stmnt.executeQuery();
			while (result.next()) {
				Person person = new Person();
				person.setId(result.getLong("ID"));
				person.setName(result.getString("NAME"));
				person.setSurname(result.getString("SURNAME"));
				person.setAge(result.getInt("AGE"));
				personList.add(person);
			}
		} catch (SQLException e) {
			logger.error("Error while trying to get list of entities: " + e.getMessage());
		}
		return personList;
	}

	@Override
	public void save(Person entity) throws SQLException {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn
						.prepareStatement("INSERT INTO \"JAVADB\".\"javaCFMTA::Person\"(\"name\", \"surname\", \"age\") VALUES (?, ?, ?)")) {
			stmnt.setString(1, entity.getName());
			stmnt.setString(2, entity.getSurname());
			stmnt.setInt(3, entity.getAge());
			stmnt.execute();
		} catch (SQLException e) {
			logger.error("Error while trying to add entity: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void delete(Long id) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn
						.prepareStatement("DELETE FROM \"JAVADB\".\"javaCFMTA::Person\" WHERE \"id\" = ?")) {
			stmnt.setLong(1, id);
			stmnt.execute();
		} catch (SQLException e) {
			logger.error("Error while trying to delete entity: " + e.getMessage());
		}
	}

	@Override
	public void update(Person entity) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmnt = conn
						.prepareStatement("UPDATE \"JAVADB\".\"javaCFMTA::Person\" SET \"name\" = ?, \"surname\" = ?, \"age\" = ? WHERE \"id\" = ?")) {
			stmnt.setString(1, entity.getName());
			stmnt.setString(2, entity.getSurname());
			stmnt.setInt(3, entity.getAge());
			stmnt.setLong(4, entity.getId());
			stmnt.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while trying to update entity: " + e.getMessage());
		}
	}

}
