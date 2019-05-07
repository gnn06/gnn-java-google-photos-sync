package gnn.com.photos.local;

import gnn.com.photos.model.Photo;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

public class PhotosLocalService {

    public ArrayList<Photo> getLocalPhotos(String folder) {
        File file = new File(folder);
        String[] list = file.list();
        ArrayList result = new ArrayList(list.length);
        for (String filename : list) {
            String id = FilenameUtils.removeExtension(filename);
            result.add(new Photo(null, id));
        }
        return result;
    }

    public void delete(Object toDelete, String folder) {

    }
}
