package de.makiti.catalog.api;

import de.makiti.catalog.dto.ItemDTO;
import de.makiti.catalog.service.ItemService;
import de.makiti.catalog.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemResourcesTest extends ResourcesTest{
    @MockBean
    private ItemService itemService;
    @Override
    protected Object getResources() {
        return new ItemResources(itemService);
    }


    @Test
    public void fetchAllItemsTest() {
        final var fieldsToExtractCode = Constants.FIELDS_TO_EXTRACT_1;
        final var id1 = UUID.randomUUID();
        ItemDTO item1 = new ItemDTO()
                .id(id1);

        final var id2 = UUID.randomUUID();
        ItemDTO item2 = new ItemDTO()
                .id(id2);

        when(itemService.getAllItems(fieldsToExtractCode)).thenReturn(List.of(item1, item2));

        final var responseBody = webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/item")
                        .queryParam(Constants.FIELDS_TO_EXTRACT_CODE, fieldsToExtractCode)
                        .build()
                )
                .exchange()
                .expectStatus().isEqualTo(200)
                .expectBodyList(ItemDTO.class)
                .returnResult()
                .getResponseBody();

        verify(itemService).getAllItems(fieldsToExtractCode);
        assertThat(responseBody)
                .map(ItemDTO::getId)
                .hasSize(2)
                .contains(id1, id2);
    }
}