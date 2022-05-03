package com.huang.tacocloud.data;

import com.huang.tacocloud.Ingredient;

import javax.persistence.criteria.CriteriaBuilder;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
