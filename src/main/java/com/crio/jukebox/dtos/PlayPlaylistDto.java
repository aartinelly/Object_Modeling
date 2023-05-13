package com.crio.jukebox.dtos;

import java.util.List;
import com.crio.jukebox.entities.Artist;

public class PlayPlaylistDto {
    private String songName;
    private String albumName;
    private List<String> featureArtist;

   public PlayPlaylistDto(String songName,  String albumName, List<String> featureArtist){
    this.songName = songName;
    this.albumName = albumName;
    this.featureArtist = featureArtist;
   }

public String getSongName() {
    return songName;
}

public void setSongName(String songName) {
    this.songName = songName;
}

public String getAlbumName() {
    return albumName;
}

public void setAlbumName(String albumName) {
    this.albumName = albumName;
}

public List<String> getFeatureArtist() {
    return featureArtist;
}

public void setFeatureArtist(List<String> featureArtist) {
    this.featureArtist = featureArtist;
}

   
}
