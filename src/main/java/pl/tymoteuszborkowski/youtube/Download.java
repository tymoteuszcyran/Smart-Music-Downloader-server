package pl.tymoteuszborkowski.youtube;

import com.github.axet.vget.VGet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class Download {

    private static final String MP4_EXTENSION = ".mp4";


    public void downloadVideo(URL url){

        try {
            File file = createTempFile();
            VGet v = new VGet(url, new File("/home/tymek/Desktop/"));

            v.download();

        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }


    private static File createTempFile() throws IOException {
        File file = File.createTempFile(UUID.randomUUID().toString(), MP4_EXTENSION);
        file.deleteOnExit();

        return file;
    }
}
