package com.banana.coolweather.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.banana.coolweather.R;
import com.banana.coolweather.gson.Forecast;
import com.banana.coolweather.gson.Lifestyle;
import com.banana.coolweather.gson.Weather;
import com.banana.coolweather.util.HttpUtil;
import com.banana.coolweather.util.Utility;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherSv;

    private TextView titleCityTv;

    private TextView titleUpdateTimeTv;

    private TextView degreeTv;

    private TextView weatherInfoTv;

    private TextView aqiTv;

    private TextView pm25Tv;

    private TextView comfortTv;

    private TextView carWashTv;

    private TextView sportTv;

    private LinearLayout forecastLayout;

    private ImageView bingPicImg;

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        initViews();
        String weatherString =preferences.getString("weather",null);
        if(weatherString!=null){
            Weather weather= Utility.handlerWeatherResponce(weatherString);
            showWeatherInfo(weather);
        }else {
            String weatherId=getIntent().getStringExtra("weather_id");
            weatherSv.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
        String bingPic=preferences.getString("bing_pic",null);
        if(bingPic!=null){
            Glide.with(this).load(bingPic).into(bingPicImg);
        }else {
            loadBingPic();
        }
    }

    private void initViews(){
        weatherSv =findViewById(R.id.weather_sv);
        titleCityTv=findViewById(R.id.title_city_tv);
        titleUpdateTimeTv=findViewById(R.id.title_update_time_tv);
        degreeTv=findViewById(R.id.now_degree_tv);
        weatherInfoTv=findViewById(R.id.now_weather_info_tv);
        forecastLayout=findViewById(R.id.forecast_ll);
        aqiTv=findViewById(R.id.api_tv);
        pm25Tv=findViewById(R.id.api_pm25_tv);
        comfortTv=findViewById(R.id.suggestion_comfort_tv);
        carWashTv=findViewById(R.id.suggestion_car_wash_tv);
        sportTv=findViewById(R.id.suggestion_sport_tv);
        bingPicImg=findViewById(R.id.weather_bing_pic_iv);
        preferences=getSharedPreferences("WeatherSP",MODE_PRIVATE);
        editor=preferences.edit();
    }

    public void requestWeather(final String weatherId){
        String weatherUrl="https://free-api.heweather.net/s6/weather?location="
                +weatherId+"&key=731fb847aec94a73a483802ba0584516";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,
                                "获取天气信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseText= response.body().string();
                final Weather weather=Utility.handlerWeatherResponce(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(weather!=null&&"ok".equals(weather.status)){
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        }else {
                            Toast.makeText(WeatherActivity.this,
                                    "获取天气信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loadBingPic();
    }

    private void showWeatherInfo(Weather weather){
        String cityName=weather.basic.cityName;
        String updateTime=weather.update.updateTime.split(" ")[1];
        String degree=weather.now.temperature+"℃";
        String weatherInfo=weather.now.info;

        titleCityTv.setText(cityName);
        titleUpdateTimeTv.setText(updateTime);
        degreeTv.setText(degree);
        weatherInfoTv.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for(Forecast forecast:weather.forecastList){
            View view= LayoutInflater.from(this).inflate(
                    R.layout.forecast_item,forecastLayout,false);
            TextView dateText=view.findViewById(R.id.forecast_item_date_tv);
            TextView infoText=view.findViewById(R.id.forecast_info_date_tv);
            TextView maxText=view.findViewById(R.id.forecast_max_date_tv);
            TextView minText=view.findViewById(R.id.forecast_min_date_tv);
            dateText.setText(forecast.date);
            infoText.setText(forecast.info);
            maxText.setText(forecast.max);
            minText.setText(forecast.min);
            forecastLayout.addView(view);
        }

        if(weather.aqi!=null){
            aqiTv.setText(weather.aqi.city.aqi);
            pm25Tv.setText(weather.aqi.city.pm25);
        }else {
            aqiTv.setText("无");
            pm25Tv.setText("无");
        }

        for(Lifestyle lifestyle:weather.lifestyleList){
            if(lifestyle.type.equals("comf")){
                comfortTv.setText("舒适度:"+lifestyle.txt);

            }else if(lifestyle.type.equals("cw")){
                carWashTv.setText("洗车指数:"+lifestyle.txt);

            }else if(lifestyle.type.equals("sport")){
                sportTv.setText("运动建议:"+lifestyle.txt);
            }

        }
        weatherSv.setVisibility(View.VISIBLE);
    }

    public void loadBingPic(){
        String requestBingPic="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String bingPic=response.body().string();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }
}
