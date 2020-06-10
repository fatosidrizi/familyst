package com.crud.famylist.service;

import com.crud.famylist.service.dto.ShoppingListDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.crud.famylist.domain.ShoppingList}.
 */
public interface ShoppingListService {
    /**
     * Save a shoppingList.
     *
     * @param shoppingListDTO the entity to save.
     * @return the persisted entity.
     */
    ShoppingListDTO save(ShoppingListDTO shoppingListDTO);

    /**
     * Get all the shoppingLists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ShoppingListDTO> findAll(Pageable pageable);

    /**
     * Get the "id" shoppingList.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShoppingListDTO> findOne(Long id);

    /**
     * Delete the "id" shoppingList.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
