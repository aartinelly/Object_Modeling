package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistRepository extends CRUDRepository<Playlist, String>{
    public Playlist addSong(String playlistId, Song s);
    public Playlist deleteSong(String playlistId, Song s);
    public boolean checkIfSongExists(String playlistId, Song songId);
    public Optional<Playlist> getActivePlaylist();
}
