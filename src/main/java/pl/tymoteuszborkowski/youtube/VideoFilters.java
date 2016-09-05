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
        Map<String, Integer> unsortedMap = new HashMap<>();
        List<Video> sortedList = new ArrayList<>();

        for(Video video : videos){
            String duration = video.getContentDetails().getDuration();
            String videoId = video.getId();
            Period period = ISOPeriodFormat.standard().parsePeriod(duration);
            int seconds = period.getSeconds() + (period.getMinutes()*60);

            unsortedMap.put(videoId, seconds);
        }

        Map<String, Integer> sortedMap = sortMapByValue(unsortedMap);

        for(String sortedVideoId : sortedMap.keySet()){
            for(Video video : videos){
                if(video.getId().equals(sortedVideoId)){
                    sortedList.add(video);
                }
            }
        }
        return sortedList;
    }


    public String getURL(Video video) throws MalformedURLException{
        String videoId = video.getId();
        return YOUTUBE_URL_PREFIX + videoId;
    }





    private <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
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
