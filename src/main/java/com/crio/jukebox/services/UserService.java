package com.crio.jukebox.services;


import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.ArtistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.SongsRepository;

public class UserService implements IUserService{

    private IUserRepository userRepository;
    private SongsService songsService;

    public UserService(IUserRepository userRepository, SongsService songsService){
        this.userRepository = userRepository;
        this.songsService = songsService;
    }

    @Override
    public User createUser(String username) {
        
        User user = new User(null, username);
        return userRepository.save(user);
    }

    @Override
    public List<Song> loadSongs(String filename) {   
       return songsService.loadSongs(filename);
        
    }    
    
}
