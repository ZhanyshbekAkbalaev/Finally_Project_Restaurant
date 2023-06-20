package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select new peaksoft.dto.response.RestaurantResponse(r.name,r.location,r.restType,r.numberOfEmployees) from Restaurant r")
    List<RestaurantResponse> findAllRestaurants();

    @Query("select new peaksoft.dto.response.RestaurantResponse(r.name,r.location,r.restType,r.numberOfEmployees)from Restaurant r where r.id = ?1")
    RestaurantResponse findRestaurantById(Long restaurantId);
}