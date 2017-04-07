package pl.tymoteuszborkowski.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    private Long duration_ms;

    public Long getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(Long duration_ms) {
        this.duration_ms = duration_ms;
    }
}
