package de.makiti.catalog.service;

import de.makiti.catalog.domain.item.ItemId;
import de.makiti.catalog.domain.item.Payload;
import de.makiti.catalog.dto.ItemDTO;
import de.makiti.catalog.exception.ResourceNotFoundException;
import de.makiti.catalog.repository.ItemRepository;
import de.makiti.catalog.service.mapper.ItemMapper;
import de.makiti.catalog.util.EventBusChannel;
import de.makiti.catalog.util.PayloadType;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final StreamBridge eventBusOutput;


    @Transactional
    public List<ItemDTO> getAllItems(String fieldsToExtractCode) {
        return itemRepository
                .findAll()
                .stream()
                .map(item -> itemMapper.toDTO(item, fieldsToExtractCode))
                .toList();
    }

    @Transactional
    public ItemDTO fetchItemById(UUID itemId, String fieldsToExtractCode) {
        return Optional.ofNullable(itemId)
                .map(ItemId::new)
                .flatMap(itemRepository::findById)
                .map(item -> itemMapper.toDTO(item, fieldsToExtractCode))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional
    public UUID createItem(ItemDTO itemDTO) {
        return Optional.ofNullable(itemDTO)
                .map(itemMapper::fromDTOForNew)
                .map(itemRepository::saveAndFlush)
                .map(itemMapper::toDTOForEventBus)
                .map(sendNotificationForCreation())
                .map(ItemDTO::getId)
                .orElseThrow();
    }

    private Function<ItemDTO, ItemDTO> sendNotificationForCreation() {
        return itemDTO -> {
            eventBusOutput.send(EventBusChannel.BINDING_ITEM_OUT,
                    Payload.builder()
                            .type(PayloadType.CREATED)
                            .data(itemDTO)
                            .build());
            return itemDTO;
        };
    }

    @Transactional
    public void update(UUID itemId, ItemDTO itemDTO) {
        Optional.ofNullable(itemId)
                .map(ItemId::new)
                .flatMap(itemRepository::findById)
                .map(item -> {
                    itemMapper.fromDTOForUpdate(item, itemDTO);
                    return item;
                })
                .map(itemMapper::toDTOForEventBus)
                .map(this::sendNotificationForUpdate)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

    private ItemDTO sendNotificationForUpdate(ItemDTO itemDTO) {
        eventBusOutput.send(
                EventBusChannel.BINDING_ITEM_OUT,
                Payload.builder()
                        .type(PayloadType.UPDATED)
                        .data(itemDTO)
                        .build()
        );
        return itemDTO;
    }


}
