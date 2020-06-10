package com.crud.famylist.service.mapper;

import com.crud.famylist.domain.*;
import com.crud.famylist.service.dto.RecipeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Recipe} and its DTO {@link RecipeDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface RecipeMapper extends EntityMapper<RecipeDTO, Recipe> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    RecipeDTO toDto(Recipe recipe);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "removeItem", ignore = true)
    @Mapping(source = "userId", target = "user")
    Recipe toEntity(RecipeDTO recipeDTO);

    default Recipe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(id);
        return recipe;
    }
}
