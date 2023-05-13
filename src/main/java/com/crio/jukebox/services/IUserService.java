package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IUserService {
    User createUser(String username);

    List<Song> loadSongs(String filename);
}
