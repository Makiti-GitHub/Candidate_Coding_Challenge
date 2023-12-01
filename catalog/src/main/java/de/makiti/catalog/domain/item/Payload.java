package de.makiti.catalog.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Payload<T> {
    @Builder.Default
    @NonNull
    private Locale locale = Locale.ENGLISH;
    @NonNull
    private String type;
    @NonNull
    private T data;

    public String getLocaleAsString() {
        return this.locale.getLanguage();
    }
}