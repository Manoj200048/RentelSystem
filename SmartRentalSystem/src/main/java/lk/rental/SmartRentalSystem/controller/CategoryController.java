package lk.rental.SmartRentalSystem.controller;

import lk.rental.SmartRentalSystem.controller.request.CreateCategoryRequest;
import lk.rental.SmartRentalSystem.controller.request.UpdateCategoryRequest;
import lk.rental.SmartRentalSystem.controller.response.ViewAllCategory;
import lk.rental.SmartRentalSystem.controller.response.ViewAllCategoryListResponse;
import lk.rental.SmartRentalSystem.controller.response.ViewAllUserListResponse;
import lk.rental.SmartRentalSystem.controller.response.ViewCategoryResponse;
import lk.rental.SmartRentalSystem.exception.CategoryNotFoundException;
import lk.rental.SmartRentalSystem.model.Category;
import lk.rental.SmartRentalSystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/add",headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
       categoryService.add(createCategoryRequest);
    }

    @GetMapping(value = "/{category-id}",headers = "X-Api-Version=v1")
    public ViewCategoryResponse getCategoryById(@PathVariable("category-id")Long id) throws CategoryNotFoundException {
        Category category = categoryService.findById(id);

        return ViewCategoryResponse
                .builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();

    }

    @GetMapping(headers = "X-Api-Version=v1")
    public ViewAllCategoryListResponse getAll(){
        List<Category> categoryList = categoryService.findAll();

        List<ViewAllCategory> viewAllCategoryList = new ArrayList<>();

        for (Category category : categoryList){
            ViewAllCategory viewAllCategory = ViewAllCategory
                    .builder()
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();

            viewAllCategoryList.add(viewAllCategory);
        }

        return ViewAllCategoryListResponse
                .builder()
                .viewAllCategoryResponseList(viewAllCategoryList)
                .build();

    }

    @PutMapping(value = "/{category-id}",headers = "X-Api-Version=v1")
    public void update(@PathVariable("category-id")Long id, @RequestBody UpdateCategoryRequest updateCategoryRequest) throws CategoryNotFoundException{
        categoryService.update(id,updateCategoryRequest);
    }

    @DeleteMapping(value = "/{category-id}",headers = "X-Api-Version=v1")
    public void delete(@PathVariable("category-id") Long id) throws CategoryNotFoundException{
        categoryService.delete(id);
    }
}
