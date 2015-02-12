package gdgssu.myminicloset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JinGyu on 2015-01-22.
 */
public class SplashActivity extends Activity {
    private long splashDelay = 1500;

    public static WeatherActivity weatherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);



        // Remove notification bar
        /*
         * this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         * WindowManager.LayoutParams.FLAG_FULLSCREEN);
         */

        setContentView(R.layout.splashpage);

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                startActivity(new Intent().setClass(SplashActivity.this,
                        MainActivity.class));
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, splashDelay);

    }

}