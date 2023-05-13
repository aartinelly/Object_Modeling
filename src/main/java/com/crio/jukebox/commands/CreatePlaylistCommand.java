package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{

    private IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {

        List<String> songIds = new ArrayList<>();
        for (int i = 3; i < tokens.size(); i++) {
            songIds.add(tokens.get(i));
        }
       
        try{
            String p = playlistService.createPlaylist(null, tokens.get(1), tokens.get(2), songIds);
            System.out.println("Playlist ID - "+p);
        }
        catch(UserNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(SongNotFoundException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
