package com.example.a800709620_midterm;


import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class Song implements Serializable {

    String trackName, albumName, artistName,  trackShareURL;
    Date updatedTime;


    public Song(String trackName, String albumName, String artistName, Date updatedTime, String trackShareURL) {
        this.trackName = trackName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.trackShareURL = trackShareURL;
        this.updatedTime = updatedTime;

    }

    public Song() {
    }

   /* public static Comparator<Song> COMPARE_BY_PRICE = new Comparator<Song>() {
        public int compare(Song one, Song other) {
            Float a = Float.parseFloat(one.trackPrice);
            Float b = Float.parseFloat(other.trackPrice);
            return a.compareTo(b);
        }
    };

    public static Comparator<Song> COMPARE_BY_DATE = new Comparator<Song>() {
        public int compare(Song one, Song other) {
            return one.date.compareTo(other.date);
        }
    };*/
}
