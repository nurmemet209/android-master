package com.cn.commans;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.cn.pppcar.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nurmemet on 2016/3/28.
 */
public class ImageHandler {
    /**
     * 相机拍照保存
     */
    private Uri capturedImageUri;

    public void openCamera(Activity activity,int code){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        capturedImageUri=getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
        activity.startActivityForResult(intent, code);
        return ;
    }
    public Uri getCapturedImageUri(){
        return capturedImageUri;
    }

    public void openGalary(Activity activity,int code){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity.startActivityForResult(Intent.createChooser(intent, activity.getString(R.string.select_head_portrait)), code);
    }

    public void startCropActivity(@NonNull Uri uri,Activity activity) {

        String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), "crop%d%s");
        Uri mDestinationUri = Uri.fromFile(new File(activity.getCacheDir(), filename));
        UCrop uCrop = UCrop.of(uri, mDestinationUri);
        uCrop.start(activity);
    }


    public void handleCropResult(@NonNull Intent result,Activity activity) {

    }

    public  void startWithUri(@NonNull Context context, @NonNull Uri uri, Class zz) {
        Intent intent = new Intent(context, zz);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public void handleCropError(@NonNull Intent result,Activity activity) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Toast.makeText(activity, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, R.string.toast_unexpected_error, Toast.LENGTH_SHORT).show();
        }
    }

    /** Create a File for saving an image or video */
    private  Uri getOutputMediaFileUri(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.
        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");


        return Uri.fromFile(mediaFile);
    }


    /**
     * 图像缩略图，不是图像本身
     * @param activity
     * @param code
     */
    public void takePhoto(Activity activity,int code) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, Constants.REQUEST_OPEN_CAMERA_FOR_BUSINESS_LICENSE);
    }


    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
    {
        // BEST QUALITY MATCH
        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        int inSampleSize = 1;
        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;
        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }
        options.inSampleSize = inSampleSize;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public Uri compress(Activity activity,Bitmap bmp){
        String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), "crop%d%s");
        Uri mDestinationUri = Uri.fromFile(new File(activity.getCacheDir(), filename));
        OutputStream outputStream= null;
        try {
            outputStream = activity.getContentResolver().openOutputStream(mDestinationUri);
            bmp.compress(Bitmap.CompressFormat.JPEG,80,outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mDestinationUri;


    }



}
