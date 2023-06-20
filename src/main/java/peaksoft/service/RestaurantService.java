package peaksoft.service;

import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface RestaurantService {
    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> findAllRestaurants();
    SimpleResponse deleteRestaurantById(Long restaurantId);
    SimpleResponse updateRestaurant(Long restaurantId,RestaurantRequest restaurantRequest);
    RestaurantResponse findById(Long restaurantId);

}
