package com.google.android.vending.expansion.downloader;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

class DownloaderClientMarshaller$Proxy implements IDownloaderClient {
    private Messenger mServiceMessenger;

    public void onDownloadStateChanged(int newState) {
        Bundle params = new Bundle(1);
        params.putInt(DownloaderClientMarshaller.PARAM_NEW_STATE, newState);
        send(10, params);
    }

    public void onDownloadProgress(DownloadProgressInfo progress) {
        Bundle params = new Bundle(1);
        params.putParcelable("progress", progress);
        send(11, params);
    }

    private void send(int method, Bundle params) {
        Message m = Message.obtain(null, method);
        m.setData(params);
        try {
            this.mServiceMessenger.send(m);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public DownloaderClientMarshaller$Proxy(Messenger msg) {
        this.mServiceMessenger = msg;
    }

    public void onServiceConnected(Messenger m) {
    }
}
