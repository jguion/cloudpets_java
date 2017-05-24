package com.google.android.vending.expansion.downloader.impl;

import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.android.vending.expansion.downloader.impl.DownloaderService.LVLRunnable;
import com.google.android.vending.licensing.APKExpansionPolicy;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.Policy;

class DownloaderService$LVLRunnable$1 implements LicenseCheckerCallback {
    final /* synthetic */ LVLRunnable this$1;
    final /* synthetic */ APKExpansionPolicy val$aep;

    DownloaderService$LVLRunnable$1(LVLRunnable this$1, APKExpansionPolicy aPKExpansionPolicy) {
        this.this$1 = this$1;
        this.val$aep = aPKExpansionPolicy;
    }

    public void allow(int reason) {
        try {
            int count = this.val$aep.getExpansionURLCount();
            DownloadsDB db = DownloadsDB.getDB(this.this$1.mContext);
            int status = 0;
            if (count != 0) {
                for (int i = 0; i < count; i++) {
                    String currentFileName = this.val$aep.getExpansionFileName(i);
                    if (currentFileName != null) {
                        DownloadInfo di = new DownloadInfo(i, currentFileName, this.this$1.mContext.getPackageName());
                        long fileSize = this.val$aep.getExpansionFileSize(i);
                        if (this.this$1.this$0.handleFileUpdated(db, i, currentFileName, fileSize)) {
                            status |= -1;
                            di.resetDownload();
                            di.mUri = this.val$aep.getExpansionURL(i);
                            di.mTotalBytes = fileSize;
                            di.mStatus = status;
                            db.updateDownload(di);
                        } else {
                            DownloadInfo dbdi = db.getDownloadInfoByFileName(di.mFileName);
                            if (dbdi == null) {
                                Log.d(Constants.TAG, "file " + di.mFileName + " found. Not downloading.");
                                di.mStatus = 200;
                                di.mTotalBytes = fileSize;
                                di.mCurrentBytes = fileSize;
                                di.mUri = this.val$aep.getExpansionURL(i);
                                db.updateDownload(di);
                            } else if (dbdi.mStatus != 200) {
                                dbdi.mUri = this.val$aep.getExpansionURL(i);
                                db.updateDownload(dbdi);
                                status |= -1;
                            }
                        }
                    }
                }
            }
            db.updateMetadata(this.this$1.mContext.getPackageManager().getPackageInfo(this.this$1.mContext.getPackageName(), 0).versionCode, status);
            switch (DownloaderService.startDownloadServiceIfRequired(this.this$1.mContext, DownloaderService.access$000(this.this$1.this$0), this.this$1.this$0.getClass())) {
                case 0:
                    DownloaderService.access$200(this.this$1.this$0).onDownloadStateChanged(5);
                    break;
                case 1:
                    Log.e(Constants.TAG, "In LVL checking loop!");
                    DownloaderService.access$200(this.this$1.this$0).onDownloadStateChanged(15);
                    throw new RuntimeException("Error with LVL checking and database integrity");
            }
            DownloaderService.access$100(false);
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
            throw new RuntimeException("Error with getting information from package name");
        } catch (Throwable th) {
            DownloaderService.access$100(false);
        }
    }

    public void dontAllow(int reason) {
        switch (reason) {
            case Policy.RETRY /*291*/:
                DownloaderService.access$200(this.this$1.this$0).onDownloadStateChanged(16);
                break;
            case Policy.NOT_LICENSED /*561*/:
                try {
                    DownloaderService.access$200(this.this$1.this$0).onDownloadStateChanged(15);
                    break;
                } catch (Throwable th) {
                    DownloaderService.access$100(false);
                }
        }
        DownloaderService.access$100(false);
    }

    public void applicationError(int errorCode) {
        try {
            DownloaderService.access$200(this.this$1.this$0).onDownloadStateChanged(16);
        } finally {
            DownloaderService.access$100(false);
        }
    }
}
