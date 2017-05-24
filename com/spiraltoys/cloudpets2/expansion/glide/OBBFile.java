package com.spiraltoys.cloudpets2.expansion.glide;

import com.spiraltoys.cloudpets2.expansion.ExpansionUtils.ExpansionFileDescriptor;

public class OBBFile {
    private final ExpansionFileDescriptor mExpansionFileDescriptor;
    private final String mPath;

    public OBBFile(String path, ExpansionFileDescriptor expansionFileDescriptor) {
        this.mPath = path;
        this.mExpansionFileDescriptor = expansionFileDescriptor;
    }

    public String getPath() {
        return this.mPath;
    }

    public ExpansionFileDescriptor getExpansionFileDescriptor() {
        return this.mExpansionFileDescriptor;
    }
}
