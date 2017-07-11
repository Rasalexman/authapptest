package com.mincor.apptest.controllers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.mincor.apptest.R;
import com.mincor.apptest.controllers.base.BaseController;

import butterknife.OnClick;

/**
 * Created by alexander on 11.07.17.
 */

public class StartController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_start, container, false);
    }

    @OnClick(R.id.goToAuthButton) void onAuthButtonClickHandler(){
        this.getRouter().pushController(RouterTransaction.with(new AuthController())
                .pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
    }
}
