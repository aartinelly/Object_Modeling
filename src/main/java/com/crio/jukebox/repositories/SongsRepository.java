package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongsRepository implements CRUDRepository<Song, String>{

    private final Map<String,Song> songsMap;
    private Integer autoIncrement = 0;

    public SongsRepository(){
        this.songsMap = new LinkedHashMap<>();
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
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
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return songsMap.entrySet().stream().map(v -> v.getValue())
        .collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
       return Optional.ofNullable(songsMap.get(id));
    }

    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        if (entity.getId()==null) {
            autoIncrement++;
            Song song = new Song(Integer.toString(autoIncrement), entity.getSongName(), entity.getGenre(), entity.getAlbumName(), entity.getOwner(), entity.getFeatureArtist());
            songsMap.put(song.getId(), song);
            return song;
        }        
        Song song = new Song(entity.getId(), entity.getSongName(), entity.getGenre(), entity.getAlbumName(), entity.getOwner(), entity.getFeatureArtist());
        return song;
    }
    
}
