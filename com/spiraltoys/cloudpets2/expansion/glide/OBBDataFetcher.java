package com.spiraltoys.cloudpets2.expansion.glide;

import android.content.Context;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;
import java.io.IOException;
import java.io.InputStream;

public class OBBDataFetcher implements DataFetcher<InputStream> {
    private final Context mContext;
    private final OBBFile mObbFile;
    private InputStream mObbFileStream;

    public OBBDataFetcher(Context context, OBBFile obbFile) {
        this.mContext = context;
        this.mObbFile = obbFile;
    }

    public InputStream loadData(Priority priority) throws Exception {
        InputStream inputStream = ExpansionUtils.getMainExpansionDescriptor().getZipResourceFile(this.mContext).getInputStream(this.mObbFile.getPath());
        this.mObbFileStream = inputStream;
        return inputStream;
    }

    public void cleanup() {
        try {
            if (this.mObbFileStream != null) {
                this.mObbFileStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return this.mContext.getPackageName() + "@" + this.mObbFile.getExpansionFileDescriptor().getMainVersion() + "." + this.mObbFile.getExpansionFileDescriptor().getPatchVersion() + ".obb/" + this.mObbFile.getPath();
    }

    public void cancel() {
    }
}
