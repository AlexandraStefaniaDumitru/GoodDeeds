package com.example.faptebune.repository.database;

import com.example.faptebune.domain.Orase;
import com.example.faptebune.domain.Persoana;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersoanaDBRepository extends AbstractDBRepository<Long, Persoana>{
    @Override
    protected PreparedStatement getSaveStatement(Connection connection, Persoana entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO persoane (id, nume, prenume, username, parola, oras, strada, numar_strada, telefon) VALUES (?,?,?,?,?,?,?,?,?)");
        statement.setInt(1, Math.toIntExact(entity.getId()));
        statement.setString(2, entity.getNume());
        statement.setString(3, entity.getPrenume());
        statement.setString(4, entity.getUsername());
        statement.setString(5, entity.getParola());
        statement.setString(6, String.valueOf(entity.getOras()));
        statement.setString(7, entity.getStrada());
        statement.setString(8, entity.getNumar_strada());
        statement.setString(9, entity.getTelefon());
        return statement;
    }

    @Override
    protected PreparedStatement getRemoveStatement(Connection connection, Long aLong) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, Persoana entity, Persoana newEntity) throws SQLException {
        return null;
    }

    @Override
    protected String getTable() {
        return "persoane";
    }

    @Override
    protected Persoana createEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String username = resultSet.getString("username");
        String parola = resultSet.getString("parola");
        Orase oras = Orase.valueOf(resultSet.getString("oras"));
        String strada = resultSet.getString("strada");
        String numar_strada = resultSet.getString("numar_strada");
        String telefon = resultSet.getString("telefon");
        Persoana persoana = new Persoana(id,nume,prenume,username,parola, oras, strada, numar_strada, telefon);
        persoana.setId(id);
        return persoana;
    }
}