package com.spiraltoys.cloudpets2.expansion;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;
import com.spiraltoys.cloudpets2.CloudActionBarActivity;
import com.spiraltoys.cloudpets2.free.R;

public class ExpansionDownloaderActivity extends CloudActionBarActivity implements IDownloaderClient {
    private static final String LOG_TAG = "LVLDownloader";
    private static final int PERMISSIONS_REQUEST_ACCESS_EXTERNAL_STORAGE = 42;
    private static final float SMOOTHING_FACTOR = 0.005f;
    private TextView mAverageSpeed;
    private boolean mCancelValidation;
    private View mCellMessage;
    private View mDashboard;
    private IStub mDownloaderClientStub;
    private ProgressBar mPB;
    private Button mPauseButton;
    private TextView mProgressFraction;
    private TextView mProgressPercent;
    private IDownloaderService mRemoteService;
    private int mState;
    private boolean mStatePaused;
    private TextView mStatusText;
    private TextView mTimeRemaining;
    private Button mWiFiSettingsButton;

    private void setState(int newState) {
        if (this.mState != newState) {
            this.mState = newState;
            this.mStatusText.setText(Helpers.getDownloaderStringResourceIDFromState(newState));
        }
    }

    private void setButtonPausedState(boolean paused) {
        this.mStatePaused = paused;
        this.mPauseButton.setText(paused ? R.string.text_button_resume : R.string.text_button_pause);
    }

