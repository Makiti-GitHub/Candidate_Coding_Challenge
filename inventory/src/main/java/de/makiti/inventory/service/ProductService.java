package de.makiti.inventory.service;

import de.makiti.catalog.dto.CatalogItemDTO;
import de.makiti.inventory.domain.product.ProductId;
import de.makiti.inventory.dto.ProductDTO;
import de.makiti.inventory.exception.ResourceNotFoundException;
import de.makiti.inventory.repository.ProductRepository;
import de.makiti.inventory.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Transactional
    public List<ProductDTO> getAllProducts(String fieldsToExtractCode) {
        return productRepository
                .findAll()
                .stream()
                .map(item -> productMapper.toDTO(item, fieldsToExtractCode))
                .toList();
    }

    @Transactional
    public ProductDTO fetchProductById(UUID itemId, String fieldsToExtractCode) {
        return Optional.ofNullable(itemId)
                .map(ProductId::new)
                .flatMap(productRepository::findById)
                .map(item -> productMapper.toDTO(item, fieldsToExtractCode))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional
    public void createProduct(CatalogItemDTO itemDTO) {
        Optional.ofNullable(itemDTO)
                .map(productMapper::fromDTOForNew)
                .ifPresent(productRepository::saveAndFlush);
    }

    @Transactional
    public void update(CatalogItemDTO itemDTO) {
        Optional.ofNullable(itemDTO.getId())
                .map(ProductId::new)
                .flatMap(productRepository::findById)
                .map(product -> {
                    productMapper.fromDTOForUpdate(product, itemDTO);
                    return product;
                })
                .ifPresent(productRepository::saveAndFlush);
    }
}
