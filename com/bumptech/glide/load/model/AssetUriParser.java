package com.bumptech.glide.load.model;

import android.net.Uri;
import com.spiraltoys.cloudpets2.model.VoiceMessage;

final class AssetUriParser {
    private static final String ASSET_PATH_SEGMENT = "android_asset";
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final int ASSET_PREFIX_LENGTH = ASSET_PREFIX.length();

    private AssetUriParser() {
    }

    public static boolean isAssetUri(Uri uri) {
        return VoiceMessage.FILE.equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && ASSET_PATH_SEGMENT.equals(uri.getPathSegments().get(0));
    }

    public static String toAssetPath(Uri uri) {
        return uri.toString().substring(ASSET_PREFIX_LENGTH);
    }
}
