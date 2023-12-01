package de.makiti.inventory.domain.product;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_product")
public class Product {
    @Builder.Default
    @EmbeddedId
    @AttributeOverride(
            name = "value",
            column = @Column(name = "c_id")
    )
    private ProductId id = new ProductId();
    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "c_name")
    )
    private Name name;
    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "c_description")
    )
    private Description description;
    @Column(name = "c_type")
    @Enumerated(EnumType.STRING)
    @NonNull
    private ProductType type;
    @Column(name = "c_barcode")
    private String barcode;
}
