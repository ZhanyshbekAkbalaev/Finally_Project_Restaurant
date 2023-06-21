package peaksoft.dto.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record StopListRequest(
        String reason,
        ZonedDateTime date,
        Long menuItemId
) {
}