package com.crud.famylist.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crud.famylist.FamilystApp;
import com.crud.famylist.domain.ShoppingList;
import com.crud.famylist.domain.User;
import com.crud.famylist.repository.ShoppingListRepository;
import com.crud.famylist.service.ShoppingListService;
import com.crud.famylist.service.dto.ShoppingListDTO;
import com.crud.famylist.service.mapper.ShoppingListMapper;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ShoppingListResource} REST controller.
 */
@SpringBootTest(classes = FamilystApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShoppingListResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SORT_ORDER = 1L;
    private static final Long UPDATED_SORT_ORDER = 2L;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShoppingListMockMvc;

    private ShoppingList shoppingList;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShoppingList createEntity(EntityManager em) {
        ShoppingList shoppingList = new ShoppingList().name(DEFAULT_NAME).sortOrder(DEFAULT_SORT_ORDER);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        shoppingList.setUser(user);
        return shoppingList;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShoppingList createUpdatedEntity(EntityManager em) {
        ShoppingList shoppingList = new ShoppingList().name(UPDATED_NAME).sortOrder(UPDATED_SORT_ORDER);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        shoppingList.setUser(user);
        return shoppingList;
    }

    @BeforeEach
    public void initTest() {
        shoppingList = createEntity(em);
    }

    @Test
    @Transactional
    public void createShoppingList() throws Exception {
        int databaseSizeBeforeCreate = shoppingListRepository.findAll().size();
        // Create the ShoppingList
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDto(shoppingList);
        restShoppingListMockMvc
            .perform(
                post("/api/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shoppingListDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ShoppingList in the database
        List<ShoppingList> shoppingListList = shoppingListRepository.findAll();
        assertThat(shoppingListList).hasSize(databaseSizeBeforeCreate + 1);
        ShoppingList testShoppingList = shoppingListList.get(shoppingListList.size() - 1);
        assertThat(testShoppingList.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShoppingList.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
    }

    @Test
    @Transactional
    public void createShoppingListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shoppingListRepository.findAll().size();

        // Create the ShoppingList with an existing ID
        shoppingList.setId(1L);
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDto(shoppingList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShoppingListMockMvc
            .perform(
                post("/api/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shoppingListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShoppingList in the database
        List<ShoppingList> shoppingListList = shoppingListRepository.findAll();
        assertThat(shoppingListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = shoppingListRepository.findAll().size();
        // set the field null
        shoppingList.setName(null);

        // Create the ShoppingList, which fails.
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDto(shoppingList);

        restShoppingListMockMvc
            .perform(
                post("/api/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shoppingListDTO))
            )
            .andExpect(status().isBadRequest());

        List<ShoppingList> shoppingListList = shoppingListRepository.findAll();
        assertThat(shoppingListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllShoppingLists() throws Exception {
        // Initialize the database
        shoppingListRepository.saveAndFlush(shoppingList);

        // Get all the shoppingListList
        restShoppingListMockMvc
            .perform(get("/api/shopping-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shoppingList.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER.intValue())));
    }

    @Test
    @Transactional
    public void getShoppingList() throws Exception {
        // Initialize the database
        shoppingListRepository.saveAndFlush(shoppingList);

        // Get the shoppingList
        restShoppingListMockMvc
            .perform(get("/api/shopping-lists/{id}", shoppingList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shoppingList.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingShoppingList() throws Exception {
        // Get the shoppingList
        restShoppingListMockMvc.perform(get("/api/shopping-lists/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShoppingList() throws Exception {
        // Initialize the database
        shoppingListRepository.saveAndFlush(shoppingList);

        int databaseSizeBeforeUpdate = shoppingListRepository.findAll().size();

        // Update the shoppingList
        ShoppingList updatedShoppingList = shoppingListRepository.findById(shoppingList.getId()).get();
        // Disconnect from session so that the updates on updatedShoppingList are not directly saved in db
        em.detach(updatedShoppingList);
        updatedShoppingList.name(UPDATED_NAME).sortOrder(UPDATED_SORT_ORDER);
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDto(updatedShoppingList);

        restShoppingListMockMvc
            .perform(
                put("/api/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shoppingListDTO))
            )
            .andExpect(status().isOk());

        // Validate the ShoppingList in the database
        List<ShoppingList> shoppingListList = shoppingListRepository.findAll();
        assertThat(shoppingListList).hasSize(databaseSizeBeforeUpdate);
        ShoppingList testShoppingList = shoppingListList.get(shoppingListList.size() - 1);
        assertThat(testShoppingList.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShoppingList.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
    }

    @Test
    @Transactional
    public void updateNonExistingShoppingList() throws Exception {
        int databaseSizeBeforeUpdate = shoppingListRepository.findAll().size();

        // Create the ShoppingList
        ShoppingListDTO shoppingListDTO = shoppingListMapper.toDto(shoppingList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShoppingListMockMvc
            .perform(
                put("/api/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(shoppingListDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShoppingList in the database
        List<ShoppingList> shoppingListList = shoppingListRepository.findAll();
        assertThat(shoppingListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShoppingList() throws Exception {
        // Initialize the database
        shoppingListRepository.saveAndFlush(shoppingList);

        int databaseSizeBeforeDelete = shoppingListRepository.findAll().size();

        // Delete the shoppingList
        restShoppingListMockMvc
            .perform(delete("/api/shopping-lists/{id}", shoppingList.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShoppingList> shoppingListList = shoppingListRepository.findAll();
        assertThat(shoppingListList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
