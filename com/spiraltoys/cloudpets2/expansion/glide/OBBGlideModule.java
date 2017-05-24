package com.spiraltoys.cloudpets2.expansion.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;
import com.spiraltoys.cloudpets2.expansion.glide.OBBStreamLoader.Factory;
import java.io.InputStream;

public class OBBGlideModule implements GlideModule {
    public void applyOptions(Context context, GlideBuilder builder) {
    }

    public void registerComponents(Context context, Glide glide) {
        glide.register(OBBFile.class, InputStream.class, new Factory());
    }
}
