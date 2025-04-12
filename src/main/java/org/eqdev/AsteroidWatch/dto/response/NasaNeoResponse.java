package org.eqdev.AsteroidWatch.dto.response;

import java.util.List;
import java.util.Map;
import org.eqdev.AsteroidWatch.dto.Asteroid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NasaNeoResponse {

    @JsonProperty("near_earth_objects")
    private Map<String, List<Asteroid>> nearEarthObjects;

    @JsonProperty("element_count")
    private Long elementCount;
}
