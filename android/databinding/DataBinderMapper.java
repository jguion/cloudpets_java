package android.databinding;

import android.view.View;
import com.google.android.gms.common.Scopes;
import com.spiraltoys.cloudpets2.databinding.ActivityAdultDashboardBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityAdultVoiceMessageBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityBarnyardSoundsGameBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityChildDashboardBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityCreateAccountBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityEditProfileBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityInviteFriendBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityLogInBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityManageProfileBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityMessageCenterBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityPlayStoryBinding;
import com.spiraltoys.cloudpets2.databinding.ActivityUnityPlayerBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentAudioPlayerBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardDialogBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardHelpBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardHelpListenBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardHelpSendBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardLullabiesBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardLullabyDetailsBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardSelectProfileBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardStoriesBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardStoryDetailsBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildRecordedMessageActionsBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildVoiceMessageDetailsBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentChildVoiceMessagesBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentConnectionIndicatorBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentCreateChildProfileBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentDialogLockScreenBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentFloatingProfileSwitcherBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentFriendPermissionsBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentMessageListBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentOverlayVisitingCloudpetBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentPremiumDialogBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentProfilesListBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentRecordVoiceMessageBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentRecordedVoiceMessagePlaybackBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentRequestBlePermissionBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentRequestBlePermissionWithOnboardingToolbarBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentSelectBluetoothToyErrorBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentSelectProfileBinding;
import com.spiraltoys.cloudpets2.databinding.FragmentVoiceMessagePlayerBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemChildVoiceMessageBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemHeaderBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemLullabyBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemProfileSwitcherBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemSelectableProfileBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemStoryBinding;
import com.spiraltoys.cloudpets2.databinding.ListItemVoiceMessageBinding;
import com.spiraltoys.cloudpets2.databinding.ViewConnectionIndicatorBinding;
import com.spiraltoys.cloudpets2.databinding.ViewRippleBinding;
import com.spiraltoys.cloudpets2.free.R;

class DataBinderMapper {
    static final int TARGET_MIN_SDK = 19;

    private static class InnerBrLookup {
        static String[] sKeys = new String[]{"_all", "adultRecordingNewMessage", "isParentalControlEnabled", "lullaby", Scopes.PROFILE, "story", "voiceMessage", "voiceMessageDateFormatter"};

        private InnerBrLookup() {
        }
    }

