package de.makiti.inventory.repository;

import de.makiti.inventory.domain.product.Product;
import de.makiti.inventory.domain.product.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
