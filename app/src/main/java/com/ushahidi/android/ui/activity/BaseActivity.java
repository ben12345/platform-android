/*
 * Copyright (c) 2014 Ushahidi.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program in the file LICENSE-AGPL. If not, see
 * https://www.gnu.org/licenses/agpl-3.0.html
 */

package com.ushahidi.android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Adds shared functionality that exists between all extended Activities
 *
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class BaseActivity extends Activity {

    /**
     * Layout resource id
     */
    protected final int mLayout;

    /**
     * Menu resource id
     */
    protected final int mMenu;


    protected BaseActivity(int layout, int menu) {
        mLayout = layout;
        mMenu = menu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mLayout != 0) {
            setContentView(mLayout);
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

    }

    protected void setActionBarTitle(String title) {
        getActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mMenu != 0) {
            getMenuInflater().inflate(mMenu, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    protected android.view.View fadeIn(final android.view.View view, final boolean animate) {
        if (view != null) {
            if (animate) {
                view.startAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.fade_in));

            } else {

                view.clearAnimation();
            }
        }

        return view;

    }

    protected android.view.View fadeOut(final android.view.View view, final boolean animate) {
        if (view != null) {
            if (animate) {
                view.startAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.fade_out));
            } else {
                view.clearAnimation();
            }
        }
        return view;

    }

    protected <V extends android.view.View> V setViewGone(final V view) {
        return setViewGone(view, true);
    }

    protected <V extends android.view.View> V setViewGone(final V view, final boolean gone) {
        if (view != null) {
            if (gone) {
                if (GONE != view.getVisibility()) {

                    fadeOut(view, true);

                    view.setVisibility(GONE);
                }
            } else {
                if (VISIBLE != view.getVisibility()) {
                    view.setVisibility(VISIBLE);

                    fadeIn(view, true);

                }
            }
        }
        return view;
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message A message resource
     */
    protected void showToast(int message) {
        Toast.makeText(this, getText(message), Toast.LENGTH_LONG)
                .show();
    }
}