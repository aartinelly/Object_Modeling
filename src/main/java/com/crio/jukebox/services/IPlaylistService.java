package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.dtos.PlayPlaylistDto;
import com.crio.jukebox.entities.Song;

public interface IPlaylistService {
    public String createPlaylist(String id, String userId, String playlistName, List<String> songsId);
    public String deletePlaylist(String userId, String playlistId);
    public ModifyPlaylistDto modifyPlaylist(String command, String userId, String playlistId, List<String> songsIds);
    public PlayPlaylistDto playPlaylist(String userId, String playlistId);
    public PlayPlaylistDto playSong(List<String> tokens);

}
