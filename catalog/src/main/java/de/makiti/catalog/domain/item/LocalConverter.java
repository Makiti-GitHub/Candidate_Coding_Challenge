package de.makiti.catalog.domain.item;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Locale;
import java.util.Optional;

@Converter(autoApply = true)
public class LocalConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale attribute) {
        return Optional
                .ofNullable(attribute)
                .map(Locale::toLanguageTag)
                .orElse(null);
    }

    @Override
    public Locale convertToEntityAttribute(String dbData) {
        String[] parts = dbData.split("_", -1);
        if (parts.length == 1) return new Locale(parts[0]);
        else if (parts.length == 2
                || (parts.length == 3 && parts[2].startsWith("#")))
            return new Locale(parts[0], parts[1]);
        else return new Locale(parts[0], parts[1], parts[2]);
    }
}
