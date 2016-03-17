package erqal.tamam.autoupgrade;

import android.content.Context;
import android.widget.Toast;


import com.cn.dialog.ProgressDialog;
import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

/**
 * Created by erqal on 2015/10/22.
 */
public class Updater {


    public void check4Update(final Context mContext) {
        //Toast.makeText(mContext, "正在检查请稍等。。。", Toast.LENGTH_SHORT).show();
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.show();
        UmengUpdateAgent.update(mContext);
//        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
//            @Override
//            public void onUpdateReturned(int i, UpdateResponse updateResponse) {
//                if (updateResponse != null) {
//                    if (!updateResponse.hasUpdate) {
//                        UIHelper.showToast(mContext, R.string.now_is_new_version,
//                                Toast.LENGTH_SHORT);
//                    }
//                } else {
//                    UIHelper.showToast(mContext, R.string.check_net,
//                            Toast.LENGTH_SHORT);
//                }
//
//                progressDialog.dismiss();
//            }
//        });


    }
}
