package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IUserService;


public class LoadDataCommand implements ICommand{

    private IUserService userService;

    public LoadDataCommand(IUserService userService){
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String fileName = tokens.get(1);
        List<Song> songs = userService.loadSongs(fileName); 
        System.out.println("Songs Loaded successfully");
    }
    
}
