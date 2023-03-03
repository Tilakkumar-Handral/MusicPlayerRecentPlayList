package com.testvagrant.music.services;

import java.util.List;

public interface RecentlyPlayedSongsStoreService {

	public  void recordSong(String user, String song);
	
	public  List<String> getRecentSongs(String user);
}
