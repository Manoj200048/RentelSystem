package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreateItemRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateItemRequest;
import lk.rental.SmartRentalSystem.controller.response.ViewAllItem;
import lk.rental.SmartRentalSystem.controller.response.ViewAllItemResponse;
import lk.rental.SmartRentalSystem.controller.response.ViewItemResponse;
import lk.rental.SmartRentalSystem.exception.CategoryNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.model.Item;
import lk.rental.SmartRentalSystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/{category-id}/add",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@PathVariable("category-id")Long id, @RequestBody CreateItemRequest createItemRequest) throws CategoryNotFoundException {
       itemService.add(id,createItemRequest);
    }

    @GetMapping(value = "/{item-id}",headers = "X-Api-Version=v1")
    public ViewItemResponse getById(@PathVariable("item-id") Long id) throws ItemNotFoundException {
        Item item = itemService.findById(id);

        return ViewItemResponse
                .builder()
                .itemName(item.getItemName())
                .pricePerDay(item.getPricePerDay())
                .location(item.getLocation())
                .imageUrl(item.getImageUrl())
                .itemAvailability(item.getItemAvailability())
                .build();

    }

    @GetMapping(headers = "X-Api-Version=v1")
    public ViewAllItemResponse getAll(){
        List<Item> itemList = itemService.findAll();

        List<ViewAllItem> allItemList = new ArrayList<>();

        for(Item item : itemList){

            ViewAllItem viewAllItem = ViewAllItem
                    .builder()
                    .itemName(item.getItemName())
                    .pricePerDay(item.getPricePerDay())
                    .location(item.getLocation())
                    .imageUrl(item.getImageUrl())
                    .itemAvailability(item.getItemAvailability())
                    .build();
            allItemList.add(viewAllItem);
        }

        return ViewAllItemResponse
                .builder()
                .viewAllItemList(allItemList)
                .build();
    }

    @PutMapping(value = "/{item-id}",headers = "X-Api-Version=v1")
    public void update(@PathVariable("item-id")Long id, @RequestBody UpdateItemRequest updateItemRequest){
        itemService.update(id,updateItemRequest);
    }

    @DeleteMapping(value = "/{item-id}",headers = "X-Api-Version=v1")
    public void delete(@PathVariable("item-id")Long id){
        itemService.delete(id);
    }
}
