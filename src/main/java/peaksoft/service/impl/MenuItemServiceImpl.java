package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.Subcategory;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.MenuItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public SimpleResponse saveMenuItem(MenuItemRequest menuItemRequest) {
        Restaurant restaurant = restaurantRepository.findById(menuItemRequest.restaurantId()).orElseThrow(()-> new NotFoundException("Restaurant with id " + menuItemRequest.restaurantId() + " is not found."));
        Subcategory subcategory = subcategoryRepository.findById(menuItemRequest.subcategoryId()).orElseThrow(() -> new NotFoundException("Subcategory with id " + menuItemRequest.subcategoryId() + " is not found."));
        if (menuItemRequest.price() < 0) {
            throw new BadRequestException(String.format("MenuItem with price %s amount must be greater than 0", menuItemRequest.price()));
        }
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemRequest.name())
                .image(menuItemRequest.image())
                .price(menuItemRequest.price())
                .description(menuItemRequest.description())
                .isVegetarian(menuItemRequest.isVegetarian())
                .build();
        restaurant.setMenuItems(List.of(menuItem));
        menuItem.setRestaurant(restaurant);
        subcategory.setMenuItems(List.of(menuItem));
        menuItem.setSubcategory(subcategory);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("MenuItem with name %s is successfully saved.", menuItem.getName()))
                .build();
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems(Long restaurantId) {
        return menuItemRepository.getAllMenuItems(restaurantId);
    }

    @Override
    public MenuItemResponse getByIdMenuItem(Long menuItemId) {
       return menuItemRepository.getMenuItemById(menuItemId).orElseThrow();
    }

    @Override
    public SimpleResponse updateMenuItem(Long menuItemId, MenuItemRequest menuItemRequest) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new NotFoundException("MenuItem with id " + menuItemId + " is not found."));
        menuItem.setName(menuItemRequest.name());
        menuItem.setImage(menuItemRequest.image());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setIsVegetarian(menuItemRequest.isVegetarian());
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("MenuItem with name %s is successfully saved.", menuItem.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new NotFoundException("MenuItem with id " + menuItemId + " is not found."));
        menuItemRepository.delete(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("MenuItem with name %s is successfully deleted.", menuItem.getName()))
                .build();
    }

    @Override
    public List<MenuItemResponse> searchAllByKeyWord(String keyWord) {
        return menuItemRepository.searchAllByKeyWord(keyWord);
    }

    @Override
    public List<MenuItemResponse> sortByPrice(String ascOrDesc) {
        return menuItemRepository.sortByAge(ascOrDesc);
    }

    @Override
    public List<MenuItemResponse> filterByIsVegetarian(Boolean isVegetarian) {
        return menuItemRepository.filter(isVegetarian);
    }
}