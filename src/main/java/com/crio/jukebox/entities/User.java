package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User extends BaseEntity{
    private String username;
    private List<Playlist> playlist;

    public User(String id, String username){
        this(username);
        this.id = id;
        this.playlist = new ArrayList<>();
    }

    public User(String username){
        this.username = username;
        this.playlist = new ArrayList<>();
    }

    public User(String id, String name, List<Playlist> playlists){
        this(id,name);
        this.playlist = playlists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public void addPlaylist(Playlist playlist){
        this.playlist.add(playlist);
    }

    public Optional<Playlist> getGivenPlaylist(Playlist playlist){
        return this.playlist.stream().filter(p -> p.getId().equals(playlist.getId()))
        .findAny();
    }

    public void removePlaylist(Playlist playlist){
        this.playlist.remove(playlist);
    }

    public Optional<Playlist> getActivePlaylist(){
        return this.playlist.stream().filter(p -> p.isActive())
        .findAny();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playlist == null) ? 0 : playlist.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (playlist == null) {
            if (other.playlist != null)
                return false;
        } else if (!playlist.equals(other.playlist))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [playlist=" + playlist + ", username=" + username + "]";
    }
        
   
}
