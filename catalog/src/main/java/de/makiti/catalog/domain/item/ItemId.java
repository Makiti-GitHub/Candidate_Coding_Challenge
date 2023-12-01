package de.makiti.catalog.domain.item;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Builder
@Data
@Embeddable
public class ItemId implements Serializable {

    @NonNull
    @Builder.Default
    private String value = UUID.randomUUID().toString();

    public ItemId(@NonNull String value) {
        this.value = value;
    }

    public ItemId(@NonNull UUID value) {
        this.value = value.toString();
    }

    public UUID toUuid() {
        return UUID.fromString(value);
    }


}
