package com.mincor.apptest.controllers;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mincor.apptest.R;
import com.mincor.apptest.application.MainApplication;
import com.mincor.apptest.controllers.base.BaseActionBarController;
import com.mincor.apptest.di.interfaces.IWeatherService;
import com.mincor.apptest.models.openweathermap.Main;
import com.mincor.apptest.models.openweathermap.Weather;
import com.mincor.apptest.models.openweathermap.WeatherData;
import com.mincor.apptest.models.openweathermap.Wind;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Created by alexander on 11.07.17.
 */

public class AuthController extends BaseActionBarController {

    @BindString(R.string.text_auth)         String text_auth;
    @BindString(R.string.error_pass_length)         String error_pass_length;
    @BindString(R.string.text_weather_result)         String text_weather_result;
    @BindString(R.string.error_get_data)              String error_get_data;

    @BindView(R.id.loginTF)         EditText email;
    @BindView(R.id.passwordTF)      EditText password;

    @Inject IWeatherService weatherService;

    private Disposable disposable;

    public AuthController(){
        MainApplication.getAppComponent().inject(this);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_auth, container, false);
    }

    @Override
    protected String getTitle() {
        return text_auth;
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        clearSubscribe();
    }

    @OnClick(R.id.sendEnterButton) void onSendEnterClickHandler(){
        // Reset errors.
        email.setError(null);
        password.setError(null);

        final String emailStr = email.getText().toString();
        final String passStr = password.getText().toString();

        boolean cancel = false;
        EditText focusView = null;

        // Check for a valid email address.
        if(!isValidEmail(emailStr) || TextUtils.isEmpty(emailStr)){
            email.setError(this.getActivity().getString(R.string.error_login));
            focusView = email;
            cancel = true;
        }else if(TextUtils.isEmpty(passStr)){       // Check for a valid password, if the user entered one.
            password.setError(this.getActivity().getString(R.string.error_pass));
            focusView = password;
            cancel = true;
        }else if(!isPasswordValid(passStr)){  // Check for a valid password, if the user entered one.
            password.setError(this.getActivity().getString(R.string.error_pass_length));
            focusView = password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            focusView.setText(null);
        } else {
            clearSubscribe();
            disposable = weatherService.findWeatherByCity().subscribe(
                    weatherData -> createAndShowWeatherData(weatherData),
                    throwable -> showSnackBack(error_get_data));
        }
    }

    private void createAndShowWeatherData(WeatherData data){
        Main main = data.getMain();
        Wind wind = data.getWind();
        Weather weather = data.getWeather().get(0);
        final String info = text_weather_result
                .replace("%city%", data.getName())
                .replace("%desc%", weather.getDescription())
                .replace("%grad%", main.getTemp().toString())
                .replace("%wind%", wind.getSpeed().toString())
                .replace("%pres%", main.getPressure().toString())
                .replace("%hum%", main.getHumidity().toString());
        showSnackBack(info);
    }

    private void showSnackBack(String info){
        Snackbar snack = Snackbar.make(this.getView(), info, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();
    }

    private boolean isValidEmail(String emailStr){
        if (TextUtils.isEmpty(emailStr)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches();
        }
    }

    private boolean isPasswordValid(String passStr){
        if (passStr.length() < 6)                return false;
        else if (!passStr.matches(".*[A-Z].*"))  return false;
        else if (!passStr.matches(".*[a-z].*"))  return false;
        else if (!passStr.matches(".*[0-9].*"))  return false;
        else return true;
    }

    @Override public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_auth, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //handle the click on the back arrow click
            case android.R.id.home:
                goBack();
                return true;
            //handle the click on menu item
            case R.id.item_menu_create:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearSubscribe(){
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }
}
