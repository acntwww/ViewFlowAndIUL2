package com.acnt.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


public class ItemDetailViewFlowAdapter extends BaseAdapter {
    private static final String NEED_LOGIN = "1";


    private final Context mContext;
    private final LayoutInflater mInflater;
    private final DisplayImageOptions options;

    private List<String> mImageUrls;


    public ItemDetailViewFlowAdapter(Context context, List<String> imageUrls) {

        mContext = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.add_default)
                .showImageForEmptyUri(R.drawable.add_default)
                .resetViewBeforeLoading(true).cacheInMemory(true)
                .cacheOnDisk(true).build();
        mImageUrls = imageUrls;

    }

    @Override
    public int getCount() {
        return mImageUrls.size();
    }

    @Override
    public String getItem(int position) {

        return mImageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_item_detail_banner_ad, null);
            holder = new ViewHolder();
            holder.imgViewAd = (ImageView) convertView
                    .findViewById(R.id.iv_item_detail_banner_add);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();


        final String imgUrl = getItem(position);
        ImageLoader.getInstance().displayImage(imgUrl, holder.imgViewAd, options);
        return convertView;
    }

    private Bitmap clipBitmapHight(Bitmap bmp, int screenSize) {
        Bitmap drawBitmap = Bitmap.createBitmap(bmp.getWidth(), screenSize, bmp.getConfig());
        Canvas canvas = new Canvas(drawBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(bmp, 0, 0, paint);
        return drawBitmap;
    }

    private static class ViewHolder {
        ImageView imgViewAd;
    }


}
