package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.DeleteCallback;
import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.events.FriendRecordDeletedEvent;
import com.spiraltoys.cloudpets2.events.FriendRecordSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class FriendPermissionsAdapter$MyFriendsViewHolder extends ViewHolder {
    private OnClickListener acceptInvite = new OnClickListener() {
        public void onClick(final View v) {
            final Context context = FriendPermissionsAdapter$MyFriendsViewHolder.this.mView.getContext();
            ((BaseActivity) v.getContext()).showProgress(R.string.message_accepting);
            ModelHelper.resolvePendingFriendRequest(FriendPermissionsAdapter$MyFriendsViewHolder.this.mView.getContext(), FriendPermissionsAdapter$MyFriendsViewHolder.this.mRecord, true, new FunctionCallback<FriendRecord>() {
                public void done(FriendRecord friendRecord, ParseException e) {
                    ((BaseActivity) v.getContext()).hideProgress();
                    if (e == null) {
                        EventBus.getDefault().post(new FriendRecordSavedToLocalDatastoreEvent());
                    } else {
                        Utils.showErrorDialog(context, ErrorMessages.getStringResourceIdForGenericParseExceiption(context));
                    }
                }
            });
        }
    };
    private OnClickListener declineInvite = new OnClickListener() {
        public void onClick(final View v) {
            final Context context = FriendPermissionsAdapter$MyFriendsViewHolder.this.mView.getContext();
            new Builder(context).setMessage(context.getString(R.string.message_reject_friend_request)).setPositiveButton(context.getString(R.string.btn_yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((BaseActivity) v.getContext()).showProgress(R.string.message_rejecting);
                    ModelHelper.resolvePendingFriendRequest(FriendPermissionsAdapter$MyFriendsViewHolder.this.mView.getContext(), FriendPermissionsAdapter$MyFriendsViewHolder.this.mRecord, false, new FunctionCallback<FriendRecord>() {
                        public void done(FriendRecord friendRecord, ParseException e) {
                            ((BaseActivity) v.getContext()).hideProgress();
                            if (e == null) {
                                EventBus.getDefault().post(new FriendRecordDeletedEvent());
                            } else {
                                Utils.showErrorDialog(context, ErrorMessages.getStringResourceIdForGenericParseExceiption(context));
                            }
                        }
                    });
                }
            }).setNegativeButton(context.getString(R.string.btn_no), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    };
    private OnClickListener deleteFriend = new OnClickListener() {
        public void onClick(final View v) {
            final Context context = FriendPermissionsAdapter$MyFriendsViewHolder.this.mView.getContext();
            Builder builder = new Builder(context);
            builder.setMessage(context.getString(R.string.message_delete_friend));
            builder.setPositiveButton(context.getString(R.string.btn_yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((BaseActivity) v.getContext()).showProgress(R.string.message_removing);
                    FriendPermissionsAdapter$MyFriendsViewHolder.this.mRecord.deleteInBackground(new DeleteCallback() {
                        public void done(ParseException e) {
                            ((BaseActivity) v.getContext()).hideProgress();
                            if (e == null) {
                                EventBus.getDefault().post(new FriendRecordDeletedEvent());
                            } else {
                                Utils.showErrorDialog(context, ErrorMessages.getStringResourceIdForGenericParseExceiption(context));
                            }
                        }
                    });
                }
            });
            builder.setNegativeButton(context.getString(R.string.btn_no), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    };
    ImageView mAcceptButton;
    ImageView mDeclineButton;
    ImageView mProfilePhotoView;
    FriendRecord mRecord;
    TextView mRequestPendingText;
    TextView mTextField1;
    TextView mTextField2;
    View mView;
    final /* synthetic */ FriendPermissionsAdapter this$0;

    public FriendPermissionsAdapter$MyFriendsViewHolder(FriendPermissionsAdapter this$0, View view) {
        this.this$0 = this$0;
        super(view);
        this.mView = view;
        this.mTextField1 = (TextView) this.mView.findViewById(R.id.list_item_friend_text_1);
        this.mTextField2 = (TextView) this.mView.findViewById(R.id.list_item_friend_text_2);
        this.mProfilePhotoView = (ImageView) this.mView.findViewById(R.id.profile_photo);
        this.mAcceptButton = (ImageView) this.mView.findViewById(R.id.confirm_icon);
        this.mDeclineButton = (ImageView) this.mView.findViewById(R.id.deny_icon);
        this.mRequestPendingText = (TextView) this.mView.findViewById(R.id.request_pending_label);
    }

    public void setParseObject(FriendRecord record) {
        String str;
        this.mRecord = record;
        Context context = this.mView.getContext();
        this.mRequestPendingText.setVisibility(8);
        if (this.mRecord.getStatus().equals(FriendStatus.PENDING)) {
            this.mTextField1.setTextColor(ContextCompat.getColor(context, R.color.button_bg_manage_users));
            this.mTextField1.setText(context.getString(R.string.message_new_friend_request));
            this.mTextField1.setTypeface(TypefaceUtils.load(context.getAssets(), "fonts/merge_bold.otf"));
            this.mTextField2.setText(context.getString(R.string.message_addition_wants_to_be_friends, new Object[]{record.getTargetProfile().getDisplayName()}));
            this.mAcceptButton.setOnClickListener(this.acceptInvite);
            this.mDeclineButton.setImageResource(R.drawable.x);
            this.mDeclineButton.setOnClickListener(this.declineInvite);
            this.mAcceptButton.setVisibility(0);
        } else {
            this.mTextField1.setTextColor(ContextCompat.getColor(context, R.color.text_color_black));
            this.mTextField1.setText(record.getTargetProfile().getDisplayName());
            this.mTextField1.setTypeface(TypefaceUtils.load(context.getAssets(), "fonts/merge_regular.otf"));
            this.mTextField2.setText(context.getString(R.string.account_type_child));
            this.mAcceptButton.setVisibility(8);
            this.mDeclineButton.setImageResource(R.drawable.trash);
            this.mDeclineButton.setOnClickListener(this.deleteFriend);
        }
        String displayName = this.mRecord.getTargetProfile().getDisplayName();
        String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
        ProfilePortrait profilePortrait = this.mRecord.getTargetProfile().getPortrait();
        BitmapPool pool = Glide.get(context).getBitmapPool();
        RequestManager with = Glide.with(context);
        if (profilePortrait == null) {
            str = null;
        } else {
            str = profilePortrait.getFile().getUrl();
        }
        with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, ContextCompat.getColor(context, R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mProfilePhotoView);
    }
}
