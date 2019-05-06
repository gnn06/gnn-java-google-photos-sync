package gnn.com.photos;

import com.google.photos.library.v1.proto.MediaItem;
import gnn.com.photos.local.PhotosLocalService;
import gnn.com.photos.remote.PhotosRemoteService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

public class CLI {

    static String folder = "c:/temp/";

    public static void main(String[] args) {
        System.out.println("begin");
        try {
            PhotosRemoteService prs = new PhotosRemoteService();
            PhotosLocalService pls = new PhotosLocalService();
            List remote = prs.getRemotePhotos("Wallpaper");
            List local = pls.getLocalPhotos();
            RemoteLocalSynchronizer sync = new RemoteLocalSynchronizer(remote, local);
            pls.delete(sync.getToDelete(), folder);
            DownloadManager.download(sync.getToDownload(), folder);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

}
