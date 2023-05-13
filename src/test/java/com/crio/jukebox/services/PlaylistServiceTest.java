package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.SongsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {
    
    @Mock
    private IPlaylistRepository playlistRepositoryMock;

    @Mock
    private IUserRepository userRepositoryMock;

    @Mock
    private SongsRepository songsRepositoryMock;

    @InjectMocks
    private PlaylistService playlistService;

    @BeforeEach
    public void setup(){
        
    }


    @Test
    @DisplayName("create playlist method should create playlist")
    public void createPlaylist_ShouldReturnPlaylist(){

        User user = new User("1", "aarti");

        Album album = new Album("1", "album1");
        Artist artist = new Artist("1", "artist1");
        List<String> featureArtist = Arrays.asList("artist1", "artist2");


        Song song1 = new Song("1", "song1", "genre1", album, artist, featureArtist);
        Song song2 = new Song("2", "song2", "genre2", album, artist, featureArtist);
        Song song3 = new Song("3", "song3", "genre3", album, artist, featureArtist);
       
        Playlist expectedPlaylist = new Playlist("1", "playlist1", Arrays.asList(song1, song2, song3));


        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(user));
        when(songsRepositoryMock.findById(anyString())).thenReturn(Optional.of(song1));
        when(songsRepositoryMock.findById(anyString())).thenReturn(Optional.of(song2));
        when(songsRepositoryMock.findById(anyString())).thenReturn(Optional.of(song3));

        when(playlistRepositoryMock.save(any(Playlist.class))).thenReturn(expectedPlaylist);

        String actualPlaylistId = playlistService.createPlaylist("1", "1", "playlist1", Arrays.asList("1","2","3"));

        Assertions.assertEquals(expectedPlaylist.getId(), actualPlaylistId);

        verify(userRepositoryMock, times(1)).findById(anyString());
        verify(songsRepositoryMock, times(3)).findById(anyString());
        verify(playlistRepositoryMock, times(1)).save(any(Playlist.class));
    }

    @Test
    @DisplayName("delete playlist method should delete the playlist")
    public void deletePlaylist_ShouldDeletePlaylist(){

        User user = new User("1", "aarti");

        Album album = new Album("1", "album1");
        Artist artist = new Artist("1", "artist1");
        List<String> featureArtist = Arrays.asList("artist1", "artist2");


        Song song1 = new Song("1", "song1", "genre1", album, artist, featureArtist);
        Song song2 = new Song("2", "song2", "genre2", album, artist, featureArtist);
        Song song3 = new Song("3", "song3", "genre3", album, artist, featureArtist);
       
        Playlist playlist = new Playlist("1", "playlist1", Arrays.asList(song1, song2, song3));
        user.addPlaylist(playlist);

        //delete playlist
        String expected = "Delete Successful";
        List<Playlist> expectedPlaylist = new ArrayList<>();

        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));        
        doNothing().when(playlistRepositoryMock).delete(any(Playlist.class));

        String actualRes = playlistService.deletePlaylist("1", "1");
        List<Playlist> actualPlaylist = user.getPlaylist();

        Assertions.assertEquals(expected, actualRes);
        Assertions.assertEquals(expectedPlaylist, actualPlaylist);
        verify(userRepositoryMock, times(1)).findById(anyString());
        verify(playlistRepositoryMock, times(1)).findById(anyString());
        verify(playlistRepositoryMock, times(1)).delete(any(Playlist.class));
    }

    @Test
    @DisplayName("modify add method should add songs in playlist")
    public void modifyPlaylistAdd_ShouldAddSongsInPlaylist(){

        User user = new User("1", "aarti");

        Album album = new Album("1", "album1");
        Artist artist = new Artist("1", "artist1");
        List<String> featureArtist = Arrays.asList("artist1", "artist2");


        Song song1 = new Song("1", "song1", "genre1", album, artist, featureArtist);
        Song song2 = new Song("2", "song2", "genre2", album, artist, featureArtist);
        Song song3 = new Song("3", "song3", "genre3", album, artist, featureArtist);
       
        Playlist playlist = new Playlist("1", "playlist1", new ArrayList<>());
        user.addPlaylist(playlist);

        Playlist playlistAfterSong1 = new Playlist("1","playlist1", Arrays.asList(song1));
        Playlist playlistAfterSong2 = new Playlist("1", "playlist1", Arrays.asList(song1, song2));
        Playlist playlistAfterSong3 = new Playlist("1", "playlist1", Arrays.asList(song1, song2, song3));
        //add songs in playlist
       when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(user));
       when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
       when(songsRepositoryMock.findById("1")).thenReturn(Optional.of(song1));
       when(songsRepositoryMock.findById("2")).thenReturn(Optional.of(song2));
       when(songsRepositoryMock.findById("3")).thenReturn(Optional.of(song3));
       when(playlistRepositoryMock.checkIfSongExists(anyString(), any(Song.class))).thenReturn(false);
       when(playlistRepositoryMock.addSong("1", song1)).thenReturn(playlistAfterSong1);
       when(playlistRepositoryMock.addSong("1", song2)).thenReturn(playlistAfterSong2);
       when(playlistRepositoryMock.addSong("1", song3)).thenReturn(playlistAfterSong3); 

       ModifyPlaylistDto expectedResult = new ModifyPlaylistDto("1","playlist1", Arrays.asList("1","2","3"));
        
       ModifyPlaylistDto actualResult = playlistService.modifyPlaylist("ADD-SONG", "1", "1", Arrays.asList("1","2","3"));
        
       Assertions.assertEquals(expectedResult, actualResult);

       verify(userRepositoryMock, times(1)).findById(anyString());
       verify(playlistRepositoryMock, times(1)).findById(anyString());
       verify(songsRepositoryMock, times(3)).findById(anyString());
       verify(playlistRepositoryMock, times(3)).checkIfSongExists(anyString(), any(Song.class));
       verify(playlistRepositoryMock, times(3)).addSong(anyString(), any(Song.class));
        
    }


    @Test
    @DisplayName("modify delete method should delete songs from playlist")
    public void modifyPlaylistDelete_ShouldDeleteSongsFromPlaylist(){

        User user = new User("1", "aarti");

        Album album = new Album("1", "album1");
        Artist artist = new Artist("1", "artist1");
        List<String> featureArtist = Arrays.asList("artist1", "artist2");


        Song song1 = new Song("1", "song1", "genre1", album, artist, featureArtist);
        Song song2 = new Song("2", "song2", "genre2", album, artist, featureArtist);
        Song song3 = new Song("3", "song3", "genre3", album, artist, featureArtist);
       
        Playlist playlist = new Playlist("1", "playlist1", Arrays.asList(song1, song2, song3));
        user.addPlaylist(playlist);

        Playlist playlistAfterSong1 = new Playlist("1", "playlist1", Arrays.asList(song1, song3));
        Playlist playlistAfterSong2 = new Playlist("1","playlist1", Arrays.asList(song1));        
        //add songs in playlist
       when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(user));
       when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
       when(songsRepositoryMock.findById("2")).thenReturn(Optional.of(song2));
       when(songsRepositoryMock.findById("3")).thenReturn(Optional.of(song3));
       when(playlistRepositoryMock.checkIfSongExists(anyString(), any(Song.class))).thenReturn(true);
       when(playlistRepositoryMock.deleteSong("1", song2)).thenReturn(playlistAfterSong1);
       when(playlistRepositoryMock.deleteSong("1", song3)).thenReturn(playlistAfterSong2);
       
       ModifyPlaylistDto expectedResult = new ModifyPlaylistDto("1","playlist1", Arrays.asList("1"));
        
       ModifyPlaylistDto actualResult = playlistService.modifyPlaylist("DELETE-SONG", "1", "1", Arrays.asList("2","3"));
        
       Assertions.assertEquals(expectedResult, actualResult);

       verify(userRepositoryMock, times(1)).findById(anyString());
       verify(playlistRepositoryMock, times(1)).findById(anyString());
       verify(songsRepositoryMock, times(2)).findById(anyString());
       verify(playlistRepositoryMock, times(2)).checkIfSongExists(anyString(), any(Song.class));
       verify(playlistRepositoryMock, times(2)).deleteSong(anyString(), any(Song.class));
        
    }

            

}
