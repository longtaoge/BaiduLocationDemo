package org.xiangbalao.app;


import android.app.Application;
import android.os.Environment;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import org.xiangbalao.service.BaiDuLocationService;

/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 *
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 * 
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends Application {
	public BaiDuLocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new BaiDuLocationService(getApplicationContext());


        initDB();
       
    }



    private void initDB() {
        String databasesPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/hawkDome.db";

        Hawk.init(getApplicationContext())
                .setStorage(
                        HawkBuilder.newSqliteStorage(getApplicationContext(),
                                databasesPath)).setCallback(new HawkBuilder.Callback() {

            @Override
            public void onSuccess() {

                Toast.makeText(getApplicationContext(), "数据库创建成功",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFail(Exception e) {

                Toast.makeText(getApplicationContext(), "数据库创建失败",
                        Toast.LENGTH_SHORT).show();

            }
        }).build();
    }
}
