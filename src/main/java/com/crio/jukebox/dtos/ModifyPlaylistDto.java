package com.crio.jukebox.dtos;

import java.util.List;
import com.crio.jukebox.entities.Playlist;

public class ModifyPlaylistDto {
    private String playlistId;
    private String playlistName;
    private List<String> songId;

    public ModifyPlaylistDto(String playlistId, String playlistName, List<String> songId) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.songId = songId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<String> getSongId() {
        return songId;
    }

    public void setSongId(List<String> songId) {
        this.songId = songId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playlistId == null) ? 0 : playlistId.hashCode());
        result = prime * result + ((playlistName == null) ? 0 : playlistName.hashCode());
        result = prime * result + ((songId == null) ? 0 : songId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModifyPlaylistDto other = (ModifyPlaylistDto) obj;
        if (playlistId == null) {
            if (other.playlistId != null)
                return false;
        } else if (!playlistId.equals(other.playlistId))
            return false;
        if (playlistName == null) {
            if (other.playlistName != null)
                return false;
        } else if (!playlistName.equals(other.playlistName))
            return false;
        if (songId == null) {
            if (other.songId != null)
                return false;
        } else if (!songId.equals(other.songId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ModifyPlaylistDto [playlistId=" + playlistId + ", playlistName=" + playlistName
                + ", songId=" + songId + "]";
    }


}
