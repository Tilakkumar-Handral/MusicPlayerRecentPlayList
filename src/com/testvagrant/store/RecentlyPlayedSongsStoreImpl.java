package com.testvagrant.store;

import java.util.*;

import com.testvagrant.music.services.RecentlyPlayedSongsStoreService;

public class RecentlyPlayedSongsStoreImpl implements RecentlyPlayedSongsStoreService{
    private final int capacity; // the maximum number of songs per user
    private final Map<String, Deque<String>> songMap; // map user names to their recently played songs

    public RecentlyPlayedSongsStoreImpl(int capacity) {
        this.capacity = capacity;
        this.songMap = new HashMap<>();
    }

    @Override
    public synchronized void recordSong(String user, String song) {
        Deque<String> songs = songMap.get(user);
        if (songs == null) {
            songs = new LinkedList<>();
            songMap.put(user, songs);
        }
        if (songs.size() >= capacity) {
            songs.removeFirst(); // remove the least recently played song
        }
        songs.addLast(song); // add the new song to the end of the queue
    }

    @Override
    public synchronized List<String> getRecentSongs(String user) {
        Deque<String> songs = songMap.get(user);
        if (songs == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(songs); // return a copy of the list of songs
    }
}
