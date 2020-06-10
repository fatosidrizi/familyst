package com.crud.famylist.web.rest;

import com.crud.famylist.service.ShoppingListService;
import com.crud.famylist.service.dto.ShoppingListDTO;
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
 * REST controller for managing {@link com.crud.famylist.domain.ShoppingList}.
 */
@RestController
@RequestMapping("/api")
public class ShoppingListResource {
    private final Logger log = LoggerFactory.getLogger(ShoppingListResource.class);

    private static final String ENTITY_NAME = "shoppingList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShoppingListService shoppingListService;

    public ShoppingListResource(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    /**
     * {@code POST  /shopping-lists} : Create a new shoppingList.
     *
     * @param shoppingListDTO the shoppingListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shoppingListDTO, or with status {@code 400 (Bad Request)} if the shoppingList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shopping-lists")
    public ResponseEntity<ShoppingListDTO> createShoppingList(@Valid @RequestBody ShoppingListDTO shoppingListDTO)
        throws URISyntaxException {
        log.debug("REST request to save ShoppingList : {}", shoppingListDTO);
        if (shoppingListDTO.getId() != null) {
            throw new BadRequestAlertException("A new shoppingList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoppingListDTO result = shoppingListService.save(shoppingListDTO);
        return ResponseEntity
            .created(new URI("/api/shopping-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shopping-lists} : Updates an existing shoppingList.
     *
     * @param shoppingListDTO the shoppingListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shoppingListDTO,
     * or with status {@code 400 (Bad Request)} if the shoppingListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shoppingListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shopping-lists")
    public ResponseEntity<ShoppingListDTO> updateShoppingList(@Valid @RequestBody ShoppingListDTO shoppingListDTO)
        throws URISyntaxException {
        log.debug("REST request to update ShoppingList : {}", shoppingListDTO);
        if (shoppingListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShoppingListDTO result = shoppingListService.save(shoppingListDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shoppingListDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shopping-lists} : get all the shoppingLists.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shoppingLists in body.
     */
    @GetMapping("/shopping-lists")
    public ResponseEntity<List<ShoppingListDTO>> getAllShoppingLists(Pageable pageable) {
        log.debug("REST request to get a page of ShoppingLists");
        Page<ShoppingListDTO> page = shoppingListService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shopping-lists/:id} : get the "id" shoppingList.
     *
     * @param id the id of the shoppingListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shoppingListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shopping-lists/{id}")
    public ResponseEntity<ShoppingListDTO> getShoppingList(@PathVariable Long id) {
        log.debug("REST request to get ShoppingList : {}", id);
        Optional<ShoppingListDTO> shoppingListDTO = shoppingListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoppingListDTO);
    }

    /**
     * {@code DELETE  /shopping-lists/:id} : delete the "id" shoppingList.
     *
     * @param id the id of the shoppingListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shopping-lists/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id) {
        log.debug("REST request to delete ShoppingList : {}", id);
        shoppingListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
