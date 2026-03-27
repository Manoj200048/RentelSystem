package lk.rental.SmartRentalSystem.service.impl;

import lk.rental.SmartRentalSystem.controller.request.CreateCategoryRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateCategoryRequest;
import lk.rental.SmartRentalSystem.exception.CategoryNotFoundException;
import lk.rental.SmartRentalSystem.model.Category;
import lk.rental.SmartRentalSystem.repository.CategoryRepository;
import lk.rental.SmartRentalSystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void add(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();

        category.setName(createCategoryRequest.getName());
        category.setDescription(createCategoryRequest.getDescription());

        categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(
                ()-> new CategoryNotFoundException("Category Not Found...!")
        );
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void update(Long id, UpdateCategoryRequest updateCategoryRequest) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new CategoryNotFoundException("Category Not Found...!")
        );
        category.setName(updateCategoryRequest.getName());
        category.setDescription(updateCategoryRequest.getDescription());

        categoryRepository.save(category);

    }

    @Override
    public void delete(Long id) throws CategoryNotFoundException {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new CategoryNotFoundException("Category Not Found...!")
        );

        categoryRepository.delete(category);
    }
}
