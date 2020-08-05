package org.weishe.baselibrary;

import java.io.Serializable;
import java.util.ArrayList;

public class AlbumInfo implements Serializable {
    private String name;
    private int count;
    private ArrayList<String> pathList;

    public ArrayList<String> getPathList() {
        if (pathList == null) {
            return new ArrayList<>();
        }
        return pathList;
    }

     void setPathList(ArrayList<String> pathList) {
        this.pathList = pathList;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
