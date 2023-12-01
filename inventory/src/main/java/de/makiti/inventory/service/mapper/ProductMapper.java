package de.makiti.inventory.service.mapper;

import de.makiti.catalog.dto.CatalogItemDTO;
import de.makiti.inventory.domain.product.Description;
import de.makiti.inventory.domain.product.Name;
import de.makiti.inventory.domain.product.Product;
import de.makiti.inventory.domain.product.ProductId;
import de.makiti.inventory.dto.ProductDTO;
import de.makiti.inventory.util.Constants;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProductMapper {
    default ProductDTO toDTO(Product value, String fieldsToExtractCode) {
        if (fieldsToExtractCode.equals(Constants.FIELDS_TO_EXTRACT_1)) {
            return toDTOForExtractCode1(value);
        } else if (fieldsToExtractCode.equals(Constants.FIELDS_TO_EXTRACT_2)) {
            return toDTOForExtractCode2(value);
        } else {
            throw new IllegalArgumentException(
                    String.format("Extraction Code '%s' is not supported", fieldsToExtractCode)
            );
        }
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "barcode")
    @Mapping(target = "type")
    @Mapping(target = "name")
    @Mapping(target = "description")
    ProductDTO toDTOForExtractCode1(Product value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "type")
    @Mapping(target = "name")
    ProductDTO toDTOForExtractCode2(Product value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "barcode")
    @Mapping(target = "type")
    @Mapping(target = "name")
    @Mapping(target = "description")
    void fromDTOForUpdate(@MappingTarget Product item, CatalogItemDTO value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "barcode")
    @Mapping(target = "type")
    @Mapping(target = "name")
    @Mapping(target = "description")
    Product fromDTOForNew(CatalogItemDTO value);

    default UUID map(ProductId value) {
        return Optional.ofNullable(value)
                .map(ProductId::toUuid)
                .orElse(null);
    }

    default String map(Name value) {
        return Optional.ofNullable(value)
                .map(Name::getValue)
                .orElse(null);
    }

    default String map(Description value) {
        return Optional.ofNullable(value)
                .map(Description::getValue)
                .orElse(null);
    }

    default Name mapToName(String value) {
        return Optional.ofNullable(value)
                .map(Name::new)
                .orElse(null);
    }

    default Description mapToDescription(String value) {
        return Optional.ofNullable(value)
                .map(Description::new)
                .orElse(null);
    }

    default ProductId mapToProductId(String value) {
        return Optional.ofNullable(value)
                .map(ProductId::new)
                .orElse(null);
    }
}
