package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;
    private final UserRepository userRepository;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        if (!repository.findAll().isEmpty()) {
            throw new BadRequestException("You mast save only 1 Restaurant");
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with email: %s not found".formatted(email)));
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());
        restaurant.setNumberOfEmployees(restaurantRequest.numberOfUsers());
        user.setRestaurant(restaurant);
        repository.save(restaurant);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name : %s successfully saved ...!",
                        restaurantRequest.name()))
                .build();
    }

    @Override
    public List<RestaurantResponse> findAllRestaurants() {
        return repository.findAllRestaurants();
    }

    @Override
    public SimpleResponse deleteRestaurantById(Long restaurantId) {
        repository.deleteById(restaurantId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK).message(String.format("Restaurant with Id: %s is successfully deleted", restaurantId)).build();
    }

    @Override
    public SimpleResponse updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = repository.findById(restaurantId).orElseThrow(() -> new NoSuchElementException(String.format("Restaurant with id: %s in not found", restaurantId)));
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
