package com.crud.famylist.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ShoppingList.
 */
@Entity
@Table(name = "shopping_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sort_order")
    private Long sortOrder;

    @OneToMany(mappedBy = "shoppingList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Item> items = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "shoppingLists", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ShoppingList name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public ShoppingList sortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Set<Item> getItems() {
        return items;
    }

    public ShoppingList items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public ShoppingList addItem(Item item) {
        this.items.add(item);
        item.setShoppingList(this);
        return this;
    }

    public ShoppingList removeItem(Item item) {
        this.items.remove(item);
        item.setShoppingList(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public ShoppingList user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingList)) {
            return false;
        }
        return id != null && id.equals(((ShoppingList) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShoppingList{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sortOrder=" + getSortOrder() +
            "}";
    }
}
