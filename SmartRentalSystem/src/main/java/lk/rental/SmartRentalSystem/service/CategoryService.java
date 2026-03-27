package lk.rental.SmartRentalSystem.service;

import lk.rental.SmartRentalSystem.controller.request.CreateCategoryRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateCategoryRequest;
import lk.rental.SmartRentalSystem.exception.CategoryNotFoundException;
import lk.rental.SmartRentalSystem.model.Category;

import java.util.List;

public interface CategoryService {
    void add(CreateCategoryRequest createCategoryRequest);
    Category findById(Long id) throws CategoryNotFoundException;
    List<Category> findAll();
    void update(Long id, UpdateCategoryRequest updateCategoryRequest) throws CategoryNotFoundException;
    void delete(Long id) throws CategoryNotFoundException;
}
