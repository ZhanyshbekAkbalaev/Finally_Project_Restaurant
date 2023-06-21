package peaksoft.dto.request;

import lombok.Builder;

@Builder
public record RestaurantRequest(
        String name,
        String location,
        int numberOfUsers,
        String restType,
        int service
) {
}
