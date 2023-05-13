package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class Album extends BaseEntity{
    private String albumName;
    private List<Song> songs;


    public Album(String id, String albumName){
        this.id = id;
        this.albumName = albumName;
        this.songs = new ArrayList();

    }

    public void addSongs(String songName){
        
    }

    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Album other = (Album) obj;
        if (albumName == null) {
            if (other.albumName != null)
                return false;
        } else if (!albumName.equals(other.albumName))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Album [albumName=" + albumName + ", songs=" + songs + "]";
    }

   

    

}
