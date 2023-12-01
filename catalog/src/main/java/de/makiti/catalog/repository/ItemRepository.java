package de.makiti.catalog.repository;

import de.makiti.catalog.domain.item.Item;
import de.makiti.catalog.domain.item.ItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemId> {
}
