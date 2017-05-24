package com.parse;

import bolts.Task;

interface ParseCurrentUserController extends ParseObjectCurrentController<ParseUser> {
    Task<ParseUser> getAsync(boolean z);

    Task<String> getCurrentSessionTokenAsync();

    Task<Void> logOutAsync();

    Task<Void> setIfNeededAsync(ParseUser parseUser);
}
