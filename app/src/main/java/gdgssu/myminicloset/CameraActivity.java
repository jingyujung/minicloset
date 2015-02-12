package gdgssu.myminicloset;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by kay on 2015-02-12.
 */
public class CameraActivity extends Activity {
    private static final String TAG = CameraActivity.class.getSimpleName();
    private Button mBtnCamera, mBtnGallery;
    private ImageView mImg;
    private File mFile;
    private File mPhotoFile;
    private File CropFile;
    // start activity request code
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;
    private static final int REQUEST_CROP_DATA = 3;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        Util Util = new Util();
        mFile = Util.getFilePath();
        Log.d(TAG, "getFilePath = [ " + mFile + " ] ");
        findViews();
    }
    private void findViews(){
        mBtnCamera = (Button) findViewById(R.id.btn_click_camera);
        mBtnGallery = (Button) findViewById(R.id.btn_click_gallery);
        mImg = (ImageView) findViewById(R.id.image);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                switch(v.getId()){
                    case R.id.btn_click_camera:
                        startActivityCamera();
                        break;
                    case R.id.btn_click_gallery:
                        startActivityGallery();
                        break;
                }
            }
        };
        mBtnCamera.setOnClickListener(listener);
        mBtnGallery.setOnClickListener(listener);
    }
    private void startActivityCamera(){
        mFile.mkdirs();
        mPhotoFile = new File(mFile, Util.getPhotoFileName());
        final Intent i = Util.getCamaraIntent(mPhotoFile);
        startActivityForResult(i, REQUEST_CAMERA);
    }
    private void startActivityGallery(){
        final Intent i = Util.getGalleryIntent();
        startActivityForResult(i, REQUEST_GALLERY);
    }
    private void startActivityCrop(File file){
        MediaScannerConnection.scanFile(this,
                new String[]{file.getAbsolutePath()},
                new String[]{null},
                null);
        final Intent i = Util.getCropImageIntent(Uri.fromFile(file));
        startActivityForResult(i, REQUEST_CROP_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
//	super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQUEST_CAMERA:
                Log.v(TAG, "REQUEST_CAMERA........................");
                startActivityCrop(mPhotoFile);
                break;
            case REQUEST_GALLERY:
                Log.v(TAG, "REQUEST_GALLERY.......................");
                break;
            case REQUEST_CROP_DATA:
                Log.v(TAG, "REQUEST_CROP_DATA......................");
                Bitmap bitmap = data.getParcelableExtra("data");
                getImageViewBitmap(bitmap);

        }
    }
    private void getImageViewBitmap(Bitmap bitmap){
        final int size = bitmap.getHeight() * bitmap.getWidth() * 4;
        Log.d(TAG, "bitmap size ( " + size + " ) ");
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream(size);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        try {
            outStream.flush();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        mImg.setImageBitmap(bitmap);
    }


}
