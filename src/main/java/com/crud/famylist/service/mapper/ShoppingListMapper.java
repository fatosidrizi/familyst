package com.crud.famylist.service.mapper;

import com.crud.famylist.domain.*;
import com.crud.famylist.service.dto.ShoppingListDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShoppingList} and its DTO {@link ShoppingListDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface ShoppingListMapper extends EntityMapper<ShoppingListDTO, ShoppingList> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ShoppingListDTO toDto(ShoppingList shoppingList);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "removeItem", ignore = true)
    @Mapping(source = "userId", target = "user")
    ShoppingList toEntity(ShoppingListDTO shoppingListDTO);

    default ShoppingList fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(id);
        return shoppingList;
    }
}
