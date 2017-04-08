package pl.tymoteuszborkowski.spotify;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class SpotifyService {

    private static final String SPOTIFY_QUERY = "https://api.spotify.com/v1/search";
    private static final String DURATION_INDEX = "duration_ms";
    private static final String COMMA = ",";

    private final RestTemplate restTemplate = new RestTemplate();

    public Long searchForTrackDuration(String artist, String title){
        String uri = buildUriWithTrackInfo(artist, title);
        String jsonResponse = restTemplate.getForObject(uri, String.class);

        return findTrackDuration(jsonResponse);
    }

    private String buildUriWithTrackInfo(String artist, String title) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SPOTIFY_QUERY)
                .queryParam("q", artist)
                .queryParam(title)
                .queryParam("type", "track")
                .queryParam("limit", 2)
                .queryParam("duration_ms");

        return builder.toUriString();
    }

    private long findTrackDuration(final String body) {
        long seconds = 0;
        if (body.contains(DURATION_INDEX)) {
            int indexOfDuration = body.indexOf(DURATION_INDEX);
            int indexOfComma = body.indexOf(COMMA, indexOfDuration);
            String durationResponse = body.substring(indexOfDuration, indexOfComma);
            String secondsStr = durationResponse.replaceAll("[^\\d.]", "");

            seconds = Long.parseLong(secondsStr);
            seconds /= 1000;
        }

        return seconds;
    }


}
