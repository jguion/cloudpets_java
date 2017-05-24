package com.spiraltoys.cloudpets2.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.free.R;
import java.text.SimpleDateFormat;
import java.util.Date;

@ParseClassName("PlushToy")
public class PlushToy extends ParseObject {
    public static final String CHARACTER = "character";
    public static final String CONFIG_ID = "configID";
    public static final int MAX_NICKNAME_LENGTH = 32;
    public static final int MIN_NICKNAME_LENGTH = 1;
    public static final String NICKNAME = "nickName";

    public enum Character {
        BENTLEY_THE_BEAR("BentleyTheBear"),
        CHARLEY_THE_CAT("CharleyTheCat"),
        DIESEL_THE_DOG("DieselTheDog"),
        BUBBLES_THE_BUNNY("BubblesTheBunny"),
        STARBURST_THE_UNICORN("StarburstTheUnicorn");
        
        String mValue;

        private Character(String stringValue) {
            this.mValue = stringValue;
        }

        public String toString() {
            return this.mValue;
        }

        public static Character getCharacter(String stringValue) {
            for (Character character : values()) {
                if (character.mValue.equals(stringValue)) {
                    return character;
                }
            }
            return BENTLEY_THE_BEAR;
        }
    }

    public Character getCharacter() {
        return Character.getCharacter(getString("character"));
    }

    public void setCharacter(String character) {
        put("character", character);
    }

    public void setCharacter(Character character) {
        setCharacter(character.toString());
    }

    public String getConfigID() {
        return getString(CONFIG_ID);
    }

    public void setConfigID(String configID) {
        put(CONFIG_ID, configID);
    }

    public String getNickName() {
        return getString(NICKNAME);
    }

    public void setNickName(String nickName) {
        put(NICKNAME, nickName);
    }

    public static String generateCharacterId(Character character) {
        String formattedDate = new SimpleDateFormat("MM_dd_HH_mm_ss").format(new Date());
        return String.format("%02x%s", new Object[]{Integer.valueOf(character.ordinal()), formattedDate});
    }

    public static boolean isValidCharacterId(String identifier) {
        if (identifier == null) {
            return false;
        }
        return identifier.matches("\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}");
    }

    public static int getAppThemeResourceForCharacter(Character character) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$model$PlushToy$Character[character.ordinal()]) {
            case 1:
                return R.style.AppTheme.ChildDashboard.BentleyTheBear;
            case 2:
                return R.style.AppTheme.ChildDashboard.CharleyTheCat;
            case 3:
                return R.style.AppTheme.ChildDashboard.DieselTheDog;
            case 4:
                return R.style.AppTheme.ChildDashboard.BubblesTheBunny;
            case 5:
                return R.style.AppTheme.ChildDashboard.StarburstTheUnicorn;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int getAvatarResourceForCharacter(Character character) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$model$PlushToy$Character[character.ordinal()]) {
            case 1:
                return R.drawable.avatar_bear_large;
            case 2:
                return R.drawable.avatar_cat_large;
            case 3:
                return R.drawable.avatar_dog_large;
            case 4:
                return R.drawable.avatar_bunny_large;
            case 5:
                return R.drawable.avatar_unicorn_large;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int getMessageForMeAvatarResourceForCharacter(Character character) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$model$PlushToy$Character[character.ordinal()]) {
            case 1:
                return R.drawable.message_for_me_bear;
            case 2:
                return R.drawable.message_for_me_cat;
            case 3:
                return R.drawable.message_for_me_dog;
            case 4:
                return R.drawable.message_for_me_bunny;
            case 5:
                return R.drawable.message_for_me_unicorn;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int getGameAvatarResourceForCharacter(Character character) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$model$PlushToy$Character[character.ordinal()]) {
            case 1:
                return R.drawable.barnyard_bear;
            case 2:
                return R.drawable.barnyard_cat;
            case 3:
                return R.drawable.barnyard_dog;
            case 4:
                return R.drawable.barnyard_bunny;
            case 5:
                return R.drawable.barnyard_unicorn;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static BackgroundMusicTrack getBackgroundMusicTrackForCharacter(Character character) {
        switch (1.$SwitchMap$com$spiraltoys$cloudpets2$model$PlushToy$Character[character.ordinal()]) {
            case 1:
                return BackgroundMusicTrack.BENTLEY_AMBIENT;
            case 2:
                return BackgroundMusicTrack.CHARLEY_AMBIENT;
            case 3:
                return BackgroundMusicTrack.DIESEL_AMBIENT;
            case 4:
                return BackgroundMusicTrack.BUBBLES_AMBIENT;
            case 5:
                return BackgroundMusicTrack.STARBURST_AMBIENT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
