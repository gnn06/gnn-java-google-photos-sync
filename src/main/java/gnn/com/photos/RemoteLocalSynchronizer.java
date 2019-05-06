package gnn.com.photos;

import com.google.photos.library.v1.proto.MediaItem;

import java.util.List;

public class RemoteLocalSynchronizer {

    private List remote;
    private List local;

    private final List toDownload;
    private final List toDelete;

    /**
     * Determine le delta entre remote et local.
     * Returns :
     * - a list to download
     * - a list to delete
     * download new remote photo not in local, delete local not in remote.
     * how manage updated remote photo ?
     * photo.id is used
     * @param remote
     * @param  local
     * @return
     */
    public RemoteLocalSynchronizer(List remote, List local) {
        this.remote = remote;
        this.local = local;
        // TODO: 06/05/2019 determine delta 
        this.toDownload = remote;
        this.toDelete = null;
    }

    public List<MediaItem> getToDownload() {
        return this.toDownload;
    }

    public Object getToDelete() {
        return this.toDelete;
    }
}
