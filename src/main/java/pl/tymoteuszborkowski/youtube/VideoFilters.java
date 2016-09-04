package pl.tymoteuszborkowski.youtube;

import com.google.api.services.youtube.model.Video;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class VideoFilters {

    private static final String YOUTUBE_URL_PREFIX = "https://www.youtube.com/watch?v=";
    private static final String HD_DEFINITION = "hd";


    public List<Video> getBestQualityVideos(List<Video> videos){
        List<Video> hdVideos = new ArrayList<>();
        for(Video video : videos){

            String definition = video.getContentDetails().getDefinition();
            if(definition.equals(HD_DEFINITION))
                hdVideos.add(video);
        }

        return hdVideos;
    }


    public List<Video> sortByLength(List<Video> videos) {
        Map<String, Integer> durations = new HashMap<>();

        for(Video video : videos){
            String duration = video.getContentDetails().getDuration();
            String videoId = video.getId();
            Period period = ISOPeriodFormat.standard().parsePeriod(duration);
            int seconds = period.getSeconds() + (period.getMinutes()*60);

            durations.put(videoId, seconds);
        }

        Map<String, Integer> sortedMap = this.sortByValue(durations);

        for(Integer value : sortedMap.values())
            System.out.println(value);


        return new LinkedList<>();
    }


    private void getUrls(List<Video> videos) throws MalformedURLException {
        URL url;

        for(Video video : videos){
           String videoId =  video.getId();
            url = new URL(YOUTUBE_URL_PREFIX +videoId);

            System.out.println(url.toString());
        }
    }

    private URL getURL(Video video) throws MalformedURLException{
        String videoId = video.getId();

        return new URL(YOUTUBE_URL_PREFIX + videoId);
    }


    private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }




}
