package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import com.crio.codingame.repositories.UserRepository;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.ArtistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.SongsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepositoryMock;
    
    @Mock
    private SongsService songsServiceMock;
    
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("create method should create User")
    public void create_ShouldReturnUser(){
        //Arrange
        User expectedUser = new User("1", "Yakshit");
        when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);

        //Act
        User actualUser = userService.createUser("Yakshit");

        //Assert
        Assertions.assertEquals(expectedUser,actualUser);
        verify(userRepositoryMock,times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Load data should load all songs")
    public void load_data(){

        Album album = new Album("1","No.6 Collaborations Project");
        Artist artist =  new Artist("1","Ed Sheeran");

        Song song1 = new Song("1", "South of the Border", "Pop", album, artist, Arrays.asList("Ed Sheeran","Cardi.B","Camilla Cabello")); 
        Song song2 =  new Song("2", "I Dont'Care", "pop", album, artist, Arrays.asList("Ed Sheeran","Justin Bieber")); 
        Song song3 =  new Song("3", "Remember The Name", "pop", album, artist, Arrays.asList("Ed Sheeran","Eminem", "50Cent")); 
        List<Song> expectedSong = Arrays.asList(song1, song2, song3);
      
       
        when(songsServiceMock.loadSongs("TestSongs")).thenReturn(expectedSong);

        List<Song> actualSong = userService.loadSongs("TestSongs");
        
        Assertions.assertEquals(expectedSong, actualSong);
        
        verify(songsServiceMock, times(1)).loadSongs(anyString());
    }
}
