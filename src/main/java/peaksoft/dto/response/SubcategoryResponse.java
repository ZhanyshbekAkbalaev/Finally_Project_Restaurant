package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record SubcategoryResponse(Long id,String name) {
}
