package com.mincor.apptest.controllers.base;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mincor.apptest.R;
import com.mincor.apptest.controllers.actionbar.ActionBarProvider;

import butterknife.BindView;

/**
 * Created by alexander on 11.07.17.
 */

public abstract class BaseActionBarController extends BaseController {

    @BindView(R.id.toolbar_controller)      Toolbar toolbar_controller;
    protected Toolbar getToolbar(){
        return toolbar_controller;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        setTitle();
        setActionBar();
        this.setHasOptionsMenu(true);
    }

    protected void setHomeButtonEnable() {
        //set the back arrow in the toolbar
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected ActionBar getActionBar() {
        ActionBarProvider actionBarProvider = ((ActionBarProvider) getActivity());
        return actionBarProvider != null ? actionBarProvider.getSupportActionBar() : null;
    }

    protected void setActionBar(){
        ((ActionBarProvider)getActivity()).setSupportActionBar(getToolbar());
    }

    private void setTitle(){
        if(getToolbar() != null) getToolbar().setTitle(getTitle());
    }
    protected String getTitle() {
        return "";
    }

    protected void goBack(){
        getRouter().popController(this);
    }
}
