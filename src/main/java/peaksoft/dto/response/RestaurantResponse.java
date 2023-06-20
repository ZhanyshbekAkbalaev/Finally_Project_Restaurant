package peaksoft.dto.response;

public record RestaurantResponse(
        String name,
        String location,
        String restType,
        int numberOfEmployees
) {
}
