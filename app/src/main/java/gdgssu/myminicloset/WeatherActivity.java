package gdgssu.myminicloset;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * Created by JinGyu on 2015-01-31.
 */
public class WeatherActivity extends FragmentActivity {

    String weather="";
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        weather = ForecastFragment.weather;
        iv = (ImageView)findViewById(R.id.imageView);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }

        if(weather.equals("Snow")){
            iv.setImageResource(R.drawable.snow_big);

        }
        else if(weather.equals("Clouds")){
            iv.setImageResource(R.drawable.cloudy_big);

        }
        else if(weather.equals("Rain")){
            iv.setImageResource(R.drawable.rainy_big);

        }
        else
            iv.setImageResource(R.drawable.clear_big);
    }



}