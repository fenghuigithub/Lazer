package com.example.jsonrss_test_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by fenghui on 4/3/16.
 */
public class Logout extends ActionBarActivity {
    private static final String STATE_SELECTED_FRAGMENT_INDEX = "selected_fragment_index";
    public static final String FRAGMENT_TAG = "fragment_tag";
    private FragmentManager mFragmentManager;
    public static Logout lo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        lo=this;
        setContentView(R.layout.logout);
        mFragmentManager = getSupportFragmentManager();
        toggleFragment();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void toggleFragment() {
        Fragment fragment = mFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        switch (index){
//            case INDEX_SIMPLE_LOGIN:
//                transaction.replace(android.R.id.content, new FragmentSimpleLoginButton(),FRAGMENT_TAG);
//                break;
//            case INDEX_CUSTOM_LOGIN:
//                transaction.replace(android.R.id.content, new FragmentCustomLoginButton(),FRAGMENT_TAG);
//                break;
//        }
        transaction.replace(android.R.id.content, new FragmentSimpleLogoutButton(),FRAGMENT_TAG);
        transaction.commit();
    }


}
