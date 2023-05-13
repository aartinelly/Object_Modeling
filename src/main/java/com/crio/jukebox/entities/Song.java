package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{
    private String songName;
    private String genre;
    private Album albumName;
    private Artist owner;
    private List<String> featureArtist;

    public Song(String id, String songName, String genre, Album albumName, Artist owner, List<String> featureArtist){
        this.id = id;
        this.songName = songName;
        this.genre = genre;
        this.albumName = albumName;
        this.owner = owner;
        this.featureArtist = featureArtist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Album getAlbumName() {
        return albumName;
    }


    public void setAlbumName(Album albumName) {
        this.albumName = albumName;
    }


    public Artist getOwner() {
        return owner;
    }

    public void setOwner(Artist owner) {
        this.owner = owner;
    }

    public List<String> getFeatureArtist() {
        return featureArtist;
    }

    public void setFeatureArtist(List<String> featureArtist) {
        this.featureArtist = featureArtist;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = prime * result + ((featureArtist == null) ? 0 : featureArtist.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((songName == null) ? 0 : songName.hashCode());
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
        Song other = (Song) obj;
        if (albumName == null) {
            if (other.albumName != null)
                return false;
        } else if (!albumName.equals(other.albumName))
            return false;
        if (featureArtist == null) {
            if (other.featureArtist != null)
                return false;
        } else if (!featureArtist.equals(other.featureArtist))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (songName == null) {
            if (other.songName != null)
                return false;
        } else if (!songName.equals(other.songName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Song [albumName=" + albumName + ", featureArtist=" + featureArtist + ", genre="
                + genre + ", owner=" + owner + ", songName=" + songName + "]";
    }        
    
}
