package com.example.firebaserealtimedatabase;

public class Track {
    private String trackId;
    private String trackName;
    private String trackRating;

    public Track(String id, String trackname, int rating){

    }

    public Track(String trackId, String trackName, String trackRating) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackRating = trackRating;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackRating() {
        return trackRating;
    }
}
