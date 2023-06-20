package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.SubcategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubcategoryResponse;
import peaksoft.service.SubcategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubcategoryAPI {
    private final SubcategoryService subcategoryService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse saveSubcategory(Long categoryId, SubcategoryRequest subcategoryRequest) {
        return subcategoryService.saveSubcategory(categoryId, subcategoryRequest);
    }

    @GetMapping("/{categoryId}/{ascOrDesc}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    public List<SubcategoryResponse> getAll(@PathVariable Long categoryId,@PathVariable String ascOrDesc) {
        return subcategoryService.getSubcategories(categoryId,ascOrDesc);
    }

    @GetMapping("/{subcategoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    public SubcategoryResponse getById(@PathVariable Long subcategoryId) {
        return subcategoryService.getSubcategoryById(subcategoryId);
    }

    @PutMapping("/{subcategoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse update(@PathVariable Long subcategoryId, @RequestBody SubcategoryRequest subcategoryRequest) {
        return subcategoryService.updateSubcategory(subcategoryId, subcategoryRequest);
    }

    @DeleteMapping("/{subcategoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse deleteById(@PathVariable Long subcategoryId) {
        return subcategoryService.deleteSubcategory(subcategoryId);
    }
}