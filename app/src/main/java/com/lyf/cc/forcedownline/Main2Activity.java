package com.lyf.cc.forcedownline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    @Bind(R.id.send)
    Button send;
    @Bind(R.id.go)
    Button go;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.send,R.id.go})
    public void send(View view) {
        if(view==send){
            sendLoginOutTime();
        }else if(view==go){
            Intent intent=getPackageManager().getLaunchIntentForPackage("com.lyf.cc.test");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
