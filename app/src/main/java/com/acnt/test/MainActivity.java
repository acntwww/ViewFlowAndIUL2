package com.acnt.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.*;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import org.taptwo.android.widget.CircleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private ItemDetailViewFlowAdapter mViewFlowAdapter;
    private List<String> mImageUrls = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImageLoader(this);
        setContentView(R.layout.activity_main);
        mImageUrls.add("http://img.xxjcy.com/nimg/a9/32/ec956c0a04254920fe0c74663c8c-600x400-1/tbr_tire_11r24_5.jpg");
        mImageUrls.add("http://farm3.staticflickr.com/2561/13031348943_611e81a48b_b.jpg");
        mImageUrls.add("http://img2.pconline.com.cn/pconline/0704/03/992498_070404cool11.jpg");

        initViews();
    }

    private void initViews() {
        mViewFlow = (ViewFlow) findViewById(R.id.vf_item_detail);
        mFlowIndicator = (CircleFlowIndicator) findViewById(R.id.indicator_home_banner);
        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlowAdapter = new ItemDetailViewFlowAdapter(this, mImageUrls);
        mViewFlow.setAdapter(mViewFlowAdapter);
        mViewFlow.postDelayed(new Runnable() {
            @Override
            public void run() {
                Point screenSize = getScreenSize();
                int w = screenSize.x;
                ViewGroup.LayoutParams layoutParams = mViewFlow.getLayoutParams();
                layoutParams.height = w;
                mViewFlow.setLayoutParams(layoutParams);

            }
        }, 0);
    }


    public  Point getScreenSize( ) {
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Point point =new Point();
        Display display = wm.getDefaultDisplay();
        display.getSize(point);
        return point;
    }



    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() );

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCacheSizePercentage(50)
                .diskCacheSize(100*1024*1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)

                .memoryCache(new WeakMemoryCache())
//                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }


}