    public ViewDataBinding getDataBinder(View view, int layoutId) {
        switch (layoutId) {
            case R.layout.activity_adult_dashboard:
                return ActivityAdultDashboardBinding.bind(view);
            case R.layout.activity_adult_voice_message:
                return ActivityAdultVoiceMessageBinding.bind(view);
            case R.layout.activity_barnyard_sounds_game:
                return ActivityBarnyardSoundsGameBinding.bind(view);
            case R.layout.activity_child_dashboard:
                return ActivityChildDashboardBinding.bind(view);
            case R.layout.activity_create_account:
                return ActivityCreateAccountBinding.bind(view);
            case R.layout.activity_edit_profile:
                return ActivityEditProfileBinding.bind(view);
            case R.layout.activity_invite_friend:
                return ActivityInviteFriendBinding.bind(view);
            case R.layout.activity_log_in:
                return ActivityLogInBinding.bind(view);
            case R.layout.activity_manage_profile:
                return ActivityManageProfileBinding.bind(view);
            case R.layout.activity_message_center:
                return ActivityMessageCenterBinding.bind(view);
            case R.layout.activity_play_story:
                return ActivityPlayStoryBinding.bind(view);
            case R.layout.activity_unity_player:
                return ActivityUnityPlayerBinding.bind(view);
            case R.layout.fragment_audio_player:
                return FragmentAudioPlayerBinding.bind(view);
            case R.layout.fragment_child_dashboard_dialog:
                return FragmentChildDashboardDialogBinding.bind(view);
            case R.layout.fragment_child_dashboard_help:
                return FragmentChildDashboardHelpBinding.bind(view);
            case R.layout.fragment_child_dashboard_help_listen:
                return FragmentChildDashboardHelpListenBinding.bind(view);
            case R.layout.fragment_child_dashboard_help_send:
                return FragmentChildDashboardHelpSendBinding.bind(view);
            case R.layout.fragment_child_dashboard_lullabies:
                return FragmentChildDashboardLullabiesBinding.bind(view);
            case R.layout.fragment_child_dashboard_lullaby_details:
                return FragmentChildDashboardLullabyDetailsBinding.bind(view);
            case R.layout.fragment_child_dashboard_select_profile:
                return FragmentChildDashboardSelectProfileBinding.bind(view);
            case R.layout.fragment_child_dashboard_stories:
                return FragmentChildDashboardStoriesBinding.bind(view);
            case R.layout.fragment_child_dashboard_story_details:
                return FragmentChildDashboardStoryDetailsBinding.bind(view);
            case R.layout.fragment_child_recorded_message_actions:
                return FragmentChildRecordedMessageActionsBinding.bind(view);
            case R.layout.fragment_child_voice_message_details:
                return FragmentChildVoiceMessageDetailsBinding.bind(view);
            case R.layout.fragment_child_voice_messages:
                return FragmentChildVoiceMessagesBinding.bind(view);
            case R.layout.fragment_connection_indicator:
                return FragmentConnectionIndicatorBinding.bind(view);
            case R.layout.fragment_create_child_profile:
                return FragmentCreateChildProfileBinding.bind(view);
            case R.layout.fragment_dialog_lock_screen:
                return FragmentDialogLockScreenBinding.bind(view);
            case R.layout.fragment_floating_profile_switcher:
                return FragmentFloatingProfileSwitcherBinding.bind(view);
            case R.layout.fragment_friend_permissions:
                return FragmentFriendPermissionsBinding.bind(view);
            case R.layout.fragment_message_list:
                return FragmentMessageListBinding.bind(view);
            case R.layout.fragment_overlay_visiting_cloudpet:
                return FragmentOverlayVisitingCloudpetBinding.bind(view);
            case R.layout.fragment_premium_dialog:
                return FragmentPremiumDialogBinding.bind(view);
            case R.layout.fragment_profiles_list:
                return FragmentProfilesListBinding.bind(view);
            case R.layout.fragment_record_voice_message:
                return FragmentRecordVoiceMessageBinding.bind(view);
            case R.layout.fragment_recorded_voice_message_playback:
                return FragmentRecordedVoiceMessagePlaybackBinding.bind(view);
            case R.layout.fragment_request_ble_permission:
                return FragmentRequestBlePermissionBinding.bind(view);
            case R.layout.fragment_request_ble_permission_with_onboarding_toolbar:
                return FragmentRequestBlePermissionWithOnboardingToolbarBinding.bind(view);
            case R.layout.fragment_select_bluetooth_toy_error:
                return FragmentSelectBluetoothToyErrorBinding.bind(view);
            case R.layout.fragment_select_profile:
                return FragmentSelectProfileBinding.bind(view);
            case R.layout.fragment_voice_message_player:
                return FragmentVoiceMessagePlayerBinding.bind(view);
            case R.layout.list_item_child_voice_message:
                return ListItemChildVoiceMessageBinding.bind(view);
            case R.layout.list_item_header:
                return ListItemHeaderBinding.bind(view);
            case R.layout.list_item_lullaby:
                return ListItemLullabyBinding.bind(view);
            case R.layout.list_item_profile_switcher:
                return ListItemProfileSwitcherBinding.bind(view);
            case R.layout.list_item_selectable_profile:
                return ListItemSelectableProfileBinding.bind(view);
            case R.layout.list_item_story:
                return ListItemStoryBinding.bind(view);
            case R.layout.list_item_voice_message:
                return ListItemVoiceMessageBinding.bind(view);
            case R.layout.view_connection_indicator:
                return ViewConnectionIndicatorBinding.bind(view);
            case R.layout.view_ripple:
                return new ViewRippleBinding(new View[]{view});
            default:
                return null;
        }
    }

