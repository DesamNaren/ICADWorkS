package cgg.gov.in.icadworks.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import cgg.gov.in.icadworks.R;


public class CustomProgressDialog {
    private static CustomProgressDialog mCShowProgress;
    private Dialog mDialog;

    public static CustomProgressDialog getInstance() {
        if (mCShowProgress == null) {
            mCShowProgress = new CustomProgressDialog();
        }
        return mCShowProgress;
    }

    public void showProgress(final Context mContext) {
        try {
            mDialog = new Dialog(mContext);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.app_progress_dialog);
            mDialog.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(false);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hideProgress() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
