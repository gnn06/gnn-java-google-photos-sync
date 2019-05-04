import com.google.common.collect.ImmutableList;
import com.google.photos.library.v1.PhotosLibraryClient;
import com.google.photos.library.v1.internal.InternalPhotosLibraryClient;
import com.google.photos.library.v1.proto.Album;
import com.google.photos.library.v1.proto.MediaItem;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class CLI {

    private static final List<String> REQUIRED_SCOPES =
            ImmutableList.of(
                    "https://www.googleapis.com/auth/photoslibrary.readonly",
                    "https://www.googleapis.com/auth/photoslibrary.appendonly");

    public static void main(String[] args) {
        System.out.println("begin");
        try {
            PhotosLibraryClient client =
                    PhotosLibraryClientFactory.createClient("./client_secret.json", REQUIRED_SCOPES);

            InternalPhotosLibraryClient.ListAlbumsPagedResponse response = client.listAlbums();
            for (Album album : response.iterateAll()) {
                String title = album.getTitle();
                if ("Wallpaper".equals(title)) {
                    String albumId = album.getId();
                    System.out.println(albumId);
                    InternalPhotosLibraryClient.SearchMediaItemsPagedResponse responsePhotos = client.searchMediaItems(albumId);
                    int count = 0;
                    for (MediaItem item : responsePhotos.iterateAll()) {
                        String filename = item.getFilename();
                        System.out.println(filename);
                        count++;
                    }
                    System.out.println("count=" + count);
                }
            }
            System.out.println("end");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
