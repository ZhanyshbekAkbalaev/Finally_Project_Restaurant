package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.repository.MenuItemRepository;
import peaksoft.service.MenuItemService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    @Override
    public SimpleResponse saveMenuItem(Long restaurantId, MenuItemRequest menuItemRequest) {

        return null;
    }

    @Override
    public List<MenuItemResponse> getAllMenuItems(Long restaurant) {
        return null;
    }

    @Override
    public MenuItemResponse getByIdMenuItem(Long menuItemId) {
        return null;
    }

    @Override
    public SimpleResponse updateMenuItem(Long menuItemId, MenuItemRequest menuItemRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteMenuItem(Long menuItemId) {
        return null;
    }

    @Override
    public List<MenuItemResponse> searchAllByKeyWord(String keyWord) {
        return null;
    }

    @Override
    public List<MenuItemResponse> sortByPrice(String ascOrDesc) {
        return null;
    }

    @Override
    public List<MenuItemResponse> filterByIsVegetarian(Boolean isVegetarian) {
        return null;
    }
}
