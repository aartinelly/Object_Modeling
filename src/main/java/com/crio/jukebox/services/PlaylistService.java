package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.dtos.ModifyPlaylistDto;
import com.crio.jukebox.dtos.PlayPlaylistDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.SongsRepository;

public class PlaylistService implements IPlaylistService {

    private IPlaylistRepository playlistRepository;
    private IUserRepository userRepository;
    private SongsRepository songsRepository;

    public PlaylistService(IPlaylistRepository playlistRepository, IUserRepository userRepository,
            SongsRepository songsRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songsRepository = songsRepository;
    }

    @Override
    public String createPlaylist(String id, String userId, String playlistName,
            List<String> songsId) throws UserNotFoundException, SongNotFoundException {
        // TODO Auto-generated method stub
        // check if userId exists or not
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        // check if all song id are exists or not
        List<Song> songs = new ArrayList<>();
        for (String sid : songsId) {
            Song s = songsRepository.findById(sid).orElseThrow(() -> new SongNotFoundException(
                    "Some Requested Songs Not Available. Please try again."));
            songs.add(s);
        }

        Playlist playlist = new Playlist(null, playlistName, songs);
        Playlist savedPlaylist = playlistRepository.save(playlist);
        user.addPlaylist(savedPlaylist);
        return savedPlaylist.getId();
    }

    @Override
    public String deletePlaylist(String userId, String playlistId) {
        // TODO Auto-generated method stub
        // check if userId exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // check if playlistId exists
        playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist Not Found"));

        // check if playlistId exists for given user
        Playlist p = getPlaylistById(user.getPlaylist(), playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist Not Found"));
        user.removePlaylist(p);

        playlistRepository.delete(p);

        return "Delete Successful";
    }

    private Optional<Playlist> getPlaylistById(List<Playlist> playlists, String playlistId) {
        return playlists.stream().filter(p -> p.getId().equals(playlistId)).findFirst();
    }

    @Override
    public ModifyPlaylistDto modifyPlaylist(String command, String userId, String playlistId,
            List<String> songsIds) {
        // check if user id exists or not
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        // check if playlistId exists or not
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist not found"));

        // check if user has given playlist or not
        user.getGivenPlaylist(playlist)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist not found"));

        // remove existing playlist
        user.removePlaylist(playlist);

        // TODO Auto-generated method stub
        if (command.equals("ADD-SONG")) {
            // check if songsIds exists or not
            for (String sid : songsIds) {
                Song s = songsRepository.findById(sid).orElseThrow(() -> new SongNotFoundException(
                        "Some Requested Songs Not Available. Please try again."));
                // check if song exists in playlist
                if (!playlistRepository.checkIfSongExists(playlist.getId(), s)) {
                    playlist = playlistRepository.addSong(playlist.getId(), s);
                }
            }

            // add new playlist
            user.addPlaylist(playlist);

        } else if (command.equals("DELETE-SONG")) {
            for (String sid : songsIds) {
                Song s = songsRepository.findById(sid).orElseThrow(() -> new SongNotFoundException(
                        "Some Requested Songs Not Available. Please try again."));
                // check if song exists in playlist
                if (playlistRepository.checkIfSongExists(playlist.getId(), s)) {
                    playlist = playlistRepository.deleteSong(playlist.getId(), s);
                } else {
                    throw new SongNotFoundException(
                            "Some Requested Songs for Deletion are not present in the playlist. Please try again.");
                }

            }

            user.addPlaylist(playlist);
        }
        List<String> songId =
                playlist.getSongs().stream().map(s -> s.getId()).collect(Collectors.toList());
        return new ModifyPlaylistDto(playlist.getId(), playlist.getPlaylistName(), songId);
    }

    @Override
    public PlayPlaylistDto playPlaylist(String userId, String playlistId) {
        // TODO Auto-generated method stub
        // check if user id exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not found"));
        // check if playlistid exists for given user
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist does not exists"));
        Playlist pl = user.getGivenPlaylist(playlist)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist does not exists"));
        // check if playlist is empty
        if (pl.getSongs().size() == 0) {
            throw new PlaylistEmptyException("Playlist is empty.");
        }
        Song s1 = pl.getSongs().get(0);
        pl.setActive(true);
        pl.setCurrentsong(Integer.parseInt(s1.getId()));
        PlayPlaylistDto playPlaylistDto = new PlayPlaylistDto(s1.getSongName(),
                s1.getAlbumName().getAlbumName(), s1.getFeatureArtist());
        return playPlaylistDto;
    }

    @Override
    public PlayPlaylistDto playSong(List<String> tokens) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(tokens.get(1))
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Playlist playlist = user.getActivePlaylist()
                .orElseThrow(() -> new PlaylistNotFoundException("No active playlist found"));

        PlayPlaylistDto playPlaylistDto;
        if (tokens.get(2).equals("BACK")) {
            playPlaylistDto = playSongBack(user, playlist);
        } else if (tokens.get(2).equals("NEXT")) {
            playPlaylistDto = playSongAfter(user, playlist);
        } else {
            playPlaylistDto = playPreferredSong(user, tokens.get(2), playlist);
        }

        return playPlaylistDto;
    }

