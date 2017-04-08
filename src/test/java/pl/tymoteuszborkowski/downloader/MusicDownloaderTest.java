package pl.tymoteuszborkowski.downloader;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MusicDownloaderTest {


    @Test
    public void downloadTrackTest(){
        MusicDownloader musicDownloader = new MusicDownloader();
        musicDownloader.downloadMp3("https://www.youtube.com/watch?v=s_T5bpmBrwI");
    }




}
