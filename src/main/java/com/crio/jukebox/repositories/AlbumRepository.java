package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Album;

public class AlbumRepository implements CRUDRepository<Album, String>{

    private final Map<String,Album> albumMap;
    private Integer autoIncrement = 0;

    public AlbumRepository(){
        albumMap = new HashMap<>();
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Album entity) {
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
    public List findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Album save(Album entity) {

        Album al = findByName(entity.getAlbumName()).orElse(null);
        // TODO Auto-generated method stub
        if (al==null) {
            if (entity.getId()==null) {
                autoIncrement++;
               Album album = new Album(Integer.toString(autoIncrement), entity.getAlbumName());
               albumMap.put(album.getId(), album);
               return album;
            }
            Album album = new Album(entity.getId(), entity.getAlbumName());
            return album;    
        }
        else{
            return al;
        }
    }

    public Optional<Album> findByName(String name){
       return albumMap.entrySet().stream()
        .filter(v -> v.getValue().getAlbumName().equals(name))
        .map(val -> val.getValue())
        .findFirst();
    }
    
}
