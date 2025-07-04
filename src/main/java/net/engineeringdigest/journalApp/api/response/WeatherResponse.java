package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;

        private List<String> weather_descriptions;

        private int feelslike;
    }


}


