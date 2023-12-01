package de.makiti.inventory.api;

import de.makiti.inventory.dto.ProductDTO;
import de.makiti.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@Controller
public class ProductResources implements ProductApi {

    private final ProductService itemService;

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts(String fieldsToExtractCode) {
        return ResponseEntity.status(OK).body(itemService.getAllProducts(fieldsToExtractCode));
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(UUID id, String fieldsToExtractCode) {
        return ResponseEntity.status(OK).body(itemService.fetchProductById(id, fieldsToExtractCode));
    }
}
