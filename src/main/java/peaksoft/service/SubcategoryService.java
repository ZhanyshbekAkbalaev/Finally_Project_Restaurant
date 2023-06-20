package peaksoft.service;

import peaksoft.dto.request.SubcategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubcategoryResponse;

import java.util.List;

public interface SubcategoryService {
    SimpleResponse saveSubcategory(Long categoryId, SubcategoryRequest subcategoryRequest);
    List<SubcategoryResponse> getSubcategories(Long categoryId,String ascOrDesc);
    SubcategoryResponse getSubcategoryById(Long subcategoryId);
    SimpleResponse updateSubcategory(Long subcategoryId,SubcategoryRequest subcategoryRequest);
    SimpleResponse deleteSubcategory(Long subcategoryId);

}