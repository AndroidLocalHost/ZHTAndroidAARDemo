package com.zht.aar.demo;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.zht.androidlibrary.utils.sdkUtils.YYExpressEngine;

public class MainActivity extends AppCompatActivity {


    //店长2
    public static String userToken = "eyJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiJNVGc1TXpFME9UQTNNVEk9IiwidXNlcklkIjoiTVRrPSIsInVzZXJOYW1lIjoiNWJxWDZaVy9NZz09IiwiaWF0IjoxNjA0NjUxODgwLCJleHAiOjE2MDQ2NTE4MjAsIm5iZiI6MTYwNDY1MTg4MH0.TgP4GuLA2btuEQD4wNg29No1g5Gn1jxXYMMhFkmcm-c";
    //员工4
//    public static String userToken = "eyJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiJNVGc1TXpFME9UQTNNVFE9IiwidXNlcklkIjoiTWpFPSIsInVzZXJOYW1lIjoiNWJxWDVaR1lOQT09IiwiaWF0IjoxNjA0NjUxOTQxLCJleHAiOjE2MDQ2NTE4ODEsIm5iZiI6MTYwNDY1MTk0MX0.YpRzeNbVdMicNoWQXVSS4RgDWZ9ZgR7oUoBj4piYtm8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_notifications, R.id.navigation_fragment_mine)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        YYExpressEngine instance = YYExpressEngine.getInstance();
        //设置token
        instance.setBaseToken(userToken);

        TUIKitConfigs configs = TUIKit.getConfigs();


    }

}