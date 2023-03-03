package com.testvagrant.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.testvagrant.store.RecentlyPlayedSongsStoreImpl;

public class RecentlyPlayedSongsStoreTest {
    private RecentlyPlayedSongsStoreImpl store;

    @Before
    public void setUp() {
        store = new RecentlyPlayedSongsStoreImpl(3);
    }

    @Test
    public void testEmptyList() {
        List<String> songs = store.getRecentSongs("Alice");
        assertEquals(Collections.emptyList(), songs);
    }

    
    @Test
    /*Let's assume that the user has played 3 songs - S1, S2 and S3.
     * The play list would look like -> S1,S2,S3
     * */   
    public void testRecordAndGetSongs() {
        store.recordSong("Alice", "S1");
        store.recordSong("Alice", "S2");
        store.recordSong("Alice", "S3");
        

        List<String> songs = store.getRecentSongs("Alice");
        assertEquals(Arrays.asList("S1", "S2", "S3"), songs);
    }
   
    
    @Test
    /*Let's assume that the user has played 3 songs - S1, S2 and S3.
     *then user add one more song "S4" and play that song.
     *When S4 song is played play list would look like -> S2,S3,S4 
     * */
    public void testRecordAndGetSongs2() {
    	store.recordSong("Alice", "S1");
        store.recordSong("Alice", "S2");
        store.recordSong("Alice", "S3");
        store.recordSong("Alice", "S4");
        
        List<String> songs = store.getRecentSongs("Alice");
        assertEquals(Arrays.asList("S2", "S3", "S4"), songs);
    }
    
    
    @Test
    /*Let's assume that the user has played 3 songs - S2, S3 and S4.
     *then user add one more song "S2" and play that song.
     *When S2 song is played play list would look like -> S3,S4,S2
     * */
    public void testRecordAndGetSongs3() {
    	store.recordSong("Alice", "S1");
        store.recordSong("Alice", "S2");
        store.recordSong("Alice", "S3");
        store.recordSong("Alice", "S4");
        store.recordSong("Alice", "S2");
        
        List<String> songs = store.getRecentSongs("Alice");
        assertEquals(Arrays.asList("S3", "S4", "S2"), songs);
    }
    
    
    @Test
    /*Let's assume that the user has played 3 songs - S2, S3 and S4.
     *then user add one more song "S1" and play that song.
     *When S1 song is played play list would look like-> S4,S2,S1
     * */
    public void testRecordAndGetSong4() {
    	store.recordSong("Alice", "S1");
        store.recordSong("Alice", "S2");
        store.recordSong("Alice", "S3");
        store.recordSong("Alice", "S4");
        store.recordSong("Alice", "S2");
        store.recordSong("Alice", "S1");

        
        List<String> songs = store.getRecentSongs("Alice");
        assertEquals(Arrays.asList("S4", "S2", "S1"), songs);
    }
    
    
}
