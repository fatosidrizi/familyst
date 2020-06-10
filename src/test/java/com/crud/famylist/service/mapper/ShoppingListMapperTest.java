package com.crud.famylist.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingListMapperTest {
    private ShoppingListMapper shoppingListMapper;

    @BeforeEach
    public void setUp() {
        shoppingListMapper = new ShoppingListMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shoppingListMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shoppingListMapper.fromId(null)).isNull();
    }
}
