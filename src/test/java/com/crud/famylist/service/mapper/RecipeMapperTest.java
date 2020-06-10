package com.crud.famylist.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeMapperTest {
    private RecipeMapper recipeMapper;

    @BeforeEach
    public void setUp() {
        recipeMapper = new RecipeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(recipeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(recipeMapper.fromId(null)).isNull();
    }
}
