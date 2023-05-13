package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public class PlaylistRepository implements IPlaylistRepository{

    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;


    public PlaylistRepository(){
        playlistMap = new HashMap<>();
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        playlistMap.remove(entity.getId(), entity);
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public Playlist save(Playlist entity) {
        // TODO Auto-generated method stub
        if (entity.getId()==null) {
            autoIncrement++;
            Playlist playlist = new Playlist(Integer.toString(autoIncrement), entity.getPlaylistName(), entity.getSongs());
            playlistMap.put(playlist.getId(), playlist);
            return playlist;
        }
        
        Playlist playlist = new Playlist(entity.getId(), entity.getPlaylistName(), entity.getSongs());
        playlistMap.put(playlist.getId(), playlist);
        return playlist;
    }

    @Override
    public Playlist addSong(String playlistId, Song s) {
        // TODO Auto-generated method stub
       Playlist playlist = findById(playlistId).get();
       playlist.addSong(s);
       playlistMap.put(playlist.getId(), playlist);
        return playlist;
    }

    @Override
    public Playlist deleteSong(String playlistId, Song s) {
        // TODO Auto-generated method stub
        Playlist playlist = findById(playlistId).get();
        playlist.deleteSong(s);
        playlistMap.put(playlist.getId(), playlist);
        return playlist;
    }

    @Override
    public boolean checkIfSongExists(String playlistId, Song song) {
        // TODO Auto-generated method stub
       return playlistMap.entrySet().stream()
        .filter(p -> p.getKey().equals(playlistId))
        .anyMatch(s -> s.getValue().getSongs().contains(song));
        
    }

    @Override
    public Optional<Playlist> getActivePlaylist() {
        // TODO Auto-generated method stub
       return playlistMap.entrySet().stream()
        .filter(p -> p.getValue().isActive())
        .map(v -> v.getValue())
        .findFirst();
    }

  
}
