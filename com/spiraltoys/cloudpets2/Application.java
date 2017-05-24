package com.spiraltoys.cloudpets2;

import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.google.android.gms.ads.MobileAds;
import com.parse.Parse;
import com.parse.Parse.Configuration;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendAcceptanceNotification;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PrivateProfile;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig.Builder;

public class Application extends MultiDexApplication {
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new Builder().setDefaultFontPath("fonts/merge_regular.otf").setFontAttrId(R.attr.fontPath).build());
        MobileAds.initialize(this, "ca-app-pub-6184197962257773~8563801840");
        ParseObject.registerSubclass(FriendRecord.class);
        ParseObject.registerSubclass(FriendAcceptanceNotification.class);
        ParseObject.registerSubclass(PlushToy.class);
        ParseObject.registerSubclass(PrivateProfile.class);
        ParseObject.registerSubclass(ProfilePortrait.class);
        ParseObject.registerSubclass(Profile.class);
        ParseObject.registerSubclass(VoiceMessage.class);
        ParseACL.setDefaultACL(new ParseACL(), true);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Configuration.Builder(getApplicationContext()).applicationId(BuildConfig.PARSE_APP_ID).clientKey(BuildConfig.PARSE_CLIENT_KEY).server(BuildConfig.PARSE_CLIENT_URL).enableLocalDataStore().build());
        ParsePush.subscribeInBackground("", new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}
