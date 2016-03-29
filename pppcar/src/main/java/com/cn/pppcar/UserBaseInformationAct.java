package com.cn.pppcar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cn.commans.Constants;
import com.cn.commans.ImageHandler;
import com.cn.dialog.PictureSelecctDialog;
import com.cn.util.UIHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yalantis.ucrop.UCrop;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/3/26.
 */
public class UserBaseInformationAct extends Activity{

    private Handler mHandler=new Handler();

    ImageHandler imageHandler;

    @Bind(R.id.head_portrait)
    protected SimpleDraweeView headPortrait;

    @Bind(R.id.business_license)
    protected SimpleDraweeView licenseImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_base_informaton_act);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
       imageHandler =new ImageHandler();

    }


    /**
     * xml 配置
     * @param view
     */
    public void uploadHeadPortrait(View view){
        PictureSelecctDialog pictureSelecctDialog=new PictureSelecctDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tag=(Integer)v.getTag();
                switch (tag){
                    case PictureSelecctDialog.FROM_ALBUM:
                        imageHandler.openGalary(UserBaseInformationAct.this,Constants.REQUEST_OPEN_GALARY_FOR_HEAD_PORTRAIT);
                        break;
                    case PictureSelecctDialog.TAKE_PICTURE:
                        imageHandler.openCamera(UserBaseInformationAct.this,Constants.REQUEST_OPEN_CAMERA_FOR_HEAD_PORTRAIT);
                        break;
                    default:
                        break;
                }
            }
        });
        pictureSelecctDialog.show();
    }

    /**
     * xml配置
     * @param view
     */
    public void modifyNickName(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyPhoneNumber(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyTelNumber(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyEmail(View view){

    }
    /**
     * xml配置
     * @param view
     */
    public void uploadLicenseImage(View view){
        PictureSelecctDialog pictureSelecctDialog=new PictureSelecctDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tag=(Integer)v.getTag();
                switch (tag){
                    case PictureSelecctDialog.FROM_ALBUM:
                        imageHandler.openGalary(UserBaseInformationAct.this,Constants.REQUEST_OPEN_GALARY_FOR_BUSINESS_LICENSE);
                        break;
                    case PictureSelecctDialog.TAKE_PICTURE:
                        imageHandler.openCamera(UserBaseInformationAct.this,Constants.REQUEST_OPEN_CAMERA_FOR_BUSINESS_LICENSE);
                        break;
                    default:
                        break;
                }
            }
        });
        pictureSelecctDialog.show();
    }
    /**
     * xml配置
     * @param view
     */
    public void uploadShopImage(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyLegelPerson(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyCompanyName(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyLicenseRegistrationCode(View view){

    }

    /**
     * xml配置
     * @param view
     */
    public void modifyContactPerson(View view){
    }
    /**
     * xml配置
     * @param view
     */
    public void modifyCompanyAddress(View view){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUEST_OPEN_GALARY_FOR_HEAD_PORTRAIT) {
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    imageHandler.startCropActivity(data.getData(),UserBaseInformationAct.this);
                } else {
                    Toast.makeText(this, R.string.toast_cannot_retrieve_selected_image, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                handleCropResult(data);
            }
            else if(requestCode==Constants.REQUEST_OPEN_CAMERA_FOR_HEAD_PORTRAIT){
                Uri uri=imageHandler.getCapturedImageUri();
                imageHandler.startCropActivity(uri,UserBaseInformationAct.this);
            }
            else if (requestCode==Constants.REQUEST_OPEN_GALARY_FOR_BUSINESS_LICENSE){
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                  licenseImg.setImageURI(selectedUri);
                } else {
                    Toast.makeText(this, "营业执照片片Uri为空", Toast.LENGTH_SHORT).show();
                }
            }
            else if(requestCode==Constants.REQUEST_OPEN_CAMERA_FOR_BUSINESS_LICENSE){
                Uri uri=imageHandler.getCapturedImageUri();
                Bitmap bmp=imageHandler.decodeSampledBitmapFromFile(uri.getPath(),600,800);
                uri=imageHandler.compress(UserBaseInformationAct.this,bmp);
                licenseImg.setImageURI(uri);

            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            imageHandler.handleCropError(data,UserBaseInformationAct.this);
        }
    }

    private void handleCropResult(Intent data){
        final Uri resultUri = UCrop.getOutput(data);
        if (resultUri != null) {
            headPortrait.setImageURI(resultUri);
        } else {
            Toast.makeText(this, R.string.toast_cannot_retrieve_cropped_image, Toast.LENGTH_SHORT).show();
        }
    }








}
