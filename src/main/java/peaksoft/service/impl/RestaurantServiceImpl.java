package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.exception.BadRequestException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        if (!repository.findAll().isEmpty()) {
            throw new BadRequestException("Restaurant one");
        }
            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantRequest.name());
            restaurant.setLocation(restaurantRequest.location());
            restaurant.setRestType(restaurantRequest.restType());
            restaurant.setService(restaurantRequest.service());
            repository.save(restaurant);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message(String.format("Restaurant with name %s is successfully saved!", restaurant.getName()))
                    .build();
    }

    @Override
    public List<RestaurantResponse> findAllRestaurants() {
        return repository.findAllRestaurants();
    }

    @Override
    public SimpleResponse deleteRestaurantById(Long restaurantId) {
        if (!repository.existsById(restaurantId)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Restaurant with id: %s  is NOT FOUND", restaurantId)).build();
        }
        repository.deleteById(restaurantId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK).message(String.format("Restaurant with Id: %s is successfully deleted", restaurantId)).build();
    }

    @Override
    public SimpleResponse updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = repository.findById(restaurantId).orElseThrow(() -> new NoSuchElementException(String.format(
                "Restaurant with id: $s in NOT", restaurantId
        )));
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());
        repository.save(restaurant);
        return SimpleResponse.builder()
                .status(HttpStatus.OK).message(String.format(
                        "Restaurant with id: %s is updated...", restaurantRequest.name())).build();
    }

    @Override
    public RestaurantResponse findById(Long restaurantId) {
        return repository.findRestaurantById(restaurantId);
    }
}
