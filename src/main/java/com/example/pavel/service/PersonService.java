package com.example.pavel.service;

import com.example.pavel.dao.PersonDAO;
import com.example.pavel.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService extends Database implements PersonDAO {

    Connection connection = getConnection();


    @Override
    public void add(Person person) {
        String sql = "INSERT INTO person (name,second_name) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSecondName());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys() ){
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT id,name,second_name FROM person ORDER BY id";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSecondName(rs.getString("second_name"));

                personList.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); }

        return personList;
    }

    @Override
    public Person getById(long id) {
        String sql = "SELECT id,name,second_name FROM person WHERE id=?";
        Person person = new Person();
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSecondName(rs.getString("second_name"));
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return person;
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET name=?, second_name=?  WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getSecondName());
            stmt.setLong(3, person.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Person person) {
        String sql = "DELETE FROM person WHERE id=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1,person.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
