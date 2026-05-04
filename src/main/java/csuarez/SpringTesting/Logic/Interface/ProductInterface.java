package csuarez.SpringTesting.Logic.Interface;

import java.util.List;

public interface ProductInterface {
    String createNewProduct(Product product);
    String createMultipleProducts(List<Product> products);
    List<Product> getAllProducts();
}
