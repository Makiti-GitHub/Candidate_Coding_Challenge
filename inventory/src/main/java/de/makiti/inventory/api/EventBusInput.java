package de.makiti.inventory.api;

import de.makiti.catalog.dto.CatalogItemDTO;
import de.makiti.inventory.domain.product.Payload;
import de.makiti.inventory.service.ProductService;
import de.makiti.inventory.util.PayloadType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
public class EventBusInput {
    private final ProductService productService;
    @Bean
    public Consumer<Payload<CatalogItemDTO>> item() {
        return payload -> {
            if (PayloadType.CREATED.equals(payload.getType())) {
                productService.createProduct(payload.getData());
            } else if (PayloadType.UPDATED.equals(payload.getType())) {
                productService.update(payload.getData());
            }
        };
    }
}