    ViewDataBinding getDataBinder(View[] views, int layoutId) {
        switch (layoutId) {
            case R.layout.view_ripple:
                return new ViewRippleBinding(views);
            default:
                return null;
        }
    }

    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        switch (tag.hashCode()) {
            case -2062564930:
                if (tag.equals("layout/fragment_friend_permissions_0")) {
                    return R.layout.fragment_friend_permissions;
                }
                return 0;
            case -1940030882:
                if (tag.equals("layout/fragment_create_child_profile_0")) {
                    return R.layout.fragment_create_child_profile;
                }
                return 0;
            case -1792754191:
                if (tag.equals("layout/fragment_message_list_0")) {
                    return R.layout.fragment_message_list;
                }
                return 0;
            case -1615356139:
                if (tag.equals("layout/activity_adult_dashboard_0")) {
                    return R.layout.activity_adult_dashboard;
                }
                return 0;
            case -1594430314:
                if (tag.equals("layout/list_item_story_0")) {
                    return R.layout.list_item_story;
                }
                return 0;
            case -1462145161:
                if (tag.equals("layout/activity_child_dashboard_0")) {
                    return R.layout.activity_child_dashboard;
                }
                return 0;
            case -1430334681:
                if (tag.equals("layout/activity_unity_player_0")) {
                    return R.layout.activity_unity_player;
                }
                return 0;
            case -1158109584:
                if (tag.equals("layout/activity_edit_profile_0")) {
                    return R.layout.activity_edit_profile;
                }
                return 0;
            case -1037008321:
                if (tag.equals("layout/fragment_child_dashboard_help_send_0")) {
                    return R.layout.fragment_child_dashboard_help_send;
                }
                return 0;
            case -964517018:
                if (tag.equals("layout/activity_play_story_0")) {
                    return R.layout.activity_play_story;
                }
                return 0;
            case -926608565:
                if (tag.equals("layout/fragment_premium_dialog_0")) {
                    return R.layout.fragment_premium_dialog;
                }
                return 0;
            case -853652596:
                if (tag.equals("layout/fragment_child_dashboard_stories_0")) {
                    return R.layout.fragment_child_dashboard_stories;
                }
                return 0;
            case -720743474:
                if (tag.equals("layout/list_item_header_0")) {
                    return R.layout.list_item_header;
                }
                return 0;
            case -290405328:
                if (tag.equals("layout/list_item_lullaby_0")) {
                    return R.layout.list_item_lullaby;
                }
                return 0;
            case -259983029:
                if (tag.equals("layout/activity_manage_profile_0")) {
                    return R.layout.activity_manage_profile;
                }
                return 0;
            case -210484202:
                if (tag.equals("layout/view_ripple_0")) {
                    return R.layout.view_ripple;
                }
                return 0;
            case -185105269:
                if (tag.equals("layout/fragment_child_dashboard_lullaby_details_0")) {
                    return R.layout.fragment_child_dashboard_lullaby_details;
                }
                return 0;
            case -157028546:
                if (tag.equals("layout/fragment_request_ble_permission_0")) {
                    return R.layout.fragment_request_ble_permission;
                }
                return 0;
            case -34143426:
                if (tag.equals("layout/fragment_child_dashboard_help_listen_0")) {
                    return R.layout.fragment_child_dashboard_help_listen;
                }
                return 0;
            case -20206121:
                if (tag.equals("layout/fragment_floating_profile_switcher_0")) {
                    return R.layout.fragment_floating_profile_switcher;
                }
                return 0;
            case 11465225:
                if (tag.equals("layout/fragment_connection_indicator_0")) {
                    return R.layout.fragment_connection_indicator;
                }
                return 0;
            case 13077354:
                if (tag.equals("layout/fragment_child_dashboard_help_0")) {
                    return R.layout.fragment_child_dashboard_help;
                }
                return 0;
            case 131350182:
                if (tag.equals("layout/fragment_child_dashboard_lullabies_0")) {
                    return R.layout.fragment_child_dashboard_lullabies;
                }
                return 0;
            case 238093745:
                if (tag.equals("layout/fragment_child_dashboard_dialog_0")) {
                    return R.layout.fragment_child_dashboard_dialog;
                }
                return 0;
            case 379505755:
                if (tag.equals("layout/list_item_voice_message_0")) {
                    return R.layout.list_item_voice_message;
                }
                return 0;
            case 407205601:
                if (tag.equals("layout/fragment_voice_message_player_0")) {
                    return R.layout.fragment_voice_message_player;
                }
                return 0;
            case 753320919:
                if (tag.equals("layout/fragment_child_voice_messages_0")) {
                    return R.layout.fragment_child_voice_messages;
                }
                return 0;
            case 759629688:
                if (tag.equals("layout/list_item_profile_switcher_0")) {
                    return R.layout.list_item_profile_switcher;
                }
                return 0;
            case 809580890:
                if (tag.equals("layout/activity_invite_friend_0")) {
                    return R.layout.activity_invite_friend;
                }
                return 0;
            case 907686134:
                if (tag.equals("layout/fragment_recorded_voice_message_playback_0")) {
                    return R.layout.fragment_recorded_voice_message_playback;
                }
                return 0;
            case 1138334465:
                if (tag.equals("layout/fragment_select_profile_0")) {
                    return R.layout.fragment_select_profile;
                }
                return 0;
            case 1170485716:
                if (tag.equals("layout/fragment_child_recorded_message_actions_0")) {
                    return R.layout.fragment_child_recorded_message_actions;
                }
                return 0;
            case 1196310779:
                if (tag.equals("layout/activity_adult_voice_message_0")) {
                    return R.layout.activity_adult_voice_message;
                }
                return 0;
            case 1226369852:
                if (tag.equals("layout/activity_log_in_0")) {
                    return R.layout.activity_log_in;
                }
                return 0;
            case 1247128713:
                if (tag.equals("layout/activity_message_center_0")) {
                    return R.layout.activity_message_center;
                }
                return 0;
            case 1346693711:
                if (tag.equals("layout/fragment_child_dashboard_select_profile_0")) {
                    return R.layout.fragment_child_dashboard_select_profile;
                }
                return 0;
            case 1361891220:
                if (tag.equals("layout/view_connection_indicator_0")) {
                    return R.layout.view_connection_indicator;
                }
                return 0;
            case 1386568494:
                if (tag.equals("layout/fragment_select_bluetooth_toy_error_0")) {
                    return R.layout.fragment_select_bluetooth_toy_error;
                }
                return 0;
            case 1421654840:
                if (tag.equals("layout/list_item_child_voice_message_0")) {
                    return R.layout.list_item_child_voice_message;
                }
                return 0;
            case 1447282202:
                if (tag.equals("layout/fragment_profiles_list_0")) {
                    return R.layout.fragment_profiles_list;
                }
                return 0;
            case 1520475046:
                if (tag.equals("layout/activity_create_account_0")) {
                    return R.layout.activity_create_account;
                }
                return 0;
            case 1555009569:
                if (tag.equals("layout/fragment_child_voice_message_details_0")) {
                    return R.layout.fragment_child_voice_message_details;
                }
                return 0;
            case 1622032740:
                if (tag.equals("layout/fragment_dialog_lock_screen_0")) {
                    return R.layout.fragment_dialog_lock_screen;
                }
                return 0;
            case 1742593857:
                if (tag.equals("layout/list_item_selectable_profile_0")) {
                    return R.layout.list_item_selectable_profile;
                }
                return 0;
            case 1744836143:
                if (tag.equals("layout/fragment_request_ble_permission_with_onboarding_toolbar_0")) {
                    return R.layout.fragment_request_ble_permission_with_onboarding_toolbar;
                }
                return 0;
            case 1776792170:
                if (tag.equals("layout/fragment_overlay_visiting_cloudpet_0")) {
                    return R.layout.fragment_overlay_visiting_cloudpet;
                }
                return 0;
            case 1818348785:
                if (tag.equals("layout/fragment_child_dashboard_story_details_0")) {
                    return R.layout.fragment_child_dashboard_story_details;
                }
                return 0;
            case 1858509607:
                if (tag.equals("layout/fragment_record_voice_message_0")) {
                    return R.layout.fragment_record_voice_message;
                }
                return 0;
            case 1894544005:
                if (tag.equals("layout/fragment_audio_player_0")) {
                    return R.layout.fragment_audio_player;
                }
                return 0;
            case 1932466303:
                if (tag.equals("layout/activity_barnyard_sounds_game_0")) {
                    return R.layout.activity_barnyard_sounds_game;
                }
                return 0;
            default:
                return 0;
        }
    }

    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
}
