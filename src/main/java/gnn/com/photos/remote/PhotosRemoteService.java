package gnn.com.photos.remote;

import com.google.api.client.util.Lists;
import com.google.common.collect.ImmutableList;
import com.google.photos.library.v1.PhotosLibraryClient;
import com.google.photos.library.v1.internal.InternalPhotosLibraryClient;
import com.google.photos.library.v1.proto.Album;
import com.google.photos.library.v1.proto.MediaItem;

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
        this.client = PhotosLibraryClientFactory.createClient("./client_secret.json", REQUIRED_SCOPES);
    }

    public List getRemotePhotos(String albumName) {

        InternalPhotosLibraryClient.ListAlbumsPagedResponse response = client.listAlbums();
        for (Album album : response.iterateAll()) {
            String title = album.getTitle();
            if (albumName.equals(title)) {
                String albumId = album.getId();
                //System.out.println("albumId=" + albumId);
                InternalPhotosLibraryClient.SearchMediaItemsPagedResponse responsePhotos = client.searchMediaItems(albumId);
                int count = 0;
                List result = new ArrayList();
                for (MediaItem item : responsePhotos.iterateAll()) {
                    String filename = item.getFilename();
                    result.add(item);
                    count++;
                }
                System.out.println("count=" + count);
                return result;
            }
        }
        return null;
    }

}
