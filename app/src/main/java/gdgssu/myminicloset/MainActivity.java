package gdgssu.myminicloset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;


public class MainActivity extends Activity {


    File pathTop, pathBot, pathTopInfo, pathBotInfo;
    File pathRoot;

    Button btnCloset, btnWeather;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mkdir();
        init();




    }

    private void mkdir() {
        pathRoot = Environment.getExternalStorageDirectory();
        pathTop = new File(pathRoot.getAbsolutePath() +"/Android/data/gdgssu.myminicloset/topImage");
        pathBot = new File(pathRoot.getAbsolutePath() +"/Android/data/gdgssu.myminicloset/botImage");
        pathTopInfo = new File(pathRoot.getAbsolutePath()+"/Android/data/gdgssu.myminicloset/topInfo");
        pathBotInfo = new File(pathRoot.getAbsolutePath()+"/Android/data/gdgssu.myminicloset/botInfo");

        if(!pathTop.exists())
        {
            pathTop.mkdirs();
        }
        if(!pathBot.exists())
        {
            pathBot.mkdirs();
        }
        if(!pathTopInfo.exists())
        {
            pathTopInfo.mkdirs();
        }
        if(!pathBotInfo.exists())
        {
            pathBotInfo.mkdirs();
        }
    }

    private void init() {
        btnCloset = (Button)findViewById(R.id.btnCloth);
        btnWeather = (Button)findViewById(R.id.btnWeather);


        btnCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ClosetActivity.class));
            }
        });

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
