package com.parse;

import com.spiraltoys.cloudpets2.model.Profile;
import org.json.JSONException;
import org.json.JSONObject;

class PointerOrLocalIdEncoder extends ParseEncoder {
    private static final PointerOrLocalIdEncoder INSTANCE = new PointerOrLocalIdEncoder();

    PointerOrLocalIdEncoder() {
    }

    public static PointerOrLocalIdEncoder get() {
        return INSTANCE;
    }

    public JSONObject encodeRelatedObject(ParseObject object) {
        JSONObject json = new JSONObject();
        try {
            if (object.getObjectId() != null) {
                json.put("__type", "Pointer");
                json.put("className", object.getClassName());
                json.put(Profile.OBJECT_ID, object.getObjectId());
            } else {
                json.put("__type", "Pointer");
                json.put("className", object.getClassName());
                json.put("localId", object.getOrCreateLocalId());
            }
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
