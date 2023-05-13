package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Playlist extends BaseEntity{
    
    private String playlistName;
    private List<Song> songs;
    private boolean isActive;
    private int currentsong;

    public Playlist(String id, String playlistName, List<Song> songs){
        this(playlistName);
        this.id = id;
        this.songs = songs;
        this.isActive = false;
        this.currentsong = 0;
    }

    public Playlist(String playlistName){
        this.playlistName = playlistName;
        this.songs = new ArrayList<>();
    }

    public Playlist(String id, String playlistName, List<Song> songs, boolean isActive, int currentSong){
        this(id, playlistName, songs);
        this.isActive = isActive;
        this.currentsong = currentSong;
    }

    public void addSong(Song s){
        this.songs.add(s);
    }

    public void deleteSong(Song s){
        this.songs.remove(s);
    }

    public boolean checkSongExists(Song s){
        Optional<Song> song = this.songs.stream().filter(p -> p.getId().equals(s.getId()))
        .findAny();
        
        return song.isEmpty();
        
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getCurrentsong() {
        return currentsong;
    }

    public void setCurrentsong(int currentsong) {
        this.currentsong = currentsong;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + currentsong;
        result = prime * result + ((playlistName == null) ? 0 : playlistName.hashCode());
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
        Playlist other = (Playlist) obj;
        if (currentsong != other.currentsong)
            return false;
        if (playlistName == null) {
            if (other.playlistName != null)
                return false;
        } else if (!playlistName.equals(other.playlistName))
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
        return "Playlist [currentsong=" + currentsong + ", playlistName=" + playlistName
                + ", songs=" + songs + "]";
    }
    
    
}
