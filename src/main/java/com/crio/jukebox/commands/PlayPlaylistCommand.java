package com.crio.jukebox.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.PlayPlaylistDto;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {

    private IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        try {
            PlayPlaylistDto playPlaylistDto =
            playlistService.playPlaylist(tokens.get(1), tokens.get(2));
            System.out.println("Current Song Playing");
            System.out.println("Song - " + playPlaylistDto.getSongName());
            System.out.println("Album - " + playPlaylistDto.getAlbumName());
            String s = playPlaylistDto.getFeatureArtist().stream().collect(Collectors.joining(","));
            System.out.println("Artists - " + s);
        } catch (PlaylistEmptyException e) {
            e.getMessage();
        }

    }

}
