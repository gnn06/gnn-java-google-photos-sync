package gnn.com.photos.sync;

import gnn.com.photos.local.PhotosLocalService;
import gnn.com.photos.remote.PhotosRemoteService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class Synchronizer {

    // TODO: 07/05/2019 managed updated photo if possible
    public void sync(String albumName, String folder) {
        try {
            PhotosRemoteService prs = new PhotosRemoteService();
            PhotosLocalService pls = new PhotosLocalService();
            ArrayList remote = prs.getRemotePhotos(albumName);
            ArrayList local = pls.getLocalPhotos(folder);
            DiffCalculator sync = new DiffCalculator(remote, local);
            pls.delete(sync.getToDelete(), folder);
            DownloadManager.download(sync.getToDownload(), folder);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
