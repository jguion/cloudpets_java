package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.fragments.BirthdayPickerFragment;
import com.spiraltoys.cloudpets2.fragments.BirthdayPickerFragment.OnBirthdayPickerInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import com.spiraltoys.cloudpets2.util.Utils;
import java.util.Calendar;
import java.util.Random;

public class WelcomeActivity extends InteractiveToyActivity {
    private static final int MINIMUM_REGISTRATION_AGE = 13;
    private static final String TAG_DIALOG_FRAGMENT = "tag_dialog_fragment";
    private Character mCharacter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Character[] toys = Character.values();
        this.mCharacter = toys[new Random().nextInt(toys.length)];
        setTheme(PlushToy.getAppThemeResourceForCharacter(this.mCharacter));
        setContentView((int) R.layout.activity_welcome);
        ButterKnife.inject((Activity) this);
    }

    @OnClick({2131755201})
    void onLogInClicked() {
        startActivity(new Intent(this, LogInActivity.class));
    }

    @OnClick({2131755224})
    void onCreateAccountClicked() {
        final WelcomeActivity that = this;
        BirthdayPickerFragment newFragment = new BirthdayPickerFragment();
        newFragment.setListener(new OnBirthdayPickerInteractionListener() {
            public void onDateSelect(Calendar date) {
                date.add(1, 13);
                if (date.compareTo(Calendar.getInstance()) > 0) {
                    Utils.showHintDialog(WelcomeActivity.this, WelcomeActivity.this.getString(R.string.hint_registration_age_requirement, new Object[]{Integer.valueOf(13)}));
                    return;
                }
                TermsOfUseActivity.start(that, false);
            }
        });
        newFragment.show(getFragmentManager(), TAG_DIALOG_FRAGMENT);
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return PlushToy.getBackgroundMusicTrackForCharacter(this.mCharacter);
    }
}
