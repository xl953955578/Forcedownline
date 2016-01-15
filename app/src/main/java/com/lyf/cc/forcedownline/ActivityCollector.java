package com.lyf.cc.forcedownline;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类描述的是：管理Activity退出
 * 作者：肖雷
 * 时间：2016/1/14 11:30
 * 公司：上海家乐宝真好电子商务有限公司
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    /**
     * 把活动加入list
     * @param activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * 把活动从list当中移除
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * 结束所有活动
     */
    public static void finishAllActivity(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
