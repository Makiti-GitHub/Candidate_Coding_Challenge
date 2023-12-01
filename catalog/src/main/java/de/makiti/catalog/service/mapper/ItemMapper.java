package de.makiti.catalog.service.mapper;

import de.makiti.catalog.domain.item.Description;
import de.makiti.catalog.domain.item.Item;
import de.makiti.catalog.domain.item.ItemId;
import de.makiti.catalog.domain.item.ItemType;
import de.makiti.catalog.domain.item.Name;
import de.makiti.catalog.dto.ItemDTO;
import de.makiti.catalog.util.Constants;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Optional;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ItemMapper {
    default ItemDTO toDTO(Item value, String fieldsToExtractCode) {
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
    ItemDTO toDTOForExtractCode1(Item value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "type")
    @Mapping(target = "name")
    ItemDTO toDTOForExtractCode2(Item value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "barcode")
    @Mapping(target = "type")
    @Mapping(target = "name")
    @Mapping(target = "description")
    void fromDTOForUpdate(@MappingTarget Item item, ItemDTO value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "barcode")
    @Mapping(target = "type")
    @Mapping(target = "name")
    @Mapping(target = "description")
    Item fromDTOForNew(ItemDTO value);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "barcode")
    @Mapping(target = "type")
    @Mapping(target = "name")
    @Mapping(target = "description")
    ItemDTO toDTOForEventBus(Item value);

    default UUID map(ItemId value) {
        return Optional.ofNullable(value)
                .map(ItemId::toUuid)
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

    default ItemId mapToItemId(String value) {
        return Optional.ofNullable(value)
                .map(ItemId::new)
                .orElse(null);
    }
}
