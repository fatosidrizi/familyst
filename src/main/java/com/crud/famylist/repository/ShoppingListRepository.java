package com.crud.famylist.repository;

import com.crud.famylist.domain.ShoppingList;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ShoppingList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    @Query("select shoppingList from ShoppingList shoppingList where shoppingList.user.login = ?#{principal.username}")
    List<ShoppingList> findByUserIsCurrentUser();
}
