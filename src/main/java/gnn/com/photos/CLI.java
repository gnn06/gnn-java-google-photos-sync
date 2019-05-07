package gnn.com.photos;

import gnn.com.photos.local.PhotosLocalService;
import gnn.com.photos.remote.PhotosRemoteService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class CLI {

    static String folder = "c:/temp/";

    public static void main(String[] args) {
        System.out.println("begin");
        try {
            PhotosRemoteService prs = new PhotosRemoteService();
            PhotosLocalService pls = new PhotosLocalService();
            ArrayList remote = prs.getRemotePhotos("Wallpaper");
            ArrayList local = pls.getLocalPhotos(folder);
            RemoteLocalSynchronizer sync = new RemoteLocalSynchronizer(remote, local);
            pls.delete(sync.getToDelete(), folder);
            DownloadManager.download(sync.getToDownload(), folder);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

}
