package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public SimpleResponse saveStopList(StopListRequest stopListRequest) {
        MenuItem menuItem = menuItemRepository.findById(stopListRequest.menuItemId()).orElseThrow(() -> new NotFoundException("MenuItem with id " + stopListRequest.menuItemId() + " not found"));
        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.reason());
        stopList.setDate(stopListRequest.date().toLocalDate());
        menuItem.setStopList(stopList);
        stopList.setMenuItem(menuItem);
        stopListRepository.save(stopList);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("StopList with reason %s is successfully saved.",stopList.getReason()))
                .build();
    }

    @Override
    public List<StopListResponse> getAllStopList() {
        return null;
    }

    @Override
    public StopListResponse getStopListById(Long stopListId) {
        return null;
    }

    @Override
    public SimpleResponse updateStopList(Long stopListId, StopListRequest stopListRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteStopList(Long stopListId) {
        return null;
    }
}
