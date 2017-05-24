package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.EditFriendPermissionActivity;
import com.spiraltoys.cloudpets2.adapters.FriendPermissionsAdapter.ChildFriendRecordGroup;
import com.spiraltoys.cloudpets2.events.FriendRecordEditEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;

public class FriendPermissionsAdapter$ChildFriendsViewHolder extends ViewHolder {
    private OnClickListener deleteRequest = new OnClickListener() {
        public void onClick(View v) {
            final Context context = FriendPermissionsAdapter$ChildFriendsViewHolder.this.mView.getContext();
            new Builder(context).setMessage(context.getString(R.string.message_delete_friend_request)).setPositiveButton(context.getString(R.string.btn_yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((BaseActivity) context).showProgress(R.string.message_removing);
                    final int itemPosition = FriendPermissionsAdapter$ChildFriendsViewHolder.this.this$0.mChildFriendRecords.indexOf(FriendPermissionsAdapter$ChildFriendsViewHolder.this.mRecordGroup) + 1;
                    FriendPermissionsAdapter$ChildFriendsViewHolder.this.this$0.mChildFriendRecords.remove(FriendPermissionsAdapter$ChildFriendsViewHolder.this.mRecordGroup);
                    ParseObject.deleteAllInBackground(new ArrayList(FriendPermissionsAdapter$ChildFriendsViewHolder.this.mRecordGroup), new DeleteCallback() {
                        public void done(ParseException e) {
                            ((BaseActivity) context).hideProgress();
                            if (e != null) {
                                Utils.showErrorDialog(context, ErrorMessages.getStringResourceIdForGenericParseExceiption(context));
                            } else {
                                FriendPermissionsAdapter$ChildFriendsViewHolder.this.this$0.notifyItemRemoved(itemPosition);
                            }
                        }
                    });
                }
            }).setNegativeButton(context.getString(R.string.btn_no), null).show();
        }
    };
    ImageView mAcceptButton;
    ImageView mDeclineButton;
    ImageView mProfilePhotoView;
    ChildFriendRecordGroup mRecordGroup;
    TextView mRequestPendingText;
    TextView mTextField1;
    TextView mTextField2;
    View mView;
    final /* synthetic */ FriendPermissionsAdapter this$0;

    public FriendPermissionsAdapter$ChildFriendsViewHolder(FriendPermissionsAdapter this$0, View view) {
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

    public void setParseObject(ChildFriendRecordGroup recordGroup) {
        CharSequence targetEmailAddress;
        this.mRecordGroup = recordGroup;
        final Context context = this.mView.getContext();
        String displayName = "";
        boolean isPending = true;
        Iterator it = this.mRecordGroup.iterator();
        while (it.hasNext()) {
            if (!((FriendRecord) it.next()).getStatus().equals(FriendStatus.PENDING)) {
                isPending = false;
                break;
            }
        }
        if (this.mRecordGroup.first().getTargetProfile() == null) {
            this.mTextField1.setText(this.mRecordGroup.first().getRelationship().getLocalizedString(context));
        } else {
            displayName = this.mRecordGroup.first().getTargetProfile().getDisplayName();
            this.mTextField1.setText(context.getString(R.string.label_friend_name_with_relation, new Object[]{displayName, this.mRecordGroup.first().getRelationship().getLocalizedString(context)}));
        }
        TextView textView = this.mTextField2;
        if (isPending) {
            targetEmailAddress = this.mRecordGroup.first().getTargetEmailAddress();
        } else {
            targetEmailAddress = context.getString(R.string.account_type_adult);
        }
        textView.setText(targetEmailAddress);
        this.mAcceptButton.setVisibility(8);
        if (isPending) {
            this.mTextField1.setEnabled(false);
            this.mTextField2.setEnabled(false);
            BitmapPool pool = Glide.get(context).getBitmapPool();
            Glide.with(context).load(Integer.valueOf(R.drawable.request_pending)).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, ContextCompat.getColor(context, R.color.profile_photo_border_dark), 0)}).crossFade().into(this.mProfilePhotoView);
            this.mDeclineButton.setOnClickListener(this.deleteRequest);
            this.mView.setOnClickListener(null);
            this.mDeclineButton.setVisibility(0);
            this.mDeclineButton.setImageResource(R.drawable.gray_x);
            this.mRequestPendingText.setVisibility(0);
            return;
        }
        String str;
        this.mTextField1.setEnabled(true);
        this.mTextField2.setEnabled(true);
        this.mDeclineButton.setVisibility(8);
        this.mRequestPendingText.setVisibility(8);
        this.mView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new FriendRecordEditEvent(FriendPermissionsAdapter$ChildFriendsViewHolder.this.mRecordGroup));
                context.startActivity(new Intent(context, EditFriendPermissionActivity.class));
            }
        });
        String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
        ProfilePortrait profilePortrait = this.mRecordGroup.first().getTargetProfile().getPortrait();
        pool = Glide.get(context).getBitmapPool();
        RequestManager with = Glide.with(context);
        if (profilePortrait == null) {
            str = null;
        } else {
            str = profilePortrait.getFile().getUrl();
        }
        with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, ContextCompat.getColor(context, R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mProfilePhotoView);
    }
}
