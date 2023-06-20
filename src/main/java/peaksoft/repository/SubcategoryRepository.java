package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.SubcategoryResponse;
import peaksoft.entity.Subcategory;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("select new peaksoft.dto.response.SubcategoryResponse(s.id,s.name)" +
            "from Subcategory s " +
            "join s.category c " +
            "group by c having c.id = :categoryId" +
            " order by case when :ascOrDesc = 'asc' then s.name end asc ,case when :ascOrDesc = 'desc' then s.name end desc")
    List<SubcategoryResponse> getAllByCategoryId(Long categoryId,String ascOrDesc);

    @Query("select new peaksoft.dto.response.SubcategoryResponse(s.id,s.name)from Subcategory s where s.id = ?1")
    SubcategoryResponse getSubcategoryById(Long subcategoryId);
}