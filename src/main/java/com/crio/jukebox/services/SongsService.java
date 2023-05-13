package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.ArtistRepository;
import com.crio.jukebox.repositories.SongsRepository;

public class SongsService {

    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private SongsRepository songsRepository;

    public SongsService(AlbumRepository albumRepository, ArtistRepository artistRepository,
            SongsRepository songsRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songsRepository = songsRepository;
    }


    public List<Song> loadSongs(String fileName) {
       
        BufferedReader reader;
        try {
            FileReader file = new FileReader(fileName);
            reader = new BufferedReader(file);
            String line = reader.readLine();

            while (line != null) {
                List<String> song = Arrays.asList(line.split(","));

                String songName = song.get(0);
                String genre = song.get(1);
                Album album = albumRepository.save(new Album(null, song.get(2)));
                album.addSongs(songName);
                Artist artist = artistRepository.save(new Artist(null, song.get(3)));
                List<String> featureArtist = Arrays.asList(song.get(4).split("#"));


                Song s = new Song(null, songName, genre, album, artist, featureArtist);
                songsRepository.save(s);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songsRepository.findAll();
    }

}
