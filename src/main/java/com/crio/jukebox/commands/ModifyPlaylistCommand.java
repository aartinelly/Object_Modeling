package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private IPlaylistService playlistService;


    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        List<String> songIds = new ArrayList<>();
        for (int i = 4; i < tokens.size(); i++) {
            songIds.add(tokens.get(i));
        }
        try {
            ModifyPlaylistDto modifyPlaylistDto = playlistService.modifyPlaylist(tokens.get(1),
            tokens.get(2), tokens.get(3), songIds);
            System.out.println("Playlist ID - " + modifyPlaylistDto.getPlaylistId());
            System.out.println("Playlist Name - " + modifyPlaylistDto.getPlaylistName());
            String sid = modifyPlaylistDto.getSongId().stream().collect(Collectors.joining(" "));
            System.out.println("Song IDs - " + sid);
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
