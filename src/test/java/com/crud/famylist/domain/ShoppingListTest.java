package com.crud.famylist.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.crud.famylist.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ShoppingListTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingList.class);
        ShoppingList shoppingList1 = new ShoppingList();
        shoppingList1.setId(1L);
        ShoppingList shoppingList2 = new ShoppingList();
        shoppingList2.setId(shoppingList1.getId());
        assertThat(shoppingList1).isEqualTo(shoppingList2);
        shoppingList2.setId(2L);
        assertThat(shoppingList1).isNotEqualTo(shoppingList2);
        shoppingList1.setId(null);
        assertThat(shoppingList1).isNotEqualTo(shoppingList2);
    }
}
