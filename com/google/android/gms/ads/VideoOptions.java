package com.google.android.gms.ads;

import com.google.android.gms.internal.zziy;

@zziy
public final class VideoOptions {
    private final boolean zzakg;

    public static final class Builder {
        private boolean zzakg = false;

        public VideoOptions build() {
            return new VideoOptions();
        }

        public Builder setStartMuted(boolean z) {
            this.zzakg = z;
            return this;
        }
    }

    private VideoOptions(Builder builder) {
        this.zzakg = builder.zzakg;
    }

    public boolean getStartMuted() {
        return this.zzakg;
    }
}
