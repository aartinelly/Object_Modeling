package com.crio.jukebox.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.PlayPlaylistDto;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand {

    private IPlaylistService playlistService;

    public PlaySongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            PlayPlaylistDto playPlaylistDto = playlistService.playSong(tokens);
            System.out.println("Current Song Playing");
            System.out.println("Song - " + playPlaylistDto.getSongName());
            System.out.println("Album - " + playPlaylistDto.getAlbumName());
            String s = playPlaylistDto.getFeatureArtist().stream().collect(Collectors.joining(","));
            System.out.println("Artists - " + s);
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

}
