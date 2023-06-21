package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m where m.restaurant.id = :restaurantId")
    List<MenuItemResponse> getAllMenuItems(Long restaurantId);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m where m.id = :menuItemId")
    Optional<MenuItemResponse> getMenuItemById(Long menuItemId);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m where m.name ilike concat('%',:keyWord,'%')or m.description ilike concat('%',:keyWord,'%')or m.subcategory.name ilike concat('%',:keyWord,'%')or m.subcategory.category.name ilike concat('%',:keyWord,'%')")
    List<MenuItemResponse> searchAllByKeyWord(@Param("keyWord") String keyWord);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m order by case when :ascOrDesc = 'asc' then m.price end asc ,case when :ascOrDesc = 'desc' then m.price end desc")
    List<MenuItemResponse> sortByAge(@Param("ascOrDesc") String ascOrDesc);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m where m.isVegetarian = :isVegetarian")
    List<MenuItemResponse> filter(@Param("isVegetarian") Boolean isVegetarian);
}