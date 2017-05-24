package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnTextChanged;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import icepick.Icepick;
import icepick.State;

public class SelectPetAvatarFragment extends Fragment {
    private static final String ARG_PET_NICKNAME = "pet_nickname";
    private static final String ARG_SELECTED_CHARACTER = "selected_character";
    @InjectView(2131755312)
    RadioButton mBearButton;
    @InjectView(2131755315)
    RadioButton mBunnyButton;
    @InjectView(2131755313)
    RadioButton mCatButton;
    @State
    Character mCharacter;
    @InjectView(2131755314)
    RadioButton mDogButton;
    private OnPetAvatarChangeListener mListener;
    @InjectView(2131755316)
    EditText mNickNameText;
    @State
    String mPetNickname;
    @InjectView(2131755311)
    RadioButton mUnicornButton;

    public interface OnPetAvatarChangeListener {
        void onPetChanged();

        void onPetCharacterChanged(Character character);

        void onPetNicknameChanged(CharSequence charSequence);
    }

    public static SelectPetAvatarFragment newInstance(Character selectedCharacter, String petNickname) {
        SelectPetAvatarFragment fragment = new SelectPetAvatarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SELECTED_CHARACTER, selectedCharacter.toString());
        args.putString(ARG_PET_NICKNAME, petNickname);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        if (getArguments() != null) {
            this.mCharacter = Character.getCharacter(getArguments().getString(ARG_SELECTED_CHARACTER));
            this.mPetNickname = getArguments().getString(ARG_PET_NICKNAME);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_pet_avatar, container, false);
        ButterKnife.inject((Object) this, view);
        this.mNickNameText.setText(this.mPetNickname);
        this.mNickNameText.addTextChangedListener(new 1(this));
        switch (2.$SwitchMap$com$spiraltoys$cloudpets2$model$PlushToy$Character[this.mCharacter.ordinal()]) {
            case 1:
                this.mBearButton.setChecked(true);
                break;
            case 2:
                this.mCatButton.setChecked(true);
                break;
            case 3:
                this.mDogButton.setChecked(true);
                break;
            case 4:
                this.mBunnyButton.setChecked(true);
                break;
            case 5:
                this.mUnicornButton.setChecked(true);
                break;
        }
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnPetAvatarChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSelectPetAvatarListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @OnCheckedChanged({2131755312})
    void onBearChecked(boolean isChecked) {
        if (isChecked) {
            setCharacter(Character.BENTLEY_THE_BEAR);
        }
    }

    @OnCheckedChanged({2131755313})
    void onCatChecked(boolean isChecked) {
        if (isChecked) {
            setCharacter(Character.CHARLEY_THE_CAT);
        }
    }

    @OnCheckedChanged({2131755314})
    void onDogChecked(boolean isChecked) {
        if (isChecked) {
            setCharacter(Character.DIESEL_THE_DOG);
        }
    }

    @OnCheckedChanged({2131755315})
    void onBunnyChecked(boolean isChecked) {
        if (isChecked) {
            setCharacter(Character.BUBBLES_THE_BUNNY);
        }
    }

    @OnCheckedChanged({2131755311})
    void onUnicornChecked(boolean isChecked) {
        if (isChecked) {
            setCharacter(Character.STARBURST_THE_UNICORN);
        }
    }

    private void setCharacter(Character character) {
        if (character != this.mCharacter) {
            this.mCharacter = character;
            if (this.mListener != null) {
                this.mListener.onPetCharacterChanged(character);
                this.mListener.onPetChanged();
            }
        }
    }

    private boolean hasNickname() {
        return this.mPetNickname != null && this.mPetNickname.length() > 0;
    }

    @OnTextChanged({2131755316})
    void onPetNicknameChanged(CharSequence newNickname) {
        this.mPetNickname = newNickname.toString();
        if (this.mListener != null) {
            this.mListener.onPetNicknameChanged(newNickname);
        }
    }
}
