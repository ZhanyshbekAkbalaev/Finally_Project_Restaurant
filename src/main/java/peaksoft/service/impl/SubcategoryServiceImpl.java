package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.SubcategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubcategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.Subcategory;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.SubcategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse saveSubcategory(Long categoryId, SubcategoryRequest subcategoryRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category with id " + categoryId + "is not found!"));
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryRequest.name());
        category.setSubcategories(List.of(subcategory));
        subcategory.setCategory(category);
        subcategoryRepository.save(subcategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Subcategory with name %s is successfully saved.", subcategory.getName()))
                .build();
    }

    @Override
    public List<SubcategoryResponse> getSubcategories(Long categoryId,String ascOrDesc) {
        if (!categoryRepository.existsById(categoryId)){
            throw new NotFoundException("Category with id "+ categoryId + " is not found!");
        }
        return subcategoryRepository.getAllByCategoryId(categoryId,ascOrDesc);
    }

    @Override
    public SubcategoryResponse getSubcategoryById(Long subcategoryId) {
        if (!subcategoryRepository.existsById(subcategoryId)){
            throw new NotFoundException("Subcategory with id "+ subcategoryId + " is not found!");
        }
        return subcategoryRepository.getSubcategoryById(subcategoryId);
    }

    @Override
    public SimpleResponse updateSubcategory(Long subcategoryId, SubcategoryRequest subcategoryRequest) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(() -> new NotFoundException("Subcategory with id " + subcategoryId + " is not found!"));
        subcategory.setName(subcategoryRequest.name());
        subcategoryRepository.save(subcategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Subcategory with name %s is successfully saved.", subcategory.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteSubcategory(Long subcategoryId) {
        if (!subcategoryRepository.existsById(subcategoryId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Subcategory with id %s not found", subcategoryId))
                    .build();
        }
        subcategoryRepository.deleteById(subcategoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Subcategory with id %s is successfully deleted.", subcategoryId))
                .build();
    }
}
