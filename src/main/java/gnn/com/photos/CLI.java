package gnn.com.photos;

import gnn.com.photos.remote.PhotosRemoteService;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class CLI {

    public static void main(String[] args) {
        System.out.println("begin");
        try {
            PhotosRemoteService prs;
            prs = new PhotosRemoteService();
            prs.getRemotePhotos();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

}
