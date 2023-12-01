package de.makiti.catalog.api;

import de.makiti.catalog.dto.ItemDTO;
import de.makiti.catalog.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@Controller
public class ItemResources implements ItemApi {

    private final ItemService itemService;

    @Override
    public ResponseEntity<UUID> createItem(ItemDTO itemDTO) {
        return ResponseEntity.status(CREATED).body(itemService.createItem(itemDTO));
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getAllItems(String fieldsToExtractCode) {
        return ResponseEntity.status(OK).body(itemService.getAllItems(fieldsToExtractCode));
    }

    @Override
    public ResponseEntity<ItemDTO> getItemById(UUID id, String fieldsToExtractCode) {
        return ResponseEntity.status(OK).body(itemService.fetchItemById(id, fieldsToExtractCode));
    }

    @Override
    public ResponseEntity<Void> updateItem(UUID id, ItemDTO itemDTO) {
        return null;
    }

}
