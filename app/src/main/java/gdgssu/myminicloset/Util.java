package gdgssu.myminicloset;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by kay on 2015-02-12.
 */
public class Util {
    private static final String TAG = Util.class.getSimpleName();
    /**
     * 카메라의 파일이 저장되는 경로를 구하는 함수..
     * @return
     */
    public static File getFilePath(){
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
        Log.d(TAG, "getFilePath result is ( " + file + " ) ");
        return file;
    }

    /**
     * 카메라에서 출력된 영상을 crop을 하기 위해서 필요한 intent
     * @param uri
     * @return
     */
    public static Intent getCropImageIntent(Uri uri){
        Intent i = new Intent("com.android.camera.action.CROP");
        i.setDataAndType(uri, "image/*");
        i.putExtra("crop", "true");
        i.putExtra("aspectX", 1);
        i.putExtra("aspectY", 1);
        i.putExtra("outputX", 100);
        i.putExtra("outputY", 100);
        i.putExtra("return-data", true);
        return i;
    }
    /**
     * 카메라로 보내는 intent
     */
    public static Intent getCamaraIntent(File file){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        return i;
    }
    /**
     * 갤러리로 보내는 Intent
     * 그냥 보내지 않고 크롭을 하기 위해서 보내는 방법이다.
     */
    public static Intent getGalleryIntent(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT, null);
        i.setType("image/*");
        i.putExtra("crop", "true");
        i.putExtra("aspectX", 1);
        i.putExtra("aspectY", 1);
        i.putExtra("outputX", 100);
        i.putExtra("outputY", 100);
        i.putExtra("return-data", true);
        return i;
    }
    /**
     * 파일을 이름을 저장을 하는 방법을 날짜로 기록을 하는 방법이다.
     * @return
     */
    public static String getPhotoFileName(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'GDGSSU'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
