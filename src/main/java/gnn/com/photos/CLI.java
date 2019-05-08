package gnn.com.photos;

import gnn.com.photos.sync.Synchronizer;

public class CLI {

    public static void main(String[] args) {
        System.out.println("begin");
        new Synchronizer().sync("test", "c:/temp/");
        System.out.println("end");
    }

}
