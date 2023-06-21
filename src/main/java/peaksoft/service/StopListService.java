package peaksoft.service;

import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;

import java.util.List;

public interface StopListService {
    SimpleResponse saveStopList(StopListRequest stopListRequest);
    List<StopListResponse> getAllStopList();
    StopListResponse getStopListById(Long stopListId);
    SimpleResponse updateStopList(Long stopListId,StopListRequest stopListRequest);
    SimpleResponse deleteStopList(Long stopListId);
}
