package gdgssu.myminicloset;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;


/**
 * Created by JinGyu on 2015-01-30.
 */
public class ClosetActivity extends Activity {

    ListView topList, botList;
    ImageView maneTop, maneBot;

    Bitmap[] topImages;
    Bitmap[] botImages;

    Button btnRandom, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloth_activity);

        File topImageFiles[] = getFileNames("/Android/data/gdgssu.myminicloset/topImage");
        File botImageFiles[] = getFileNames("/Android/data/gdgssu.myminicloset/botImage");

        topImages = new Bitmap[topImageFiles.length];
        botImages = new Bitmap[botImageFiles.length];

        for(int i = 0 ; i < topImageFiles.length; i++)
        {
            Log.v("ClosetActivity",""+topImageFiles[i].getAbsolutePath());
            topImages[i] = BitmapFactory.decodeFile(topImageFiles[i].getAbsolutePath());
        }

        for(int i = 0 ; i < botImageFiles.length; i++)
        {
            botImages[i] = BitmapFactory.decodeFile(botImageFiles[i].getAbsolutePath());
        }

        init();

    }

    private void init() {
        btnRandom = (Button)findViewById(R.id.btnRandom);
        btnReset = (Button)findViewById(R.id.btnReset);
        maneTop = (ImageView)findViewById(R.id.avataTop);
        maneBot = (ImageView)findViewById(R.id.avataBot);
        topList = (ListView)findViewById(R.id.listTopImage);
        botList = (ListView)findViewById(R.id.listBotImage);

        MyAdapter topAdapter = new MyAdapter(this, topImages);
        topList.setAdapter(topAdapter);
        topList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maneTop.setImageBitmap(topImages[position]);
            }
        });

        MyAdapter botAdapter = new MyAdapter(this, botImages);
        botList.setAdapter(botAdapter);
        botList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maneBot.setImageBitmap(botImages[position]);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maneTop.setImageResource(R.drawable.avatop);
                maneBot.setImageResource(R.drawable.avabot);
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int top = (int)(Math.random()*1000)%topImages.length;
                maneTop.setImageBitmap(topImages[top]);
                int bot = (int)(Math.random()*1000)%botImages.length;
                maneBot.setImageBitmap(botImages[bot]);

            }
        });

    }

    private File[] getFileNames(String dataPath) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+dataPath;
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: "+ file.length);

        return file;
    }
}
