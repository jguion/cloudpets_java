package com.parse;

import bolts.Task;
import java.util.Map;

class ParseAnalyticsController {
    ParseEventuallyQueue eventuallyQueue;

    public ParseAnalyticsController(ParseEventuallyQueue eventuallyQueue) {
        this.eventuallyQueue = eventuallyQueue;
    }

    public Task<Void> trackEventInBackground(String name, Map<String, String> dimensions, String sessionToken) {
        return this.eventuallyQueue.enqueueEventuallyAsync(ParseRESTAnalyticsCommand.trackEventCommand(name, dimensions, sessionToken), null).makeVoid();
    }

    public Task<Void> trackAppOpenedInBackground(String pushHash, String sessionToken) {
        return this.eventuallyQueue.enqueueEventuallyAsync(ParseRESTAnalyticsCommand.trackAppOpenedCommand(pushHash, sessionToken), null).makeVoid();
    }
}
