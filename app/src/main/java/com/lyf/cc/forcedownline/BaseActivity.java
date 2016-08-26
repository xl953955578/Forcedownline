package com.lyf.cc.forcedownline;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * 此类描述的是：活动的基类
 * 作者：肖雷
 * 时间：2016/1/14 11:37
 * 公司：上海家乐宝真好电子商务有限公司
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver forceOfflineReceiver;
    protected LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initAllMembersView(savedInstanceState);
        ActivityCollector.addActivity(this);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        forceOfflineReceiver = new ForceOfflineReceiver();
        IntentFilter filter = new IntentFilter("com.lyf.forceOffline");
        localBroadcastManager.registerReceiver(forceOfflineReceiver, filter);
        Log.d("info","onCreate---------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("info", "onStart---------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("info", "onResume---------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("info", "onRestart---------");

    }

    public abstract int getContentViewId();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("info", "onDestroy------------");
        localBroadcastManager.unregisterReceiver(forceOfflineReceiver);
        ActivityCollector.removeActivity(this);
        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
    }

    class ForceOfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(BaseActivity.this);
            dialogBuilder.setTitle("警告");
            dialogBuilder.setMessage("登录超时");
            dialogBuilder.setCancelable(false);
            dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAllActivity();
                    Intent intent1 = new Intent(context, LoginActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }
            });
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        }
    }

    public void sendLoginOutTime() {
        //本应用内发送广播
        Intent intent = new Intent("com.lyf.forceOffline");
        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("info", "onPause------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("info", "onStop------------");
    }
}
