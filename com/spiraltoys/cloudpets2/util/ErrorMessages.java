package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.free.R;
import java.io.IOException;

public class ErrorMessages {
    private static final String SERVER_ERROR_MESSAGE_DUPLICATE_FRIEND_REQUEST = "That friend request has already been sent.";
    private static final String SERVER_ERROR_MESSAGE_USERNAME_TAKEN = "That user name is already taken.";

    private ErrorMessages() {
    }

    public static int getStringResourceIdForErrorSendingVoiceMessage(Context context, ParseException ex) {
        if (Utils.isNetworkConnectionAvailable(context)) {
            return R.string.error_message_not_sent;
        }
        return R.string.error_message_no_internet;
    }

    public static int getStringResourceIdForIOException(IOException ex) {
        return R.string.error_generic;
    }

    public static int getStringResourceIdForErrorFetchingVoiceMessage(Context context, Exception ex) {
        if (Utils.isNetworkConnectionAvailable(context)) {
            return R.string.error_message_could_not_be_downloaded;
        }
        return R.string.error_message_could_not_be_downloaded_no_internet;
    }

    public static int getStringResourceIdForLoginException(Context context, ParseException ex) {
        if (ex.getCode() == 101) {
            return R.string.error_invalid_login_params;
        }
        if (Utils.isNetworkConnectionAvailable(context)) {
            return R.string.error_generic;
        }
        return R.string.error_password_reset_no_internet;
    }

    public static int getStringResourceIdForSignupException(Context context, ParseException ex) {
        switch (ex.getCode()) {
            case ParseException.SCRIPT_ERROR /*141*/:
                return R.string.error_password_strength;
            case ParseException.VALIDATION_ERROR /*142*/:
                return SERVER_ERROR_MESSAGE_USERNAME_TAKEN.equals(ex.getMessage()) ? R.string.error_username_taken : R.string.error_invalid_profile_info;
            case ParseException.USERNAME_TAKEN /*202*/:
                return R.string.error_email_taken;
            default:
                if (Utils.isNetworkConnectionAvailable(context)) {
                    return R.string.error_generic;
                }
                return R.string.error_message_no_internet;
        }
    }

    public static int getStringResourceIdForCreateProfileException(Context context, ParseException ex) {
        switch (ex.getCode()) {
            case ParseException.VALIDATION_ERROR /*142*/:
                return SERVER_ERROR_MESSAGE_USERNAME_TAKEN.equals(ex.getMessage()) ? R.string.error_username_taken : R.string.error_invalid_profile_info;
            default:
                return Utils.isNetworkConnectionAvailable(context) ? R.string.error_generic : R.string.error_message_no_internet;
        }
    }

    public static int getStringResourceIdForErrorSendingVerificationEmail(Context context, ParseException ex) {
        if (Utils.isNetworkConnectionAvailable(context)) {
            return R.string.error_verification_not_sent;
        }
        return R.string.error_verification_no_internet;
    }

    public static int getStringResourceIdForErrorResettingPassword(Context context, ParseException ex) {
        if (ex.getCode() == ParseException.EMAIL_NOT_FOUND) {
            return R.string.error_password_reset_user_does_not_exist;
        }
        if (Utils.isNetworkConnectionAvailable(context)) {
            return R.string.error_password_reset_not_sent;
        }
        return R.string.error_password_reset_no_internet;
    }

    public static int getStringResourceIdForErrorSendingFriendRequest(Context context, ParseException ex) {
        if (Utils.isNetworkConnectionAvailable(context)) {
            return SERVER_ERROR_MESSAGE_DUPLICATE_FRIEND_REQUEST.equals(ex.getMessage()) ? R.string.error_send_friend_request_already_sent : R.string.error_send_friend_request_not_sent;
        } else {
            return R.string.error_send_friend_request_no_internet;
        }
    }

    public static int getStringResourceIdForGenericParseExceiption(Context context) {
        if (Utils.isNetworkConnectionAvailable(context)) {
            return R.string.error_parse_generic;
        }
        return R.string.error_parse_generic_no_internet;
    }
}
