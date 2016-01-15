package com.lyf.cc.forcedownline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    public void send(View v) {
        sendLoginOutTime();
    }

    public void go(View v) {
        startActivity(new Intent(this, Main2Activity.class));
    }
}
