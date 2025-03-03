package com.osrs_springboot_project.osrs_springboot_project.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.ItemNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemData;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemId;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemInfoData;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemPriceData;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemResponse;
import com.osrs_springboot_project.osrs_springboot_project.repositories.ItemRepository;

@Service
public class ItemService {
    private static final String OSRS_ITEM_ID_DATA_URL = "https://oldschool.runescape.wiki/?title=Module:GEIDs/data.json&action=raw&ctype=application%2Fjson";
    private static final String OSRS_ITEM_DATA_URL = "https://secure.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=";

    @Autowired
    ItemRepository itemRepository;

    /* Item Services */
    public Boolean getItemIDs() {
        RestTemplate restTemplate = new RestTemplate();
        String url = OSRS_ITEM_ID_DATA_URL;
        int i = 0;

        try {
            String response = restTemplate.getForObject(url, String.class);
            response = response.substring(3, response.length() - 2); // Trim {} at beginning and end of the response.
            String[] itemIdDataList = response != null ? response.split(",\n\t") : new String[0];

            for (i = 2; i < itemIdDataList.length; i++) {
                String[] rawItemId = itemIdDataList[i].split(": ");
                rawItemId[0] = rawItemId[0].replace(" ", "_");
                ItemId itemId = new ItemId(rawItemId[0].substring(1, rawItemId[0].length()-1), Integer.parseInt(rawItemId[1]));

                this.itemRepository.save(itemId);
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public ItemData getItemData(String itemName) {
        RestTemplate restTemplate = new RestTemplate();
        ItemData itemData = null;
        ItemId itemId = null;

        itemId = itemRepository.findById(itemName).orElseThrow(() -> new ItemNotFoundException(itemName));

        String url = OSRS_ITEM_DATA_URL + itemId.getId();

        try {
            // Create HttpHeaders and set Accept to application/json
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            // Create the request entity with the URL and headers
            RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, new URI(url));

            // Perform the request and get the response as ItemResponse
            ResponseEntity<String> response = restTemplate.exchange(request, String.class);

            // Deserialize the response body into ItemResponse (assuming it's a valid JSON)
            ItemResponse itemResponse = new ObjectMapper().readValue(response.getBody(), ItemResponse.class);
            return itemResponse.getItem();

        } catch (Exception e) {
            System.out.println(e.toString());
            return itemData;
        }
    }

    public ItemInfoData getItemInfoData(String itemName) {
        return extractItemInfo(this.getItemData(itemName));
    }

    public ItemInfoData extractItemInfo(ItemData itemData) {
        ItemInfoData itemInfo = new ItemInfoData();
        itemInfo.setId(itemData.getId());
        itemInfo.setIcon(itemData.getIcon());
        itemInfo.setIconLarge(itemData.getIconLarge());
        itemInfo.setType(itemData.getType());
        itemInfo.setTypeIcon(itemData.getTypeIcon());
        itemInfo.setName(itemData.getName());
        itemInfo.setDescription(itemData.getDescription());
        itemInfo.setMembers(itemData.getMembers());

        return itemInfo;
    }

    // public ItemPriceData extractItemPriceData(ItemData itemData) {
        // ItemPriceData priceData = null;
        // priceData.setId(itemData.getId()); // Link by ID
        // priceData.setCurrentPrice(itemData.getCurrentPrice());
        // priceData.setTodayPrice(itemData.getTodayPrice());
        // priceData.setDay30(itemData.getDay30());
        // priceData.setDay90(itemData.getDay90());
        // priceData.setDay180(itemData.getDay180());

        // return pricedata;
    // }
}
