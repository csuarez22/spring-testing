package csuarez.SpringTesting.Entities;

import csuarez.SpringTesting.Entities.Enums.CompanyStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //we don't really need to care about ids yet
    private Long id;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    private String address;
    @Column(name = "registration_date", columnDefinition = "DATE")
    private final LocalDate registrationDate = LocalDate.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
