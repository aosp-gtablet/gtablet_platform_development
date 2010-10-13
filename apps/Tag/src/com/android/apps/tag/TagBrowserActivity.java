/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.apps.tag;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * A browsing {@code Activity} that displays the saved tags in categories under tabs.
 */
public class TagBrowserActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // While we're doing development, delete the database every time we start.
        getBaseContext().getDatabasePath("Tags.db").delete();

        setContentView(R.layout.main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        Intent i = new Intent().setClass(this, TagList.class);

        Intent iSavedList = new Intent().setClass(this, TagList.class)
                .putExtra(TagList.SHOW_SAVED_ONLY, true);
        Intent iRecentList = new Intent().setClass(this, TagList.class);

        TabHost.TabSpec spec1 = tabHost.newTabSpec("1")
                .setIndicator("Saved", res.getDrawable(R.drawable.ic_menu_tag))
                .setContent(iSavedList);
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("2")
                .setIndicator("Recent", res.getDrawable(R.drawable.ic_menu_desk_clock))
                .setContent(iRecentList);
        tabHost.addTab(spec2);
    }
}
