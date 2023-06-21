package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api/menuItems")
@RequiredArgsConstructor
public class MenuItemAPI {
    private final MenuItemService menuItemService;

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse saveMenuItem(@RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.saveMenuItem(menuItemRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/getAll/{restaurantId}")
    public List<MenuItemResponse> getAll(@PathVariable Long restaurantId) {
        return menuItemService.getAllMenuItems(restaurantId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/getById/{menuItemId}")
    public MenuItemResponse getById(@PathVariable Long menuItemId) {
        return menuItemService.getByIdMenuItem(menuItemId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping("/update/{menuItemId}")
    public SimpleResponse update(@PathVariable Long menuItemId, @RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.updateMenuItem(menuItemId, menuItemRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/delete/{menuItemId}")
    public SimpleResponse delete(@PathVariable Long menuItemId) {
        return menuItemService.deleteMenuItem(menuItemId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/search/{word}")
    public List<MenuItemResponse> search(@PathVariable String word) {
        return menuItemService.searchAllByKeyWord(word);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/sort/{ascOrDesc}")
    public List<MenuItemResponse> sort(@PathVariable String ascOrDesc) {
        return menuItemService.sortByPrice(ascOrDesc);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/filter/{isVegetarian}")
    public List<MenuItemResponse> filter(@PathVariable Boolean isVegetarian) {
        return menuItemService.filterByIsVegetarian(isVegetarian);
    }
}