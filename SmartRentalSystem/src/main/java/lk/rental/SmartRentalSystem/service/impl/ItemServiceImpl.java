package lk.rental.SmartRentalSystem.service.impl;

import lk.rental.SmartRentalSystem.controller.request.CreateItemRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateItemRequest;
import lk.rental.SmartRentalSystem.exception.CategoryNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.model.Category;
import lk.rental.SmartRentalSystem.model.Item;
import lk.rental.SmartRentalSystem.model.enums.AvailabilityStatus;
import lk.rental.SmartRentalSystem.repository.CategoryRepository;
import lk.rental.SmartRentalSystem.repository.ItemRepository;
import lk.rental.SmartRentalSystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public void add(Long id, CreateItemRequest createItemRequest) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new CategoryNotFoundException("Category Not Found..!")
        );

        Item item = new Item();
        item.setItemName(createItemRequest.getItemName());
        item.setPricePerDay(createItemRequest.getPricePerDay());
        item.setLocation(createItemRequest.getLocation());
        item.setImageUrl(createItemRequest.getImageUrl());
        item.setItemAvailability(AvailabilityStatus.AVAILABLE);
        item.setCategory(category);

        itemRepository.save(item);


    }

    @Override
    public Item findById(Long id) throws ItemNotFoundException {
        return itemRepository.findById(id).orElseThrow(
                ()->new ItemNotFoundException("Item Not Found..!")
        );

    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void update(Long id, UpdateItemRequest updateItemRequest) throws ItemNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(
                ()->new ItemNotFoundException("Item Not Found..!")
        );

        item.setItemName(updateItemRequest.getItemName());
        item.setPricePerDay(updateItemRequest.getPricePerDay());
        item.setLocation(updateItemRequest.getLocation());
        item.setImageUrl(updateItemRequest.getImageUrl());

        itemRepository.save(item);
    }

    @Override
    public void delete(Long id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(
                ()->new ItemNotFoundException("Item Not Found..!")
        );

        itemRepository.delete(item);
    }

    @Override
    public Page<Item> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Order.asc("pricePerDay")));
        return itemRepository.findAll(pageable);
    }
}
