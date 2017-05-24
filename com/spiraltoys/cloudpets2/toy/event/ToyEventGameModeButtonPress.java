package com.spiraltoys.cloudpets2.toy.event;

public class ToyEventGameModeButtonPress {
    private Button mButton;

    public enum Button {
        RECORD,
        PLAY
    }

    public ToyEventGameModeButtonPress(Button button) {
        this.mButton = button;
    }

    public Button getButton() {
        return this.mButton;
    }
}
