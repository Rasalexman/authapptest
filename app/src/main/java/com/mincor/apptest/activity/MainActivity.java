package com.mincor.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.mincor.apptest.R;
import com.mincor.apptest.controllers.StartController;
import com.mincor.apptest.controllers.actionbar.ActionBarProvider;

public class MainActivity extends AppCompatActivity implements ActionBarProvider {

    private Router mainRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define app router for conductor
        ViewGroup container = (ViewGroup) findViewById(R.id.controller_container);
        mainRouter = Conductor.attachRouter(this, container, savedInstanceState);
        mainRouter.setRoot(RouterTransaction.with(new StartController()));
    }
}
