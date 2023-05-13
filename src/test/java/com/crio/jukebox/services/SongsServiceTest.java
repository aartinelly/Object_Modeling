package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.ArtistRepository;
import com.crio.jukebox.repositories.SongsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class SongsServiceTest {
    
    @Mock
    private AlbumRepository albumRepositoryMock;

    @Mock
    private ArtistRepository artistRepositoryMock;

    @Mock
    private SongsRepository songsRepositoryMock;
    
    @InjectMocks
    private SongsService songsService;

   
    @Test
    @DisplayName("Load data should load all songs")
    public void load_data(){
        

        // Album album = new Album("1","No.6 Collaborations Project");
        // Artist artist =  new Artist("1","Ed Sheeran");

        // Song song1 = new Song("1", "South of the Border", "Pop", album, artist, Arrays.asList("Ed Sheeran","Cardi.B","Camilla Cabello")); 
        // Song song2 =  new Song("2", "I Dont'Care", "pop", album, artist, Arrays.asList("Ed Sheeran","Justin Bieber")); 
        // Song song3 =  new Song("3", "Remember The Name", "pop", album, artist, Arrays.asList("Ed Sheeran","Eminem", "50Cent")); 
        // List<Song> expectedSong = Arrays.asList(song1, song2, song3);
        
        
        // when(albumRepositoryMock.save(any(Album.class))).thenReturn(album);
        // when(artistRepositoryMock.save(any(Artist.class))).thenReturn(artist);
        // when(songsRepositoryMock.save(song1)).thenReturn(song1);
        // when(songsRepositoryMock.save(song2)).thenReturn(song2);
        // when(songsRepositoryMock.save(song3)).thenReturn(song3);

        // List<Song> actualSong = songsService.loadSongs("TestSongs.csv");
        
        // Assertions.assertEquals(expectedSong, actualSong);
        // verify(albumRepositoryMock, times(1)).save(any(Album.class));
        // verify(artistRepositoryMock, times(1)).save(any(Artist.class));
        // verify(songsRepositoryMock, times(3)).save(any(Song.class));
    }
}
