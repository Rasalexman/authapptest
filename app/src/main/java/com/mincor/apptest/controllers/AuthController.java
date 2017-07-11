package com.mincor.apptest.controllers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mincor.apptest.R;
import com.mincor.apptest.controllers.base.BaseActionBarController;

import butterknife.BindString;

/**
 * Created by alexander on 11.07.17.
 */

public class AuthController extends BaseActionBarController {

    @BindString(R.string.text_auth)         String text_auth;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_auth, container, false);
    }

    @Override
    protected String getTitle() {
        return text_auth;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        setHomeButtonEnable();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
