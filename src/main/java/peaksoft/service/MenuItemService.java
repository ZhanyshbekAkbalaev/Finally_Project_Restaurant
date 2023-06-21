package peaksoft.service;

import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;


import java.util.List;

public interface MenuItemService {
    SimpleResponse saveMenuItem(Long restaurantId , MenuItemRequest menuItemRequest);
    List<MenuItemResponse> getAllMenuItems(Long restaurant);
    MenuItemResponse getByIdMenuItem(Long menuItemId);
    SimpleResponse updateMenuItem(Long menuItemId,MenuItemRequest menuItemRequest);
    SimpleResponse deleteMenuItem(Long menuItemId);
    List<MenuItemResponse> searchAllByKeyWord(String keyWord);
    List<MenuItemResponse> sortByPrice(String ascOrDesc);
    List<MenuItemResponse> filterByIsVegetarian(Boolean isVegetarian);
}