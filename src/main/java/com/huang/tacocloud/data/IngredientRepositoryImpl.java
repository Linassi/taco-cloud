package com.huang.tacocloud.data;

import com.huang.tacocloud.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository{
    private JdbcTemplate jdbc;

    @Autowired
    public IngredientRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT id,name,type FROM Ingredient", new RowMapper<Ingredient>() {
            @Override
            public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Ingredient(resultSet.getString("id"),resultSet.getString("name")
                    ,Ingredient.Type.valueOf(resultSet.getString("type")));
            }
        });
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("SELECT id,name,type FROM Ingredient WHERE id=?", new RowMapper<Ingredient>() {
            @Override
            public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Ingredient(resultSet.getString("id"),resultSet.getString("name")
                    ,Ingredient.Type.valueOf(resultSet.getString("type")));
            }
        },id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType().toString());
        return ingredient;
    }
}
