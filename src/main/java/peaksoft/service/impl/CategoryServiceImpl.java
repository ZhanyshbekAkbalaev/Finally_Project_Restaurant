package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with name %s is successfully saved.", category.getName()))
                .build();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category with id " + categoryId + " is not found!"));
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with name %s is successfully updated.", category.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Category with id %s not found!", categoryId))
                    .build();
        }
        categoryRepository.deleteById(categoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Category with id %s is successfully deleted.", categoryId))
                .build();
    }
}
