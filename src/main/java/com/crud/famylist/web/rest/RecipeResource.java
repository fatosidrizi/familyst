package com.crud.famylist.web.rest;

import com.crud.famylist.service.RecipeService;
import com.crud.famylist.service.dto.RecipeDTO;
import com.crud.famylist.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.crud.famylist.domain.Recipe}.
 */
@RestController
@RequestMapping("/api")
public class RecipeResource {
    private final Logger log = LoggerFactory.getLogger(RecipeResource.class);

    private static final String ENTITY_NAME = "recipe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * {@code POST  /recipes} : Create a new recipe.
     *
     * @param recipeDTO the recipeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recipeDTO, or with status {@code 400 (Bad Request)} if the recipe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recipes")
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO) throws URISyntaxException {
        log.debug("REST request to save Recipe : {}", recipeDTO);
        if (recipeDTO.getId() != null) {
            throw new BadRequestAlertException("A new recipe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecipeDTO result = recipeService.save(recipeDTO);
        return ResponseEntity
            .created(new URI("/api/recipes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recipes} : Updates an existing recipe.
     *
     * @param recipeDTO the recipeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recipeDTO,
     * or with status {@code 400 (Bad Request)} if the recipeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recipeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recipes")
    public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipeDTO) throws URISyntaxException {
        log.debug("REST request to update Recipe : {}", recipeDTO);
        if (recipeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecipeDTO result = recipeService.save(recipeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recipeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recipes} : get all the recipes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recipes in body.
     */
    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(Pageable pageable) {
        log.debug("REST request to get a page of Recipes");
        Page<RecipeDTO> page = recipeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /recipes/:id} : get the "id" recipe.
     *
     * @param id the id of the recipeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recipeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recipes/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {
        log.debug("REST request to get Recipe : {}", id);
        Optional<RecipeDTO> recipeDTO = recipeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recipeDTO);
    }

    /**
     * {@code DELETE  /recipes/:id} : delete the "id" recipe.
     *
     * @param id the id of the recipeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        log.debug("REST request to delete Recipe : {}", id);
        recipeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
