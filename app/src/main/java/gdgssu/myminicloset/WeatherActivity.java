package gdgssu.myminicloset;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by JinGyu on 2015-01-31.
 */
public class WeatherActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }


    }



}
