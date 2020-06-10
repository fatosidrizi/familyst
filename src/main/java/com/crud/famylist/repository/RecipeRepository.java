package com.crud.famylist.repository;

import com.crud.famylist.domain.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Recipe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("select recipe from Recipe recipe where recipe.user.login = ?#{principal.username}")
    List<Recipe> findByUserIsCurrentUser();
}
