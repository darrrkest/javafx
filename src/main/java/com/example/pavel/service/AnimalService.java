package com.example.pavel.service;

import com.example.pavel.dao.AnimalDAO;
import com.example.pavel.entity.Animal;
import com.example.pavel.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalService extends Database implements AnimalDAO {

    Connection connection = getConnection();
    @Override
    public void add(Animal animal) {
        String sql = "INSERT INTO animal (type,nickname,color,owner) VALUES (?,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,animal.getType());
            stmt.setString(2,animal.getNickname());
            stmt.setString(3,animal.getColor());
            stmt.setLong(4,animal.getOwner());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    animal.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Animal> getAll() {
        List<Animal> animalList = new ArrayList<>();
        String sql = "SELECT id,type,nickname,color,owner FROM animal ORDER BY id";

        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Animal animal = new Animal();
                animal.setId(rs.getLong("id"));
                animal.setType(rs.getString("type"));
                animal.setNickname(rs.getString("nickname"));
                animal.setColor(rs.getString("color"));
                animal.setOwner(rs.getLong("owner"));
                animalList.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animalList;
    }

    @Override
    public Animal getById(long id) {
        String sql = "SELECT id,type,nickname,color,owner FROM animal WHERE id=?";
        Animal animal = new Animal();
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            animal.setId(rs.getLong("id"));
            animal.setType(rs.getString("type"));
            animal.setNickname(rs.getString("nickname"));
            animal.setColor(rs.getString("color"));
            animal.setOwner(rs.getLong("owner"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }

    @Override
    public List<Animal> getByPerson(Person person) {
        List<Animal> animalList = new ArrayList<>();
        String sql = "SELECT animal.id,animal.type,animal.nickname,animal.color,animal.\"owner\"\n" +
                "FROM animal INNER JOIN person ON person.id = animal.\"owner\"\n" +
                "WHERE animal.\"owner\"=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,person.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Animal animal = new Animal();
                animal.setId(rs.getLong("id"));
                animal.setType(rs.getString("type"));
                animal.setNickname(rs.getString("nickname"));
                animal.setColor(rs.getString("color"));
                animal.setOwner(rs.getLong("owner"));
                animalList.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animalList;
    }


    @Override
    public void update(Animal animal) {
        String sql = "UPDATE animal SET type=?, nickname=?, color=?, owner=? WHERE id=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(5,animal.getId());
            stmt.setString(1,animal.getType());
            stmt.setString(2,animal.getNickname());
            stmt.setString(3,animal.getColor());
            stmt.setLong(4,animal.getOwner());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(Animal animal) {
        String sql = "DELETE FROM animal WHERE id=?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