    void validateXAPKZipFiles() {
        new AsyncTask<Object, DownloadProgressInfo, Boolean>() {
            protected void onPreExecute() {
                ExpansionDownloaderActivity.this.mDashboard.setVisibility(0);
                ExpansionDownloaderActivity.this.mCellMessage.setVisibility(8);
                ExpansionDownloaderActivity.this.mStatusText.setText(R.string.text_verifying_download);
                ExpansionDownloaderActivity.this.mPauseButton.setOnClickListener(new 1(this));
                ExpansionDownloaderActivity.this.mPauseButton.setText(R.string.text_button_cancel_verify);
                super.onPreExecute();
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            protected java.lang.Boolean doInBackground(java.lang.Object... r41) {
                /*
                r40 = this;
                r34 = com.spiraltoys.cloudpets2.expansion.ExpansionUtils.EXPANSION_FILE_DESCRIPTORS;
                r0 = r34;
                r0 = r0.length;
                r35 = r0;
                r3 = 0;
                r33 = r3;
            L_0x000a:
                r0 = r33;
                r1 = r35;
                if (r0 >= r1) goto L_0x01a6;
            L_0x0010:
                r30 = r34[r33];
                r0 = r40;
                r3 = com.spiraltoys.cloudpets2.expansion.ExpansionDownloaderActivity.this;
                r6 = r30.isMain();
                r7 = r30.getFileVersion();
                r20 = com.google.android.vending.expansion.downloader.Helpers.getExpansionAPKFileName(r3, r6, r7);
                r0 = r40;
                r3 = com.spiraltoys.cloudpets2.expansion.ExpansionDownloaderActivity.this;
                r6 = r30.getFileSize();
                r32 = 0;
                r0 = r20;
                r1 = r32;
                r3 = com.google.android.vending.expansion.downloader.Helpers.doesFileExist(r3, r0, r6, r1);
                if (r3 != 0) goto L_0x003c;
            L_0x0036:
                r3 = 0;
                r3 = java.lang.Boolean.valueOf(r3);
            L_0x003b:
                return r3;
            L_0x003c:
                r0 = r40;
                r3 = com.spiraltoys.cloudpets2.expansion.ExpansionDownloaderActivity.this;
                r0 = r20;
                r20 = com.google.android.vending.expansion.downloader.Helpers.generateSaveFileName(r3, r0);
                r3 = 262144; // 0x40000 float:3.67342E-40 double:1.295163E-318;
                r2 = new byte[r3];
                r31 = new com.android.vending.expansion.zipfile.ZipResourceFile;	 Catch:{ IOException -> 0x012c }
                r0 = r31;
                r1 = r20;
                r0.<init>(r1);	 Catch:{ IOException -> 0x012c }
                r18 = r31.getAllEntries();	 Catch:{ IOException -> 0x012c }
                r4 = 0;
                r0 = r18;
                r6 = r0.length;	 Catch:{ IOException -> 0x012c }
                r3 = 0;
            L_0x005d:
                if (r3 >= r6) goto L_0x006c;
            L_0x005f:
                r19 = r18[r3];	 Catch:{ IOException -> 0x012c }
                r0 = r19;
                r0 = r0.mCompressedLength;	 Catch:{ IOException -> 0x012c }
                r36 = r0;
                r4 = r4 + r36;
                r3 = r3 + 1;
                goto L_0x005d;
            L_0x006c:
                r10 = 0;
                r28 = r4;
                r0 = r18;
                r0 = r0.length;	 Catch:{ IOException -> 0x012c }
                r36 = r0;
                r3 = 0;
                r32 = r3;
            L_0x0077:
                r0 = r32;
                r1 = r36;
                if (r0 >= r1) goto L_0x01a0;
            L_0x007d:
                r19 = r18[r32];	 Catch:{ IOException -> 0x012c }
                r6 = -1;
                r0 = r19;
                r0 = r0.mCRC32;	 Catch:{ IOException -> 0x012c }
                r38 = r0;
                r3 = (r6 > r38 ? 1 : (r6 == r38 ? 0 : -1));
                if (r3 == 0) goto L_0x0193;
            L_0x008b:
                r0 = r19;
                r0 = r0.mUncompressedLength;	 Catch:{ IOException -> 0x012c }
                r22 = r0;
                r11 = new java.util.zip.CRC32;	 Catch:{ IOException -> 0x012c }
                r11.<init>();	 Catch:{ IOException -> 0x012c }
                r13 = 0;
                r16 = new java.io.DataInputStream;	 Catch:{ all -> 0x0199 }
                r0 = r19;
                r3 = r0.mFileName;	 Catch:{ all -> 0x0199 }
                r0 = r31;
                r3 = r0.getInputStream(r3);	 Catch:{ all -> 0x0199 }
                r0 = r16;
                r0.<init>(r3);	 Catch:{ all -> 0x0199 }
                r24 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x01ad }
            L_0x00ac:
                r6 = 0;
                r3 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1));
                if (r3 <= 0) goto L_0x013c;
            L_0x00b2:
                r3 = r2.length;	 Catch:{ all -> 0x01ad }
                r6 = (long) r3;	 Catch:{ all -> 0x01ad }
                r3 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1));
                if (r3 <= 0) goto L_0x0137;
            L_0x00b8:
                r3 = r2.length;	 Catch:{ all -> 0x01ad }
                r6 = (long) r3;	 Catch:{ all -> 0x01ad }
            L_0x00ba:
                r0 = (int) r6;	 Catch:{ all -> 0x01ad }
                r21 = r0;
                r3 = 0;
                r0 = r16;
                r1 = r21;
                r0.readFully(r2, r3, r1);	 Catch:{ all -> 0x01ad }
                r3 = 0;
                r0 = r21;
                r11.update(r2, r3, r0);	 Catch:{ all -> 0x01ad }
                r0 = r21;
                r6 = (long) r0;	 Catch:{ all -> 0x01ad }
                r22 = r22 - r6;
                r14 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x01ad }
                r26 = r14 - r24;
                r6 = 0;
                r3 = (r26 > r6 ? 1 : (r26 == r6 ? 0 : -1));
                if (r3 <= 0) goto L_0x0114;
            L_0x00dc:
                r0 = r21;
                r3 = (float) r0;	 Catch:{ all -> 0x01ad }
                r0 = r26;
                r6 = (float) r0;	 Catch:{ all -> 0x01ad }
                r12 = r3 / r6;
                r3 = 0;
                r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1));
                if (r3 == 0) goto L_0x013a;
            L_0x00e9:
                r3 = 1000593162; // 0x3ba3d70a float:0.005 double:4.94358707E-315;
                r3 = r3 * r12;
                r6 = 1065269330; // 0x3f7eb852 float:0.995 double:5.263129795E-315;
                r6 = r6 * r10;
                r10 = r3 + r6;
            L_0x00f3:
                r0 = r21;
                r6 = (long) r0;	 Catch:{ all -> 0x01ad }
                r28 = r28 - r6;
                r0 = r28;
                r3 = (float) r0;	 Catch:{ all -> 0x01ad }
                r3 = r3 / r10;
                r8 = (long) r3;	 Catch:{ all -> 0x01ad }
                r3 = 1;
                r0 = new com.google.android.vending.expansion.downloader.DownloadProgressInfo[r3];	 Catch:{ all -> 0x01ad }
                r37 = r0;
                r38 = 0;
                r3 = new com.google.android.vending.expansion.downloader.DownloadProgressInfo;	 Catch:{ all -> 0x01ad }
                r6 = r4 - r28;
                r3.<init>(r4, r6, r8, r10);	 Catch:{ all -> 0x01ad }
                r37[r38] = r3;	 Catch:{ all -> 0x01ad }
                r0 = r40;
                r1 = r37;
                r0.publishProgress(r1);	 Catch:{ all -> 0x01ad }
            L_0x0114:
                r24 = r14;
                r0 = r40;
                r3 = com.spiraltoys.cloudpets2.expansion.ExpansionDownloaderActivity.this;	 Catch:{ all -> 0x01ad }
                r3 = r3.mCancelValidation;	 Catch:{ all -> 0x01ad }
                if (r3 == 0) goto L_0x00ac;
            L_0x0120:
                r3 = 1;
                r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ all -> 0x01ad }
                if (r16 == 0) goto L_0x003b;
            L_0x0127:
                r16.close();	 Catch:{ IOException -> 0x012c }
                goto L_0x003b;
            L_0x012c:
                r17 = move-exception;
                r17.printStackTrace();
                r3 = 0;
                r3 = java.lang.Boolean.valueOf(r3);
                goto L_0x003b;
            L_0x0137:
                r6 = r22;
                goto L_0x00ba;
            L_0x013a:
                r10 = r12;
                goto L_0x00f3;
            L_0x013c:
                r6 = r11.getValue();	 Catch:{ all -> 0x01ad }
                r0 = r19;
                r0 = r0.mCRC32;	 Catch:{ all -> 0x01ad }
                r38 = r0;
                r3 = (r6 > r38 ? 1 : (r6 == r38 ? 0 : -1));
                if (r3 == 0) goto L_0x018e;
            L_0x014a:
                r3 = "LVLDL";
                r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ad }
                r6.<init>();	 Catch:{ all -> 0x01ad }
                r7 = "CRC does not match for entry: ";
                r6 = r6.append(r7);	 Catch:{ all -> 0x01ad }
                r0 = r19;
                r7 = r0.mFileName;	 Catch:{ all -> 0x01ad }
                r6 = r6.append(r7);	 Catch:{ all -> 0x01ad }
                r6 = r6.toString();	 Catch:{ all -> 0x01ad }
                android.util.Log.e(r3, r6);	 Catch:{ all -> 0x01ad }
                r3 = "LVLDL";
                r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ad }
                r6.<init>();	 Catch:{ all -> 0x01ad }
                r7 = "In file: ";
                r6 = r6.append(r7);	 Catch:{ all -> 0x01ad }
                r7 = r19.getZipFileName();	 Catch:{ all -> 0x01ad }
                r6 = r6.append(r7);	 Catch:{ all -> 0x01ad }
                r6 = r6.toString();	 Catch:{ all -> 0x01ad }
                android.util.Log.e(r3, r6);	 Catch:{ all -> 0x01ad }
                r3 = 0;
                r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ all -> 0x01ad }
                if (r16 == 0) goto L_0x003b;
            L_0x0189:
                r16.close();	 Catch:{ IOException -> 0x012c }
                goto L_0x003b;
            L_0x018e:
                if (r16 == 0) goto L_0x0193;
            L_0x0190:
                r16.close();	 Catch:{ IOException -> 0x012c }
            L_0x0193:
                r3 = r32 + 1;
                r32 = r3;
                goto L_0x0077;
            L_0x0199:
                r3 = move-exception;
            L_0x019a:
                if (r13 == 0) goto L_0x019f;
            L_0x019c:
                r13.close();	 Catch:{ IOException -> 0x012c }
            L_0x019f:
                throw r3;	 Catch:{ IOException -> 0x012c }
            L_0x01a0:
                r3 = r33 + 1;
                r33 = r3;
                goto L_0x000a;
            L_0x01a6:
                r3 = 1;
                r3 = java.lang.Boolean.valueOf(r3);
                goto L_0x003b;
            L_0x01ad:
                r3 = move-exception;
                r13 = r16;
                goto L_0x019a;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.spiraltoys.cloudpets2.expansion.ExpansionDownloaderActivity.1.doInBackground(java.lang.Object[]):java.lang.Boolean");
            }

            protected void onProgressUpdate(DownloadProgressInfo... values) {
                ExpansionDownloaderActivity.this.onDownloadProgress(values[0]);
                super.onProgressUpdate(values);
            }

            protected void onPostExecute(Boolean result) {
                if (result.booleanValue()) {
                    ExpansionDownloaderActivity.this.finish();
                } else {
                    ExpansionDownloaderActivity.this.mDashboard.setVisibility(0);
                    ExpansionDownloaderActivity.this.mCellMessage.setVisibility(8);
                    ExpansionDownloaderActivity.this.mStatusText.setText(R.string.text_validation_failed);
                    ExpansionDownloaderActivity.this.mPauseButton.setOnClickListener(new 2(this));
                    ExpansionDownloaderActivity.this.mPauseButton.setText(17039360);
                }
                super.onPostExecute(result);
            }
        }.execute(new Object[]{new Object()});
    }

    private void initializeDownloadUI() {
        this.mDownloaderClientStub = DownloaderClientMarshaller.CreateStub(this, ExpansionDownloaderService.class);
        setContentView((int) R.layout.activity_expansion_downloader);
        ButterKnife.inject((Activity) this);
        this.mPB = (ProgressBar) findViewById(R.id.progressBar);
        this.mStatusText = (TextView) findViewById(R.id.statusText);
        this.mProgressFraction = (TextView) findViewById(R.id.progressAsFraction);
        this.mProgressPercent = (TextView) findViewById(R.id.progressAsPercentage);
        this.mAverageSpeed = (TextView) findViewById(R.id.progressAverageSpeed);
        this.mTimeRemaining = (TextView) findViewById(R.id.progressTimeRemaining);
        this.mDashboard = findViewById(R.id.downloaderDashboard);
        this.mCellMessage = findViewById(R.id.approveCellular);
        this.mPauseButton = (Button) findViewById(R.id.pauseButton);
        this.mWiFiSettingsButton = (Button) findViewById(R.id.wifiSettingsButton);
        this.mPauseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ExpansionDownloaderActivity.this.mStatePaused) {
                    ExpansionDownloaderActivity.this.mRemoteService.requestContinueDownload();
                } else {
                    ExpansionDownloaderActivity.this.mRemoteService.requestPauseDownload();
                }
                ExpansionDownloaderActivity.this.setButtonPausedState(!ExpansionDownloaderActivity.this.mStatePaused);
            }
        });
        this.mWiFiSettingsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ExpansionDownloaderActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        ((Button) findViewById(R.id.resumeOverCellular)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExpansionDownloaderActivity.this.mRemoteService.setDownloadFlags(1);
                ExpansionDownloaderActivity.this.mRemoteService.requestContinueDownload();
                ExpansionDownloaderActivity.this.mCellMessage.setVisibility(8);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 42:
                if (grantResults[0] == 0) {
                    fetchExpansionFiles();
                    return;
                } else {
                    finish();
                    return;
                }
            default:
                return;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDownloadUI();
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0 || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            fetchExpansionFiles();
            return;
        }
        requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 42);
    }

    private void fetchExpansionFiles() {
        if (ExpansionUtils.expansionFilesDelivered(this)) {
            validateXAPKZipFiles();
            return;
        }
        try {
            Intent launchIntent = getIntent();
            Intent intentToLaunchThisActivityFromNotification = new Intent(this, getClass());
            intentToLaunchThisActivityFromNotification.setFlags(335544320);
            intentToLaunchThisActivityFromNotification.setAction(launchIntent.getAction());
            if (launchIntent.getCategories() != null) {
                for (String category : launchIntent.getCategories()) {
                    intentToLaunchThisActivityFromNotification.addCategory(category);
                }
            }
            if (DownloaderClientMarshaller.startDownloadServiceIfRequired((Context) this, PendingIntent.getActivity(this, 0, intentToLaunchThisActivityFromNotification, 134217728), ExpansionDownloaderService.class) != 0) {
                initializeDownloadUI();
            }
        } catch (NameNotFoundException e) {
            Log.e(LOG_TAG, "Cannot find own package! MAYDAY!");
            e.printStackTrace();
        }
    }

    protected void onStart() {
        if (this.mDownloaderClientStub != null) {
            this.mDownloaderClientStub.connect(this);
        }
        super.onStart();
    }

    protected void onStop() {
        if (this.mDownloaderClientStub != null) {
            this.mDownloaderClientStub.disconnect(this);
        }
        super.onStop();
    }

    public void onServiceConnected(Messenger m) {
        this.mRemoteService = DownloaderServiceMarshaller.CreateProxy(m);
        this.mRemoteService.onClientUpdated(this.mDownloaderClientStub.getMessenger());
    }

    public void onDownloadStateChanged(int newState) {
        boolean paused;
        boolean indeterminate;
        int newDashboardVisibility;
        int cellMessageVisibility;
        setState(newState);
        boolean showDashboard = true;
        boolean showCellMessage = false;
        switch (newState) {
            case 1:
                paused = false;
                indeterminate = true;
                break;
            case 2:
            case 3:
                showDashboard = true;
                paused = false;
                indeterminate = true;
                break;
            case 4:
                paused = false;
                showDashboard = true;
                indeterminate = false;
                break;
            case 5:
                validateXAPKZipFiles();
                return;
            case 7:
                paused = true;
                indeterminate = false;
                break;
            case 8:
            case 9:
                showDashboard = false;
                paused = true;
                indeterminate = false;
                showCellMessage = true;
                break;
            case 12:
            case 14:
                paused = true;
                indeterminate = false;
                break;
            case 15:
            case 16:
            case 18:
            case 19:
                paused = true;
                showDashboard = false;
                indeterminate = false;
                break;
            default:
                paused = true;
                indeterminate = true;
                showDashboard = true;
                break;
        }
        if (showDashboard) {
            newDashboardVisibility = 0;
        } else {
            newDashboardVisibility = 8;
        }
        if (this.mDashboard.getVisibility() != newDashboardVisibility) {
            this.mDashboard.setVisibility(newDashboardVisibility);
        }
        if (showCellMessage) {
            cellMessageVisibility = 0;
        } else {
            cellMessageVisibility = 8;
        }
        if (this.mCellMessage.getVisibility() != cellMessageVisibility) {
            this.mCellMessage.setVisibility(cellMessageVisibility);
        }
        this.mPB.setIndeterminate(indeterminate);
        setButtonPausedState(paused);
    }

    public void onDownloadProgress(DownloadProgressInfo progress) {
        this.mAverageSpeed.setText(getString(R.string.kilobytes_per_second, new Object[]{Helpers.getSpeedString(progress.mCurrentSpeed)}));
        this.mTimeRemaining.setText(getString(R.string.time_remaining, new Object[]{Helpers.getTimeRemaining(progress.mTimeRemaining)}));
        this.mPB.setMax((int) (progress.mOverallTotal >> 8));
        this.mPB.setProgress((int) (progress.mOverallProgress >> 8));
        this.mProgressPercent.setText(Long.toString((progress.mOverallProgress * 100) / progress.mOverallTotal) + "%");
        this.mProgressFraction.setText(Helpers.getDownloadProgressString(progress.mOverallProgress, progress.mOverallTotal));
    }

    protected void onDestroy() {
        this.mCancelValidation = true;
        super.onDestroy();
    }
}
