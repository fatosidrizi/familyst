package com.crud.famylist.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.crud.famylist.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RecipeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecipeDTO.class);
        RecipeDTO recipeDTO1 = new RecipeDTO();
        recipeDTO1.setId(1L);
        RecipeDTO recipeDTO2 = new RecipeDTO();
        assertThat(recipeDTO1).isNotEqualTo(recipeDTO2);
        recipeDTO2.setId(recipeDTO1.getId());
        assertThat(recipeDTO1).isEqualTo(recipeDTO2);
        recipeDTO2.setId(2L);
        assertThat(recipeDTO1).isNotEqualTo(recipeDTO2);
        recipeDTO1.setId(null);
        assertThat(recipeDTO1).isNotEqualTo(recipeDTO2);
    }
}
