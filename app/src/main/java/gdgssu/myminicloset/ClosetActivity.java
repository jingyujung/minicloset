package gdgssu.myminicloset;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import gdgssu.myminicloset.view.HorizontalListView;

/**
 * Created by JinGyu on 2015-01-30.
 */
public class ClosetActivity extends Activity {

    private HorizontalListView listviewTop, listviewBot;
    MyAdapter topAdapter, botAdapter;
    ArrayList<Uri> imageArrayListTop= new ArrayList<Uri>();
    ArrayList<Uri> imageArrayListBot= new ArrayList<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloth_activity);

        init();

        initializeViews();

        File topImageFiles[] = getFileNames("/Android/data/gdgssu.myminicloset/topImage");
        File botImageFiles[] = getFileNames("/Android/data/gdgssu.myminicloset/botImage");

        for(int i = 0 ; i < topImageFiles.length ; i++)
        {
            imageArrayListTop.add(topImageFiles[i].toURI().);
        }
        for(int i = 0 ; i < botImageFiles.length; i++)
        {
            imageArrayListBot.add(botImageFiles[i].toURI());
        }



    }

    private File[] getFileNames(String s) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+s;
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: "+ file.length);
        return file;
    }

    private void init() {
        topAdapter = new MyAdapter(imageArrayListTop, getApplicationContext());
        botAdapter = new MyAdapter(imageArrayListBot, getApplicationContext());


    }

    private void initializeViews()
    {
        listviewTop = (HorizontalListView) findViewById(R.id.listviewTop);
        listviewBot = (HorizontalListView)findViewById(R.id.listviewBot);

        listviewTop.setAdapter(topAdapter);
        listviewBot.setAdapter(botAdapter);
    }

    class MyAdapter extends BaseAdapter
    {

        ArrayList<Uri> imageURI = new ArrayList<>();

        MyAdapter(ArrayList<Uri> imageURI, Context context) {
            this.imageURI = imageURI;
        }

        @Override
        public int getCount()
        {
            return 10;
        }

        @Override
        public Uri getItem(int position)
        {
            return imageURI.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            View view = LayoutInflater.from(ClosetActivity.this).inflate(
                    R.layout.listview_item, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.screenshot_image);
            imageView.setImageURI(getItem(position));

            return view;
        }
    }

}
