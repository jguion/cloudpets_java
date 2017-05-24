package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.model.Story;

public class StoryClickedEvent {
    private Story mStory;

    public StoryClickedEvent(Story Story) {
        this.mStory = Story;
    }

    public Story getStory() {
        return this.mStory;
    }
}
