package gnn.com.photos.remote;

import com.google.common.collect.ImmutableList;
import com.google.photos.library.v1.PhotosLibraryClient;
import com.google.photos.library.v1.internal.InternalPhotosLibraryClient;
import com.google.photos.types.proto.Album;
import com.google.photos.types.proto.MediaItem;
import gnn.com.photos.model.Photo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class PhotosRemoteService {

    private PhotosLibraryClient client;

    private static final List<String> REQUIRED_SCOPES =
            ImmutableList.of(
                    "https://www.googleapis.com/auth/photoslibrary.readonly",
                    "https://www.googleapis.com/auth/photoslibrary.appendonly");

    public PhotosRemoteService() throws IOException, GeneralSecurityException {
        this.client = PhotosLibraryClientFactory.createClient("./code_secret_client.json", REQUIRED_SCOPES);
    }

    public ArrayList getRemotePhotos(String albumName) {

        InternalPhotosLibraryClient.ListAlbumsPagedResponse response = client.listAlbums();
        for (Album album : response.iterateAll()) {
            String title = album.getTitle();
            if (albumName.equals(title)) {
                String albumId = album.getId();
                //System.out.println("albumId=" + albumId);
                InternalPhotosLibraryClient.SearchMediaItemsPagedResponse responsePhotos = client.searchMediaItems(albumId);
                int count = 0;
                ArrayList result = new ArrayList();
                for (MediaItem item : responsePhotos.iterateAll()) {
                    String filename = item.getFilename();
                    result.add(new Photo(item.getBaseUrl(), item.getId()));
                    count++;
                }
                System.out.println("count=" + count);
                return result;
            }
        }
        System.err.println("album not found");
        return null;
    }

}
