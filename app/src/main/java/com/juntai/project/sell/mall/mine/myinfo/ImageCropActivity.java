package com.juntai.project.sell.mall.mine.myinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.juntai.disabled.basecomponent.base.BaseActivity;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.project.sell.mall.MyApp;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.utils.cropbitmap.LikeQQCropView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 图片裁切页面
 * @aouther Ma
 * @date 2019/3/27
 */
public class ImageCropActivity extends BaseActivity implements View.OnClickListener{
    private LikeQQCropView likeView;
    SeekBar sb;
    Button btHorizontalFlip;
    Button btVerticalFlip;
    Button btBoth;
    Button btReset;
    Button btClip;
    @Override
    public int getLayoutView() {
        return R.layout.activity_image_crop;
    }

    @Override
    public void initView() {
        setTitleName("裁切");
        String path = getIntent().getStringExtra("path");
        //
        likeView=findViewById(R.id.likeView);
        sb=findViewById(R.id.sb);

        btHorizontalFlip=findViewById(R.id.btHorizontalFlip);
        btHorizontalFlip.setOnClickListener(this);

        btVerticalFlip=findViewById(R.id.btVerticalFlip);
        btVerticalFlip.setOnClickListener(this);

        btBoth=findViewById(R.id.btBoth);
        btBoth.setOnClickListener(this);

        btReset=findViewById(R.id.btReset);
        btReset.setOnClickListener(this);

        btClip=findViewById(R.id.btClip);

        btClip.setOnClickListener(this);


        likeView.setBitmapForWidth(path, MyApp.width);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //动态改变裁剪框圆角
                float cropWidth = likeView.getClipWidth() / 2;
                float newRadius = progress * 1f / sb.getMax() * cropWidth;
                likeView.setRadius(newRadius);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btHorizontalFlip:
                //水平翻转
                likeView.horizontalFlip();
                break;
            case R.id.btVerticalFlip:
                //垂直翻转
                likeView.verticalFlip();
                break;
            case R.id.btBoth:
                //垂直水平翻转
                likeView.verticalAndHorizontalFlip();
                break;
            case R.id.btReset:
                //重置图片位置
                likeView.reset();
                break;
            case R.id.btClip:
                //裁剪
                File file = new File(FileCacheUtils.getAppImagePath(true)+"head.png");//将要保存图片的路径
                try {
                    FileOutputStream  bos = new FileOutputStream (file);
//                    Bitmap cropBitmap = likeView.clip();
//                    Bitmap cropBitmap = getBitmap(likeView.clip());
                    Bitmap cropBitmap = bitmapZoomByScale(likeView.clip(),110,110);
//                    cropBitmap.copy(Bitmap.Config.ARGB_8888, true);
                    cropBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtra("path",file.getPath());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
    /**根据指定的宽度比例值和高度比例值进行缩放*/
    public Bitmap bitmapZoomByScale(Bitmap srcBitmap, float scaleWidth, float scaleHeight) {
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth / width, scaleHeight / height);
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
        if(bitmap != null) {
            return bitmap;
        }else {
            return srcBitmap;
        }
    }
//    private Bitmap getBitmap(Bitmap bitmap){
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawColor(Color.TRANSPARENT);
//        Paint paint = new Paint();
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        return newBitmap;
//    }
}
