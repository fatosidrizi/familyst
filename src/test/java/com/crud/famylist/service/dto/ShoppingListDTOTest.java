package com.crud.famylist.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.crud.famylist.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ShoppingListDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingListDTO.class);
        ShoppingListDTO shoppingListDTO1 = new ShoppingListDTO();
        shoppingListDTO1.setId(1L);
        ShoppingListDTO shoppingListDTO2 = new ShoppingListDTO();
        assertThat(shoppingListDTO1).isNotEqualTo(shoppingListDTO2);
        shoppingListDTO2.setId(shoppingListDTO1.getId());
        assertThat(shoppingListDTO1).isEqualTo(shoppingListDTO2);
        shoppingListDTO2.setId(2L);
        assertThat(shoppingListDTO1).isNotEqualTo(shoppingListDTO2);
        shoppingListDTO1.setId(null);
        assertThat(shoppingListDTO1).isNotEqualTo(shoppingListDTO2);
    }
}
