package com.spiraltoys.cloudpets2.model.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.Glide;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.AccountHelper.SignupCredentials;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

class AccountHelper$1 implements LogInCallback {
    final /* synthetic */ Context val$c;
    final /* synthetic */ SaveCallback val$callback;
    final /* synthetic */ SignupCredentials val$credentials;

    AccountHelper$1(SaveCallback saveCallback, SignupCredentials signupCredentials, Context context) {
        this.val$callback = saveCallback;
        this.val$credentials = signupCredentials;
        this.val$c = context;
    }

    public void done(ParseUser user, ParseException e) {
        if (e != null) {
            this.val$callback.done(e);
            return;
        }
        PrivateProfile privateProfile = new PrivateProfile();
        privateProfile.setProfileType(ProfileType.ADULT);
        final Profile profile = new Profile();
        profile.setDisplayName(SignupCredentials.access$000(this.val$credentials));
        profile.setUsername(SignupCredentials.access$100(this.val$credentials).toLowerCase());
        profile.setOwner(user);
        profile.setPrivateProfile(privateProfile);
        profile.getACL().setPublicReadAccess(true);
        if (SignupCredentials.access$200(this.val$credentials) != null) {
            new Thread(new Runnable() {
                public void run() {
                    Exception e;
                    ByteArrayOutputStream out;
                    byte[] imageData;
                    String fileName;
                    ParseFile parseFile;
                    ProfilePortrait portrait;
                    Runnable saveProfileRunnable = new Runnable() {
                        public void run() {
                            AccountHelper.access$400(AccountHelper$1.this.val$c, SignupCredentials.access$100(AccountHelper$1.this.val$credentials), SignupCredentials.access$300(AccountHelper$1.this.val$credentials), profile, AccountHelper$1.this.val$callback);
                        }
                    };
                    Bitmap bitmap = null;
                    try {
                        bitmap = (Bitmap) Glide.with(AccountHelper$1.this.val$c).load(SignupCredentials.access$200(AccountHelper$1.this.val$credentials)).asBitmap().centerCrop().into(AccountHelper$1.this.val$c.getResources().getDimensionPixelSize(R.dimen.profile_photo_width_px), AccountHelper$1.this.val$c.getResources().getDimensionPixelSize(R.dimen.profile_photo_height_px)).get();
                    } catch (InterruptedException e2) {
                        e = e2;
                        e.printStackTrace();
                        if (bitmap == null) {
                            out = new ByteArrayOutputStream();
                            bitmap.compress(CompressFormat.JPEG, 85, out);
                            imageData = out.toByteArray();
                            fileName = UUID.randomUUID().toString() + ".jpeg";
                            parseFile = new ParseFile(fileName, imageData);
                            try {
                                parseFile.save();
                                portrait = new ProfilePortrait();
                                portrait.setFile(parseFile);
                                portrait.setLocalFilename(fileName);
                                profile.setPortrait(portrait);
                                new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                            } catch (final ParseException e3) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        AccountHelper$1.this.val$callback.done(e3);
                                    }
                                });
                                return;
                            }
                        }
                        new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                        return;
                    } catch (ExecutionException e4) {
                        e = e4;
                        e.printStackTrace();
                        if (bitmap == null) {
                            new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                            return;
                        }
                        out = new ByteArrayOutputStream();
                        bitmap.compress(CompressFormat.JPEG, 85, out);
                        imageData = out.toByteArray();
                        fileName = UUID.randomUUID().toString() + ".jpeg";
                        parseFile = new ParseFile(fileName, imageData);
                        parseFile.save();
                        portrait = new ProfilePortrait();
                        portrait.setFile(parseFile);
                        portrait.setLocalFilename(fileName);
                        profile.setPortrait(portrait);
                        new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                    }
                    if (bitmap == null) {
                        new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                        return;
                    }
                    out = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.JPEG, 85, out);
                    imageData = out.toByteArray();
                    fileName = UUID.randomUUID().toString() + ".jpeg";
                    parseFile = new ParseFile(fileName, imageData);
                    parseFile.save();
                    portrait = new ProfilePortrait();
                    portrait.setFile(parseFile);
                    portrait.setLocalFilename(fileName);
                    profile.setPortrait(portrait);
                    new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                }
            }).start();
        } else {
            AccountHelper.access$400(this.val$c, SignupCredentials.access$100(this.val$credentials), SignupCredentials.access$300(this.val$credentials), profile, this.val$callback);
        }
    }
}
