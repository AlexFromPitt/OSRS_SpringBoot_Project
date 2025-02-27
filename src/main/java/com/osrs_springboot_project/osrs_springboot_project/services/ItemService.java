package com.osrs_springboot_project.osrs_springboot_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osrs_springboot_project.osrs_springboot_project.models.ItemId;
import com.osrs_springboot_project.osrs_springboot_project.repositories.ItemRepository;

@Service
public class ItemService {
    private static final String OSRS_ITEM_ID_INFO_URL = "https://oldschool.runescape.wiki/?title=Module:GEIDs/data.json&action=raw&ctype=application%2Fjson";

    @Autowired
    ItemRepository itemRepository;

    /* Item Services */
    public Boolean getItemIDs() {
        RestTemplate restTemplate = new RestTemplate();
        String url = OSRS_ITEM_ID_INFO_URL;
        int i = 0;

        try {
            String response = restTemplate.getForObject(url, String.class);
            response = response.substring(3, response.length() - 2); // Trim {} at beginning and end of the response.
            String[] itemIdDataList = response != null ? response.split(",\n\t") : new String[0];

            for (i = 2; i < itemIdDataList.length; i++) {
                String[] rawItemId = itemIdDataList[i].split(": ");
                ItemId itemId = new ItemId(rawItemId[0].substring(1, rawItemId[0].length()-1), Integer.parseInt(rawItemId[1]));

                this.itemRepository.save(itemId);
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
