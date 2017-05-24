package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v4.media.TransportMediator;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zziy;

@zziy
class zzv implements SensorEventListener {
    private final SensorManager zzbzb;
    private final Object zzbzc = new Object();
    private final Display zzbzd;
    private final float[] zzbze = new float[9];
    private final float[] zzbzf = new float[9];
    private float[] zzbzg;
    private Handler zzbzh;
    private zza zzbzi;

    interface zza {
        void zzoy();
    }

    zzv(Context context) {
        this.zzbzb = (SensorManager) context.getSystemService("sensor");
        this.zzbzd = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    private void zzf(int i, int i2) {
        float f = this.zzbzf[i];
        this.zzbzf[i] = this.zzbzf[i2];
        this.zzbzf[i2] = f;
    }

    int getRotation() {
        return this.zzbzd.getRotation();
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        zza(sensorEvent.values);
    }

    void start() {
        if (this.zzbzh == null) {
            Sensor defaultSensor = this.zzbzb.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzb.e("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            this.zzbzh = new Handler(handlerThread.getLooper());
            if (!this.zzbzb.registerListener(this, defaultSensor, 0, this.zzbzh)) {
                zzb.e("SensorManager.registerListener failed.");
                stop();
            }
        }
    }

    void stop() {
        if (this.zzbzh != null) {
            this.zzbzb.unregisterListener(this);
            this.zzbzh.post(new Runnable(this) {
                final /* synthetic */ zzv zzbzj;

                {
                    this.zzbzj = r1;
                }

                public void run() {
                    Looper.myLooper().quit();
                }
            });
            this.zzbzh = null;
        }
    }

    void zza(zza com_google_android_gms_ads_internal_overlay_zzv_zza) {
        this.zzbzi = com_google_android_gms_ads_internal_overlay_zzv_zza;
    }

    void zza(float[] fArr) {
        if (fArr[0] != 0.0f || fArr[1] != 0.0f || fArr[2] != 0.0f) {
            synchronized (this.zzbzc) {
                if (this.zzbzg == null) {
                    this.zzbzg = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzbze, fArr);
            switch (getRotation()) {
                case 1:
                    SensorManager.remapCoordinateSystem(this.zzbze, 2, 129, this.zzbzf);
                    break;
                case 2:
                    SensorManager.remapCoordinateSystem(this.zzbze, 129, TransportMediator.KEYCODE_MEDIA_RECORD, this.zzbzf);
                    break;
                case 3:
                    SensorManager.remapCoordinateSystem(this.zzbze, TransportMediator.KEYCODE_MEDIA_RECORD, 1, this.zzbzf);
                    break;
                default:
                    System.arraycopy(this.zzbze, 0, this.zzbzf, 0, 9);
                    break;
            }
            zzf(1, 3);
            zzf(2, 6);
            zzf(5, 7);
            synchronized (this.zzbzc) {
                System.arraycopy(this.zzbzf, 0, this.zzbzg, 0, 9);
            }
            if (this.zzbzi != null) {
                this.zzbzi.zzoy();
            }
        }
    }

    boolean zzb(float[] fArr) {
        boolean z = false;
        synchronized (this.zzbzc) {
            if (this.zzbzg == null) {
            } else {
                System.arraycopy(this.zzbzg, 0, fArr, 0, this.zzbzg.length);
                z = true;
            }
        }
        return z;
    }
}
