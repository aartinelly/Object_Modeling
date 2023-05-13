package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Artist;

public class ArtistRepository implements CRUDRepository<Artist, String>{

    private final Map<String, Artist> artistMap;
    private Integer autoIncrement = 0;

    public ArtistRepository(){
        artistMap = new HashMap<>();
    }

    @Override
    public Artist save(Artist entity) {
        // TODO Auto-generated method stub
        Artist art = findByName(entity.getArtistName()).orElse(null);
        if (art==null) {
            if (entity.getId()==null) {
                autoIncrement++;
                Artist artist = new Artist(Integer.toString(autoIncrement), entity.getArtistName());
                artistMap.put(artist.getId(), artist);
                return artist;
            }
            Artist artist = new Artist(entity.getId(), entity.getArtistName());
            artistMap.put(artist.getId(), artist);
            return artist;    
        }
        else{
            return art;
        }
    }

    public Optional<Artist> findByName(String name){
        return artistMap.entrySet().stream()
        .filter(v -> v.getValue().getArtistName().equals(name))
        .map(val -> val.getValue())
        .findFirst();
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Artist entity) {
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
    public List<Artist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Artist> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
