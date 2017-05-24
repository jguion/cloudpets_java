package com.google.android.vending.expansion.downloader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

class DownloaderClientMarshaller$Stub implements IStub {
    private boolean mBound;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            DownloaderClientMarshaller$Stub.this.mServiceMessenger = new Messenger(service);
            DownloaderClientMarshaller$Stub.this.mItf.onServiceConnected(DownloaderClientMarshaller$Stub.this.mServiceMessenger);
        }

        public void onServiceDisconnected(ComponentName className) {
            DownloaderClientMarshaller$Stub.this.mServiceMessenger = null;
        }
    };
    private Context mContext;
    private Class<?> mDownloaderServiceClass;
    private IDownloaderClient mItf = null;
    final Messenger mMessenger = new Messenger(new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10:
                    DownloaderClientMarshaller$Stub.this.mItf.onDownloadStateChanged(msg.getData().getInt(DownloaderClientMarshaller.PARAM_NEW_STATE));
                    return;
                case 11:
                    Bundle bun = msg.getData();
                    if (DownloaderClientMarshaller$Stub.this.mContext != null) {
                        bun.setClassLoader(DownloaderClientMarshaller$Stub.this.mContext.getClassLoader());
                        DownloaderClientMarshaller$Stub.this.mItf.onDownloadProgress((DownloadProgressInfo) msg.getData().getParcelable("progress"));
                        return;
                    }
                    return;
                case 12:
                    DownloaderClientMarshaller$Stub.this.mItf.onServiceConnected((Messenger) msg.getData().getParcelable("EMH"));
                    return;
                default:
                    return;
            }
        }
    });
    private Messenger mServiceMessenger;

    public DownloaderClientMarshaller$Stub(IDownloaderClient itf, Class<?> downloaderService) {
        this.mItf = itf;
        this.mDownloaderServiceClass = downloaderService;
    }

    public void connect(Context c) {
        this.mContext = c;
        Intent bindIntent = new Intent(c, this.mDownloaderServiceClass);
        bindIntent.putExtra("EMH", this.mMessenger);
        if (c.bindService(bindIntent, this.mConnection, 2)) {
            this.mBound = true;
        }
    }

    public void disconnect(Context c) {
        if (this.mBound) {
            c.unbindService(this.mConnection);
            this.mBound = false;
        }
        this.mContext = null;
    }

    public Messenger getMessenger() {
        return this.mMessenger;
    }
}