    private PlayPlaylistDto playSongBack(User user, Playlist playlist) {
        int size = playlist.getSongs().size();

        // if current song is first song then play last song
        if (playlist.getCurrentsong() == Integer.parseInt(playlist.getSongs().get(0).getId())) {
            int currSong = Integer.parseInt(playlist.getSongs().get(size - 1).getId());
            playlist.setCurrentsong(currSong);
            Song s = songsRepository.findById(Integer.toString(currSong)).get();
            return new PlayPlaylistDto(s.getSongName(), s.getAlbumName().getAlbumName(),
                    s.getFeatureArtist());
        }
        // get current song object
        int sid = playlist.getCurrentsong();
        Song currSongObj = songsRepository.findById(Integer.toString(sid)).get();
        int indexCurrSong = playlist.getSongs().indexOf(currSongObj);

        // increment indexCurrSong by 1 and get value of index and
        // set currentsong of playlist to new song Id
        String newSongId = playlist.getSongs().get(indexCurrSong - 1).getId();
        playlist.setCurrentsong(Integer.parseInt(newSongId));


        Song s = songsRepository.findById(Integer.toString(playlist.getCurrentsong())).get();
        return new PlayPlaylistDto(s.getSongName(), s.getAlbumName().getAlbumName(),
                s.getFeatureArtist());
    }

    private PlayPlaylistDto playSongAfter(User user, Playlist playlist) {
        int size = playlist.getSongs().size();
        // if current song is last song then play first song
        if (playlist.getCurrentsong() == Integer
                .parseInt(playlist.getSongs().get(size - 1).getId())) {
            int currSong = Integer.parseInt(playlist.getSongs().get(0).getId());
            playlist.setCurrentsong(currSong);
            Song s = songsRepository.findById(Integer.toString(currSong)).get();
            return new PlayPlaylistDto(s.getSongName(), s.getAlbumName().getAlbumName(),
                    s.getFeatureArtist());
        }

        int sid = playlist.getCurrentsong();
        Song currSongObj = songsRepository.findById(Integer.toString(sid)).get();
        int indexCurrSong = playlist.getSongs().indexOf(currSongObj);

        // increment indexCurrSong by 1 and get value of index and
        // set currentsong of playlist to new song Id
        String newSongId = playlist.getSongs().get(indexCurrSong + 1).getId();
        playlist.setCurrentsong(Integer.parseInt(newSongId));

        Song s = songsRepository.findById(Integer.toString(playlist.getCurrentsong())).get();
        return new PlayPlaylistDto(s.getSongName(), s.getAlbumName().getAlbumName(),
                s.getFeatureArtist());


    }



    private PlayPlaylistDto playPreferredSong(User user, String songId, Playlist playlist) {
        Song s = songsRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song does not exists"));

        // check if songId is exists in playlist
        if (playlist.checkSongExists(s)) {
            throw new SongNotFoundException("Given song id is not a part of the active playlist");
        } else {
            playlist.setCurrentsong(Integer.parseInt(s.getId()));
            return new PlayPlaylistDto(s.getSongName(), s.getAlbumName().getAlbumName(),
                    s.getFeatureArtist());
        }

    }

}
