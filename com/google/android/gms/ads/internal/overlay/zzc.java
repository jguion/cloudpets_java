package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkn;
import com.google.android.gms.internal.zzkr;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zziy
@TargetApi(14)
public class zzc extends zzi implements OnAudioFocusChangeListener, OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzbwi = new HashMap();
    private final zzx zzbwj;
    private final boolean zzbwk;
    private int zzbwl = 0;
    private int zzbwm = 0;
    private MediaPlayer zzbwn;
    private Uri zzbwo;
    private int zzbwp;
    private int zzbwq;
    private int zzbwr;
    private int zzbws;
    private int zzbwt;
    private float zzbwu = 1.0f;
    private boolean zzbwv;
    private boolean zzbww;
    private zzw zzbwx;
    private boolean zzbwy;
    private int zzbwz;
    private zzh zzbxa;

    static {
        if (VERSION.SDK_INT >= 17) {
            zzbwi.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzbwi.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzbwi.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzbwi.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
            zzbwi.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzbwi.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzbwi.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzbwi.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzbwi.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzbwi.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzbwi.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzbwi.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzbwi.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzbwi.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzbwi.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzbwi.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzc(Context context, boolean z, boolean z2, zzx com_google_android_gms_ads_internal_overlay_zzx) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzbwj = com_google_android_gms_ads_internal_overlay_zzx;
        this.zzbwy = z;
        this.zzbwk = z2;
        this.zzbwj.zza((zzi) this);
    }

    private void zzaf(int i) {
        if (i == 3) {
            this.zzbwj.zzqf();
        } else if (this.zzbwl == 3) {
            this.zzbwj.zzqg();
        }
        this.zzbwl = i;
    }

    private void zzag(int i) {
        this.zzbwm = i;
    }

    private void zzb(float f) {
        if (this.zzbwn != null) {
            try {
                this.zzbwn.setVolume(f, f);
                return;
            } catch (IllegalStateException e) {
                return;
            }
        }
        zzb.zzdf("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    }

    private void zzoh() {
        Throwable e;
        String valueOf;
        zzkn.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzbwo != null && surfaceTexture != null) {
            zzz(false);
            try {
                SurfaceTexture zzpu;
                this.zzbwn = zzu.zzgq().zzps();
                this.zzbwn.setOnBufferingUpdateListener(this);
                this.zzbwn.setOnCompletionListener(this);
                this.zzbwn.setOnErrorListener(this);
                this.zzbwn.setOnInfoListener(this);
                this.zzbwn.setOnPreparedListener(this);
                this.zzbwn.setOnVideoSizeChangedListener(this);
                this.zzbwr = 0;
                if (this.zzbwy) {
                    this.zzbwx = new zzw(getContext());
                    this.zzbwx.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzbwx.start();
                    zzpu = this.zzbwx.zzpu();
                    if (zzpu == null) {
                        this.zzbwx.zzpt();
                        this.zzbwx = null;
                    }
                    this.zzbwn.setDataSource(getContext(), this.zzbwo);
                    this.zzbwn.setSurface(zzu.zzgr().zza(zzpu));
                    this.zzbwn.setAudioStreamType(3);
                    this.zzbwn.setScreenOnWhilePlaying(true);
                    this.zzbwn.prepareAsync();
                    zzaf(1);
                }
                zzpu = surfaceTexture;
                this.zzbwn.setDataSource(getContext(), this.zzbwo);
                this.zzbwn.setSurface(zzu.zzgr().zza(zzpu));
                this.zzbwn.setAudioStreamType(3);
                this.zzbwn.setScreenOnWhilePlaying(true);
                this.zzbwn.prepareAsync();
                zzaf(1);
            } catch (IOException e2) {
                e = e2;
                valueOf = String.valueOf(this.zzbwo);
                zzb.zzd(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzbwn, 1, 0);
            } catch (IllegalArgumentException e3) {
                e = e3;
                valueOf = String.valueOf(this.zzbwo);
                zzb.zzd(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzbwn, 1, 0);
            } catch (IllegalStateException e4) {
                e = e4;
                valueOf = String.valueOf(this.zzbwo);
                zzb.zzd(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzbwn, 1, 0);
            }
        }
    }

    private void zzoi() {
        if (this.zzbwk && zzol() && this.zzbwn.getCurrentPosition() > 0 && this.zzbwm != 3) {
            zzkn.v("AdMediaPlayerView nudging MediaPlayer");
            zzb(0.0f);
            this.zzbwn.start();
            int currentPosition = this.zzbwn.getCurrentPosition();
            long currentTimeMillis = zzu.zzgf().currentTimeMillis();
            while (zzol() && this.zzbwn.getCurrentPosition() == currentPosition) {
                if (zzu.zzgf().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzbwn.pause();
            zzoq();
        }
    }

    private void zzoj() {
        AudioManager zzor = zzor();
        if (zzor != null && !this.zzbww) {
            if (zzor.requestAudioFocus(this, 3, 2) == 1) {
                zzoo();
            } else {
                zzb.zzdf("AdMediaPlayerView audio focus request failed");
            }
        }
    }

    private void zzok() {
        zzkn.v("AdMediaPlayerView abandon audio focus");
        AudioManager zzor = zzor();
        if (zzor != null && this.zzbww) {
            if (zzor.abandonAudioFocus(this) == 1) {
                this.zzbww = false;
            } else {
                zzb.zzdf("AdMediaPlayerView abandon audio focus failed");
            }
        }
    }

    private boolean zzol() {
        return (this.zzbwn == null || this.zzbwl == -1 || this.zzbwl == 0 || this.zzbwl == 1) ? false : true;
    }

    private void zzoo() {
        zzkn.v("AdMediaPlayerView audio focus gained");
        this.zzbww = true;
        zzoq();
    }

    private void zzop() {
        zzkn.v("AdMediaPlayerView audio focus lost");
        this.zzbww = false;
        zzoq();
    }

    private void zzoq() {
        if (this.zzbwv || !this.zzbww) {
            zzb(0.0f);
        } else {
            zzb(this.zzbwu);
        }
    }

    private AudioManager zzor() {
        return (AudioManager) getContext().getSystemService("audio");
    }

    private void zzz(boolean z) {
        zzkn.v("AdMediaPlayerView release");
        if (this.zzbwx != null) {
            this.zzbwx.zzpt();
            this.zzbwx = null;
        }
        if (this.zzbwn != null) {
            this.zzbwn.reset();
            this.zzbwn.release();
            this.zzbwn = null;
            zzaf(0);
            if (z) {
                this.zzbwm = 0;
                zzag(0);
            }
            zzok();
        }
    }

    public int getCurrentPosition() {
        return zzol() ? this.zzbwn.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return zzol() ? this.zzbwn.getDuration() : -1;
    }

    public int getVideoHeight() {
        return this.zzbwn != null ? this.zzbwn.getVideoHeight() : 0;
    }

    public int getVideoWidth() {
        return this.zzbwn != null ? this.zzbwn.getVideoWidth() : 0;
    }

    public void onAudioFocusChange(int i) {
        if (i > 0) {
            zzoo();
        } else if (i < 0) {
            zzop();
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzbwr = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        zzkn.v("AdMediaPlayerView completion");
        zzaf(5);
        zzag(5);
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzc zzbxb;

            {
                this.zzbxb = r1;
            }

            public void run() {
                if (this.zzbxb.zzbxa != null) {
                    this.zzbxb.zzbxa.zzpl();
                }
            }
        });
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        final String str = (String) zzbwi.get(Integer.valueOf(i));
        final String str2 = (String) zzbwi.get(Integer.valueOf(i2));
        zzb.zzdf(new StringBuilder((String.valueOf(str).length() + 38) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzaf(-1);
        zzag(-1);
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzc zzbxb;

            public void run() {
                if (this.zzbxb.zzbxa != null) {
                    this.zzbxb.zzbxa.zzl(str, str2);
                }
            }
        });
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzbwi.get(Integer.valueOf(i));
        String str2 = (String) zzbwi.get(Integer.valueOf(i2));
        zzkn.v(new StringBuilder((String.valueOf(str).length() + 37) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.zzbwp, i);
        int defaultSize2 = getDefaultSize(this.zzbwq, i2);
        if (this.zzbwp > 0 && this.zzbwq > 0 && this.zzbwx == null) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == Ints.MAX_POWER_OF_TWO && mode2 == Ints.MAX_POWER_OF_TWO) {
                if (this.zzbwp * defaultSize2 < this.zzbwq * size) {
                    defaultSize = (this.zzbwp * defaultSize2) / this.zzbwq;
                } else if (this.zzbwp * defaultSize2 > this.zzbwq * size) {
                    defaultSize2 = (this.zzbwq * size) / this.zzbwp;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == Ints.MAX_POWER_OF_TWO) {
                defaultSize = (this.zzbwq * size) / this.zzbwp;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == Ints.MAX_POWER_OF_TWO) {
                defaultSize = (this.zzbwp * defaultSize2) / this.zzbwq;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.zzbwp;
                defaultSize = this.zzbwq;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.zzbwp * defaultSize2) / this.zzbwq;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.zzbwq * size) / this.zzbwp;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.zzbwx != null) {
            this.zzbwx.zzg(defaultSize, defaultSize2);
        }
        if (VERSION.SDK_INT == 16) {
            if ((this.zzbws > 0 && this.zzbws != defaultSize) || (this.zzbwt > 0 && this.zzbwt != defaultSize2)) {
                zzoi();
            }
            this.zzbws = defaultSize;
            this.zzbwt = defaultSize2;
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        zzkn.v("AdMediaPlayerView prepared");
        zzaf(2);
        this.zzbwj.zzpj();
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzc zzbxb;

            {
                this.zzbxb = r1;
            }

            public void run() {
                if (this.zzbxb.zzbxa != null) {
                    this.zzbxb.zzbxa.zzpj();
                }
            }
        });
        this.zzbwp = mediaPlayer.getVideoWidth();
        this.zzbwq = mediaPlayer.getVideoHeight();
        if (this.zzbwz != 0) {
            seekTo(this.zzbwz);
        }
        zzoi();
        int i = this.zzbwp;
        zzb.zzde("AdMediaPlayerView stream dimensions: " + i + " x " + this.zzbwq);
        if (this.zzbwm == 3) {
            play();
        }
        zzoj();
        zzoq();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzkn.v("AdMediaPlayerView surface created");
        zzoh();
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzc zzbxb;

            {
                this.zzbxb = r1;
            }

            public void run() {
                if (this.zzbxb.zzbxa != null) {
                    this.zzbxb.zzbxa.zzpi();
                }
            }
        });
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzkn.v("AdMediaPlayerView surface destroyed");
        if (this.zzbwn != null && this.zzbwz == 0) {
            this.zzbwz = this.zzbwn.getCurrentPosition();
        }
        if (this.zzbwx != null) {
            this.zzbwx.zzpt();
        }
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzc zzbxb;

            {
                this.zzbxb = r1;
            }

            public void run() {
                if (this.zzbxb.zzbxa != null) {
                    this.zzbxb.zzbxa.onPaused();
                    this.zzbxb.zzbxa.zzpm();
                }
            }
        });
        zzz(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Object obj = 1;
        zzkn.v("AdMediaPlayerView surface changed");
        Object obj2 = this.zzbwm == 3 ? 1 : null;
        if (!(this.zzbwp == i && this.zzbwq == i2)) {
            obj = null;
        }
        if (!(this.zzbwn == null || obj2 == null || r1 == null)) {
            if (this.zzbwz != 0) {
                seekTo(this.zzbwz);
            }
            play();
        }
        if (this.zzbwx != null) {
            this.zzbwx.zzg(i, i2);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzbwj.zzb(this);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzkn.v("AdMediaPlayerView size changed: " + i + " x " + i2);
        this.zzbwp = mediaPlayer.getVideoWidth();
        this.zzbwq = mediaPlayer.getVideoHeight();
        if (this.zzbwp != 0 && this.zzbwq != 0) {
            requestLayout();
        }
    }

    public void pause() {
        zzkn.v("AdMediaPlayerView pause");
        if (zzol() && this.zzbwn.isPlaying()) {
            this.zzbwn.pause();
            zzaf(4);
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzc zzbxb;

                {
                    this.zzbxb = r1;
                }

                public void run() {
                    if (this.zzbxb.zzbxa != null) {
                        this.zzbxb.zzbxa.onPaused();
                    }
                }
            });
        }
        zzag(4);
    }

    public void play() {
        zzkn.v("AdMediaPlayerView play");
        if (zzol()) {
            this.zzbwn.start();
            zzaf(3);
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzc zzbxb;

                {
                    this.zzbxb = r1;
                }

                public void run() {
                    if (this.zzbxb.zzbxa != null) {
                        this.zzbxb.zzbxa.zzpk();
                    }
                }
            });
        }
        zzag(3);
    }

    public void seekTo(int i) {
        zzkn.v("AdMediaPlayerView seek " + i);
        if (zzol()) {
            this.zzbwn.seekTo(i);
            this.zzbwz = 0;
            return;
        }
        this.zzbwz = i;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        this.zzbwo = uri;
        this.zzbwz = 0;
        zzoh();
        requestLayout();
        invalidate();
    }

    public void stop() {
        zzkn.v("AdMediaPlayerView stop");
        if (this.zzbwn != null) {
            this.zzbwn.stop();
            this.zzbwn.release();
            this.zzbwn = null;
            zzaf(0);
            zzag(0);
            zzok();
        }
        this.zzbwj.onStop();
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getName());
        String valueOf2 = String.valueOf(Integer.toHexString(hashCode()));
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("@").append(valueOf2).toString();
    }

    public void zza(float f) {
        this.zzbwu = f;
        zzoq();
    }

    public void zza(float f, float f2) {
        if (this.zzbwx != null) {
            this.zzbwx.zzb(f, f2);
        }
    }

    public void zza(zzh com_google_android_gms_ads_internal_overlay_zzh) {
        this.zzbxa = com_google_android_gms_ads_internal_overlay_zzh;
    }

    public String zzog() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzbwy ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public void zzom() {
        this.zzbwv = true;
        zzoq();
    }

    public void zzon() {
        this.zzbwv = false;
        zzoq();
    }
}
