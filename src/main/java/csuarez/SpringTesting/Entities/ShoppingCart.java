package csuarez.SpringTesting.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "shoppingcart")
public class ShoppingCart {
    @Id
    private String id;
    @OneToOne
    @MapsId //we want to use the user's id to set the shoppingCart's id
    private User user;
    @OneToMany //we use cart items instead of products to be more accurate
    private List<CartItem> items = new ArrayList<>();
    @Transient
    private BigDecimal total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
