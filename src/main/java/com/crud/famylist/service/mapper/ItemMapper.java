package com.crud.famylist.service.mapper;

import com.crud.famylist.domain.*;
import com.crud.famylist.service.dto.ItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Item} and its DTO {@link ItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { ShoppingListMapper.class, RecipeMapper.class })
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {
    @Mapping(source = "shoppingList.id", target = "shoppingListId")
    @Mapping(source = "shoppingList.name", target = "shoppingListName")
    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "recipe.name", target = "recipeName")
    ItemDTO toDto(Item item);

    @Mapping(source = "shoppingListId", target = "shoppingList")
    @Mapping(source = "recipeId", target = "recipe")
    Item toEntity(ItemDTO itemDTO);

    default Item fromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
