package com.spiraltoys.cloudpets2.expansion.glide;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import java.io.InputStream;

public class OBBStreamLoader implements StreamModelLoader<OBBFile> {
    private final Context mContext;

    public static class Factory implements ModelLoaderFactory<OBBFile, InputStream> {
        public ModelLoader<OBBFile, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new OBBStreamLoader(context);
        }

        public void teardown() {
        }
    }

    public OBBStreamLoader(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public DataFetcher<InputStream> getResourceFetcher(OBBFile model, int width, int height) {
        return new OBBDataFetcher(this.mContext, model);
    }
}
