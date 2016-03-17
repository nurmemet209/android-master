package com.cn.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.cn.customlibrary.R;


public class ProgressDialog extends AlertDialog implements OnDismissListener {

    private Activity parent;
    private TextView progressText;
    private int textResId;

    public Activity getParent() {
        return parent;
    }

    public void setParent(Activity parent) {
        this.parent = parent;
    }

    public ProgressDialog(Context context, boolean cancelable,
                          OnCancelListener cancelListener) {
        super(context, false, cancelListener);
    }

    public ProgressDialog(Context context) {
        super(context, R.style.Theme_Progress_Dialog);
    }

    private ProgressDialog(Context context, int textResId) {
        super(context, R.style.Theme_Progress_Dialog);
        this.textResId = textResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        this.progressText = (TextView) findViewById(R.id.progress_text);
        if (textResId > 0 && progressText != null)
            progressText.setText(getContext().getString(textResId));
        setOnDismissListener(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (getParent() != null) {
                dismiss();
                getParent().onBackPressed();
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    private void setProgressText(int textResId) {
        if (textResId > 0 && progressText != null)
            progressText.setText(getContext().getString(textResId));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // if (progressText != null)
        // progressText.setText(parent.getString(R.string.please_wait));
    }

}
