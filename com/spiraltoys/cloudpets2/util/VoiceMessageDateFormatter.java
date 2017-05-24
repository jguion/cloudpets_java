package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.text.format.DateUtils;
import com.google.android.vending.expansion.downloader.Constants;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.Calendar;
import java.util.Date;

public class VoiceMessageDateFormatter {
    private Context mContext;
    private VoiceMessage mVoiceMessage;

    public VoiceMessageDateFormatter(Context context, VoiceMessage voiceMessage) {
        this.mContext = context;
        this.mVoiceMessage = voiceMessage;
    }

    public String getDetailedRelativeCreationDateString() {
        CharSequence dateString;
        Calendar midnightCalendar = Calendar.getInstance();
        midnightCalendar.set(10, 0);
        midnightCalendar.set(12, 0);
        midnightCalendar.set(13, 0);
        midnightCalendar.set(14, 0);
        Calendar nowCalendar = Calendar.getInstance();
        long timeSinceMidnight = nowCalendar.getTimeInMillis() - midnightCalendar.getTimeInMillis();
        Date createdDate = this.mVoiceMessage.getCreatedAt();
        Calendar thenCalendar = Calendar.getInstance();
        thenCalendar.setTime(createdDate);
        if (nowCalendar.getTimeInMillis() - thenCalendar.getTimeInMillis() > timeSinceMidnight) {
            dateString = DateUtils.getRelativeDateTimeString(this.mContext, createdDate.getTime(), Constants.WATCHDOG_WAKE_TIMER, 604800000, 0);
        } else {
            dateString = DateUtils.getRelativeTimeSpanString(createdDate.getTime(), nowCalendar.getTimeInMillis(), Constants.WATCHDOG_WAKE_TIMER, 0);
        }
        return this.mContext.getString(R.string.label_message_sent_string_format, new Object[]{dateString});
    }
}
