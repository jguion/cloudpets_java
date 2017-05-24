package com.spiraltoys.cloudpets2.expansion;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.google.common.base.Ascii;
import com.spiraltoys.cloudpets2.free.R;

public class ExpansionDownloaderService extends DownloaderService {
    private static final byte[] SALT = new byte[]{Ascii.FF, Ascii.SYN, (byte) -32, (byte) -42, (byte) 52, (byte) 62, (byte) -72, (byte) -82, (byte) 92, (byte) 2, (byte) -2, (byte) -5, (byte) 7, (byte) 8, (byte) -100, (byte) -104, (byte) -34, (byte) 44, (byte) -4, (byte) 84};

    public String getPublicKey() {
        return getString(R.string.licence_key);
    }

    public byte[] getSALT() {
        return SALT;
    }

    public String getAlarmReceiverClassName() {
        return ExpansionAlarmReceiver.class.getName();
    }
}
