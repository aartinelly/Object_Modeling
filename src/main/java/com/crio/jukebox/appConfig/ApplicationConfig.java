package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.AlbumRepository;
import com.crio.jukebox.repositories.ArtistRepository;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongsRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongsService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {

    private final IUserRepository userRepository = new UserRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final SongsRepository songsRepository = new SongsRepository();
    private final AlbumRepository albumRepository = new AlbumRepository();
    private final ArtistRepository artistRepository = new ArtistRepository();
    
    private final SongsService songsService = new SongsService(albumRepository, artistRepository, songsRepository);
    private final IUserService userService = new UserService(userRepository, songsService);
    private final IPlaylistService playlistService = new PlaylistService(playlistRepository, userRepository, songsRepository);
    
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(userService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playlistService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playlistService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
       
        return commandInvoker;
    }
}
