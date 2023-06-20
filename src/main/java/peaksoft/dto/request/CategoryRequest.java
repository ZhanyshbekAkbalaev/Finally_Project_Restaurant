package peaksoft.dto.request;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

@Builder
public record CategoryRequest(String name) {
}
