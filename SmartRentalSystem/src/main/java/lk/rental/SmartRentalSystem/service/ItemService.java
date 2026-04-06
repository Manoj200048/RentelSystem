package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreateItemRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateItemRequest;
import lk.rental.SmartRentalSystem.exception.CategoryNotFoundException;
import lk.rental.SmartRentalSystem.exception.ItemNotFoundException;
import lk.rental.SmartRentalSystem.model.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    void add(Long id, CreateItemRequest createItemRequest) throws CategoryNotFoundException;
    Item findById(Long id) throws ItemNotFoundException;
    List<Item> findAll();
    void update(Long id, UpdateItemRequest updateItemRequest) throws ItemNotFoundException;
    void delete(Long id) throws ItemNotFoundException;
    Page<Item>findAll(int page,int size);
}
