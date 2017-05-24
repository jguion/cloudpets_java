package com.spiraltoys.cloudpets2.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("ProfilePortrait")
public class ProfilePortrait extends ParseObject {
    public static final String LOCAL_FILENAME = "localFilename";
    public static final String PORTRAIT = "portrait";

    public String getLocalFilename() {
        return getString("localFilename");
    }

    public void setLocalFilename(String localFilename) {
        put("localFilename", localFilename);
    }

    public ParseFile getFile() {
        return getParseFile("portrait");
    }

    public void setFile(ParseFile portrait) {
        put("portrait", portrait);
    }
}
