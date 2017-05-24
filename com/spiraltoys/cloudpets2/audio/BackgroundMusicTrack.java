package com.spiraltoys.cloudpets2.audio;

public enum BackgroundMusicTrack {
    BENTLEY_AMBIENT("background_music/BentleyTheBear_MusicAmb.mp3"),
    BUBBLES_AMBIENT("background_music/BubblesTheBunny_MusicAmb.mp3"),
    CHARLEY_AMBIENT("background_music/CharleyTheCat_MusicAmb.mp3"),
    DIESEL_AMBIENT("background_music/DieselTheDog_MusicAmb.mp3"),
    STARBURST_AMBIENT("background_music/StarburstTheUnicorn_MusicAmb.mp3"),
    FRONT_END("background_music/FE_MusicLoop.mp3");
    
    private String mFileName;

    private BackgroundMusicTrack(String fileName) {
        this.mFileName = fileName;
    }

    public String getFilename() {
        return this.mFileName;
    }
}
