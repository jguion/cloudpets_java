package com.spiraltoys.cloudpets2.expansion.glide;

import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;

public class MainExpansionFile extends OBBFile {
    public MainExpansionFile(String path) {
        super(path, ExpansionUtils.getMainExpansionDescriptor());
    }
}
