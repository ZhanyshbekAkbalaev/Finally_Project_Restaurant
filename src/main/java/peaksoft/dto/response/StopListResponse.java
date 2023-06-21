package peaksoft.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record StopListResponse(
        Long  id,
        String reason,
        ZonedDateTime date,
        String menuitemName
) {
}
