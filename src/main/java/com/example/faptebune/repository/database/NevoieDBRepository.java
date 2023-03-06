package com.example.faptebune.repository.database;

import com.example.faptebune.domain.Nevoie;
import com.example.faptebune.domain.Orase;
import com.example.faptebune.domain.Persoana;

import java.sql.*;
import java.time.LocalDateTime;

public class NevoieDBRepository extends AbstractDBRepository<Long, Nevoie> {
    @Override
    protected PreparedStatement getSaveStatement(Connection connection, Nevoie entity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO nevoi (id, titlu, descriere, deadline, om_in_nevoie, om_salvator, status) VALUES (?,?,?,?,?,?,?)");
        statement.setInt(1, Math.toIntExact(entity.getId()));
        statement.setString(2, entity.getTitlu());
        statement.setString(3, entity.getDescriere());
        statement.setTimestamp(4, Timestamp.valueOf(entity.getDeadline()));
        statement.setLong(5,entity.getOm_in_nevoie());
        statement.setNull(6, Types.INTEGER);
        statement.setString(7, entity.getStatus());
        return statement;
    }

    @Override
    protected PreparedStatement getRemoveStatement(Connection connection, Long aLong) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, Nevoie entity, Nevoie newEntity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE nevoi SET om_salvator=?, status=? WHERE id=?");
        preparedStatement.setLong(1, newEntity.getOm_salvator());
        preparedStatement.setString(2, newEntity.getStatus());
        preparedStatement.setInt(3, Math.toIntExact(entity.getId()));
        return preparedStatement;
    }

    @Override
    protected String getTable() {
        return "nevoi";
    }

    @Override
    protected Nevoie createEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String titlu = resultSet.getString("titlu");
        String descriere = resultSet.getString("descriere");
        LocalDateTime deadline = resultSet.getTimestamp("deadline").toLocalDateTime();
        Long omNevoie = resultSet.getLong("om_in_nevoie");
        Long omSalvator = resultSet.getLong("om_salvator");
        String status = resultSet.getString("status");
        Nevoie nevoie = new Nevoie(id, titlu, descriere, deadline, omNevoie, omSalvator, status);
        nevoie.setId(id);
        return nevoie;
    }
}