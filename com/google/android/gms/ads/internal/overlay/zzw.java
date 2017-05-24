package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzde;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zziy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zziy
@TargetApi(14)
public class zzw extends Thread implements OnFrameAvailableListener, zza {
    private static final float[] zzbzk = new float[]{-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private int zzajw;
    private int zzajx;
    private final float[] zzbzg;
    private final zzv zzbzl;
    private final float[] zzbzm;
    private final float[] zzbzn;
    private final float[] zzbzo;
    private final float[] zzbzp;
    private final float[] zzbzq;
    private final float[] zzbzr;
    private float zzbzs;
    private float zzbzt;
    private float zzbzu;
    private SurfaceTexture zzbzv;
    private SurfaceTexture zzbzw;
    private int zzbzx;
    private int zzbzy;
    private int zzbzz;
    private FloatBuffer zzcaa = ByteBuffer.allocateDirect(zzbzk.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final CountDownLatch zzcab;
    private final Object zzcac;
    private EGL10 zzcad;
    private EGLDisplay zzcae;
    private EGLContext zzcaf;
    private EGLSurface zzcag;
    private volatile boolean zzcah;
    private volatile boolean zzcai;

    public zzw(Context context) {
        super("SphericalVideoProcessor");
        this.zzcaa.put(zzbzk).position(0);
        this.zzbzg = new float[9];
        this.zzbzm = new float[9];
        this.zzbzn = new float[9];
        this.zzbzo = new float[9];
        this.zzbzp = new float[9];
        this.zzbzq = new float[9];
        this.zzbzr = new float[9];
        this.zzbzs = Float.NaN;
        this.zzbzl = new zzv(context);
        this.zzbzl.zza((zza) this);
        this.zzcab = new CountDownLatch(1);
        this.zzcac = new Object();
    }

    private void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = (float) Math.cos((double) f);
        fArr[5] = (float) (-Math.sin((double) f));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin((double) f);
        fArr[8] = (float) Math.cos((double) f);
    }

    private void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = ((fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3])) + (fArr2[2] * fArr3[6]);
        fArr[1] = ((fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4])) + (fArr2[2] * fArr3[7]);
        fArr[2] = ((fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5])) + (fArr2[2] * fArr3[8]);
        fArr[3] = ((fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3])) + (fArr2[5] * fArr3[6]);
        fArr[4] = ((fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4])) + (fArr2[5] * fArr3[7]);
        fArr[5] = ((fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5])) + (fArr2[5] * fArr3[8]);
        fArr[6] = ((fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3])) + (fArr2[8] * fArr3[6]);
        fArr[7] = ((fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4])) + (fArr2[8] * fArr3[7]);
        fArr[8] = ((fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5])) + (fArr2[8] * fArr3[8]);
    }

    private float[] zza(float[] fArr, float[] fArr2) {
        return new float[]{((fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1])) + (fArr[2] * fArr2[2]), ((fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1])) + (fArr[5] * fArr2[2]), ((fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1])) + (fArr[8] * fArr2[2])};
    }

    private void zzb(float[] fArr, float f) {
        fArr[0] = (float) Math.cos((double) f);
        fArr[1] = (float) (-Math.sin((double) f));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin((double) f);
        fArr[4] = (float) Math.cos((double) f);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private float zzc(float[] fArr) {
        float[] zza = zza(fArr, new float[]{0.0f, 1.0f, 0.0f});
        return ((float) Math.atan2((double) zza[1], (double) zza[0])) - 1.5707964f;
    }

    private int zzc(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzcb("createShader");
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            zzcb("shaderSource");
            GLES20.glCompileShader(glCreateShader);
            zzcb("compileShader");
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            zzcb("getShaderiv");
            if (iArr[0] == 0) {
                Log.e("SphericalVideoRenderer", "Could not compile shader " + i + ":");
                Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                zzcb("deleteShader");
                return 0;
            }
        }
        return glCreateShader;
    }

    private void zzcb(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("SphericalVideoRenderer", new StringBuilder(String.valueOf(str).length() + 21).append(str).append(": glError ").append(glGetError).toString());
        }
    }

    private void zzpw() {
        GLES20.glViewport(0, 0, this.zzajw, this.zzajx);
        zzcb("viewport");
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.zzbzx, "uFOVx");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzbzx, "uFOVy");
        if (this.zzajw > this.zzajx) {
            GLES20.glUniform1f(glGetUniformLocation, 0.87266463f);
            GLES20.glUniform1f(glGetUniformLocation2, (((float) this.zzajx) * 0.87266463f) / ((float) this.zzajw));
            return;
        }
        GLES20.glUniform1f(glGetUniformLocation, (((float) this.zzajw) * 0.87266463f) / ((float) this.zzajx));
        GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f);
    }

    private int zzpy() {
        int zzc = zzc(35633, zzqb());
        if (zzc == 0) {
            return 0;
        }
        int zzc2 = zzc(35632, zzqc());
        if (zzc2 == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        zzcb("createProgram");
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, zzc);
            zzcb("attachShader");
            GLES20.glAttachShader(glCreateProgram, zzc2);
            zzcb("attachShader");
            GLES20.glLinkProgram(glCreateProgram);
            zzcb("linkProgram");
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
            zzcb("getProgramiv");
            if (iArr[0] != 1) {
                Log.e("SphericalVideoRenderer", "Could not link program: ");
                Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(glCreateProgram));
                GLES20.glDeleteProgram(glCreateProgram);
                zzcb("deleteProgram");
                return 0;
            }
            GLES20.glValidateProgram(glCreateProgram);
            zzcb("validateProgram");
        }
        return glCreateProgram;
    }

    @Nullable
    private EGLConfig zzqa() {
        int[] iArr = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        return !this.zzcad.eglChooseConfig(this.zzcae, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344}, eGLConfigArr, 1, iArr) ? null : iArr[0] > 0 ? eGLConfigArr[0] : null;
    }

    private String zzqb() {
        zzde com_google_android_gms_internal_zzde = zzdi.zzbdq;
        return !((String) com_google_android_gms_internal_zzde.get()).equals(com_google_android_gms_internal_zzde.zzkq()) ? (String) com_google_android_gms_internal_zzde.get() : "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}";
    }

    private String zzqc() {
        zzde com_google_android_gms_internal_zzde = zzdi.zzbdr;
        return !((String) com_google_android_gms_internal_zzde.get()).equals(com_google_android_gms_internal_zzde.zzkq()) ? (String) com_google_android_gms_internal_zzde.get() : "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}";
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzbzz++;
        synchronized (this.zzcac) {
            this.zzcac.notifyAll();
        }
    }

    public void run() {
        Object obj = 1;
        if (this.zzbzw == null) {
            zzb.e("SphericalVideoProcessor started with no output texture.");
            this.zzcab.countDown();
            return;
        }
        boolean zzpz = zzpz();
        int zzpx = zzpx();
        if (this.zzbzx == 0) {
            obj = null;
        }
        if (!zzpz || r0 == null) {
            String str = "EGL initialization failed: ";
            String valueOf = String.valueOf(GLUtils.getEGLErrorString(this.zzcad.eglGetError()));
            valueOf = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            zzb.e(valueOf);
            zzu.zzgd().zza(new Throwable(valueOf), "SphericalVideoProcessor.run.1");
            zzqd();
            this.zzcab.countDown();
            return;
        }
        this.zzbzv = new SurfaceTexture(zzpx);
        this.zzbzv.setOnFrameAvailableListener(this);
        this.zzcab.countDown();
        this.zzbzl.start();
        try {
            this.zzcah = true;
            while (!this.zzcai) {
                zzpv();
                if (this.zzcah) {
                    zzpw();
                    this.zzcah = false;
                }
                try {
                    synchronized (this.zzcac) {
                        if (!(this.zzcai || this.zzcah || this.zzbzz != 0)) {
                            this.zzcac.wait();
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        } catch (IllegalStateException e2) {
            zzb.zzdf("SphericalVideoProcessor halted unexpectedly.");
        } catch (Throwable th) {
            zzb.zzb("SphericalVideoProcessor died.", th);
            zzu.zzgd().zza(th, "SphericalVideoProcessor.run.2");
        } finally {
            this.zzbzl.stop();
            this.zzbzv.setOnFrameAvailableListener(null);
            this.zzbzv = null;
            zzqd();
        }
    }

    public void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzajw = i;
        this.zzajx = i2;
        this.zzbzw = surfaceTexture;
    }

    public void zzb(float f, float f2) {
        float f3;
        float f4;
        if (this.zzajw > this.zzajx) {
            f3 = (1.7453293f * f) / ((float) this.zzajw);
            f4 = (1.7453293f * f2) / ((float) this.zzajw);
        } else {
            f3 = (1.7453293f * f) / ((float) this.zzajx);
            f4 = (1.7453293f * f2) / ((float) this.zzajx);
        }
        this.zzbzt -= f3;
        this.zzbzu -= f4;
        if (this.zzbzu < -1.5707964f) {
            this.zzbzu = -1.5707964f;
        }
        if (this.zzbzu > 1.5707964f) {
            this.zzbzu = 1.5707964f;
        }
    }

    public void zzg(int i, int i2) {
        synchronized (this.zzcac) {
            this.zzajw = i;
            this.zzajx = i2;
            this.zzcah = true;
            this.zzcac.notifyAll();
        }
    }

    public void zzoy() {
        synchronized (this.zzcac) {
            this.zzcac.notifyAll();
        }
    }

    public void zzpt() {
        synchronized (this.zzcac) {
            this.zzcai = true;
            this.zzbzw = null;
            this.zzcac.notifyAll();
        }
    }

    public SurfaceTexture zzpu() {
        if (this.zzbzw == null) {
            return null;
        }
        try {
            this.zzcab.await();
        } catch (InterruptedException e) {
        }
        return this.zzbzv;
    }

    void zzpv() {
        while (this.zzbzz > 0) {
            this.zzbzv.updateTexImage();
            this.zzbzz--;
        }
        if (this.zzbzl.zzb(this.zzbzg)) {
            if (Float.isNaN(this.zzbzs)) {
                this.zzbzs = -zzc(this.zzbzg);
            }
            zzb(this.zzbzq, this.zzbzs + this.zzbzt);
        } else {
            zza(this.zzbzg, -1.5707964f);
            zzb(this.zzbzq, this.zzbzt);
        }
        zza(this.zzbzm, 1.5707964f);
        zza(this.zzbzn, this.zzbzq, this.zzbzm);
        zza(this.zzbzo, this.zzbzg, this.zzbzn);
        zza(this.zzbzp, this.zzbzu);
        zza(this.zzbzr, this.zzbzp, this.zzbzo);
        GLES20.glUniformMatrix3fv(this.zzbzy, 1, false, this.zzbzr, 0);
        GLES20.glDrawArrays(5, 0, 4);
        zzcb("drawArrays");
        GLES20.glFinish();
        this.zzcad.eglSwapBuffers(this.zzcae, this.zzcag);
    }

    int zzpx() {
        this.zzbzx = zzpy();
        GLES20.glUseProgram(this.zzbzx);
        zzcb("useProgram");
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzbzx, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, this.zzcaa);
        zzcb("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        zzcb("enableVertexAttribArray");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        zzcb("genTextures");
        glGetAttribLocation = iArr[0];
        GLES20.glBindTexture(36197, glGetAttribLocation);
        zzcb("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        zzcb("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzcb("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzcb("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzcb("texParameteri");
        this.zzbzy = GLES20.glGetUniformLocation(this.zzbzx, "uVMat");
        GLES20.glUniformMatrix3fv(this.zzbzy, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        return glGetAttribLocation;
    }

    boolean zzpz() {
        this.zzcad = (EGL10) EGLContext.getEGL();
        this.zzcae = this.zzcad.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.zzcae == EGL10.EGL_NO_DISPLAY) {
            return false;
        }
        if (!this.zzcad.eglInitialize(this.zzcae, new int[2])) {
            return false;
        }
        EGLConfig zzqa = zzqa();
        if (zzqa == null) {
            return false;
        }
        this.zzcaf = this.zzcad.eglCreateContext(this.zzcae, zzqa, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        if (this.zzcaf == null || this.zzcaf == EGL10.EGL_NO_CONTEXT) {
            return false;
        }
        this.zzcag = this.zzcad.eglCreateWindowSurface(this.zzcae, zzqa, this.zzbzw, null);
        return (this.zzcag == null || this.zzcag == EGL10.EGL_NO_SURFACE) ? false : this.zzcad.eglMakeCurrent(this.zzcae, this.zzcag, this.zzcag, this.zzcaf);
    }

    boolean zzqd() {
        boolean z = false;
        if (!(this.zzcag == null || this.zzcag == EGL10.EGL_NO_SURFACE)) {
            z = (this.zzcad.eglMakeCurrent(this.zzcae, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | 0) | this.zzcad.eglDestroySurface(this.zzcae, this.zzcag);
            this.zzcag = null;
        }
        if (this.zzcaf != null) {
            z |= this.zzcad.eglDestroyContext(this.zzcae, this.zzcaf);
            this.zzcaf = null;
        }
        if (this.zzcae == null) {
            return z;
        }
        z |= this.zzcad.eglTerminate(this.zzcae);
        this.zzcae = null;
        return z;
    }
}
