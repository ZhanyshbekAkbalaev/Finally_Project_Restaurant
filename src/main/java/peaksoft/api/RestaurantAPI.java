package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantAPI {
    private final RestaurantService restaurantService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.saveRestaurant(restaurantRequest);
        return restaurantService.saveRestaurant(restaurantRequest);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RestaurantResponse> findAllRestaurants() {
        return restaurantService.findAllRestaurants();
    }


    @GetMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantResponse gedRestaurantById(@PathVariable Long restaurantId) {
        return restaurantService.findById(restaurantId);
    }

    @DeleteMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteRestaurantById(@PathVariable Long restaurantId) {
        return restaurantService.deleteRestaurantById(restaurantId);
    }

    @PutMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.updateRestaurant(restaurantId, restaurantRequest);
    }


}
