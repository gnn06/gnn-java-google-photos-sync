package gnn.com.photos;

import com.google.photos.library.v1.proto.MediaItem;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DownloadManager {
    public static void download(List<MediaItem> toDownload, String folder) throws IOException {
        for (MediaItem photo : toDownload) {
            URL source;
            try {
                source = new URL(photo.getBaseUrl());
                File destination = new File(folder, photo.getId() + getFileExtension());
                FileUtils.copyURLToFile(source, destination);
            } catch (MalformedURLException e) {
                // TODO: 06/05/2019 log instead of stderr
                System.err.println("erreur " + photo + e.getMessage());
            }
        }
    }

    private static String getFileExtension() {
        // TODO: 06/05/2019 manage file extension from mimeType
        return ".jpg";
    }
}
