package com.util.ming.projectutil.demo.luban;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.util.ming.projectutil.MainActivity;
import com.util.ming.projectutil.R;
import com.util.ming.projectutil.demo.permission.RequestPermissions;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class LubanActivity extends Activity implements View.OnClickListener {

    ImageView mImageView;
    Button mButton;


    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luban);

        mImageView = findViewById(R.id.luban_img);
        mButton = findViewById(R.id.luban_start_btn);

        mButton.setOnClickListener(this);


        path = getPath();
        File file = new File(path);
        if (file.exists()) {
            Logger.d("path==" + path);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.luban_start_btn) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    compressWithLs(path);
                }
            }.start();
        }
    }


    /**
     * 同步调用
     */
    private void compressWithRx(final List<String> photos) {
        Flowable.just(photos)
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        return Luban.with(LubanActivity.this).load(list).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(@NonNull List<File> list) throws Exception {
                        for (File file : list) {
                            showResult(photos, file);
                        }
                    }
                });
    }


    /**
     * 异步调用
     * 压缩图片 Listener 方式
     */
    private void compressWithLs(final String photoPath) {
        Luban.with(this)
                .load(photoPath)    // 传入需要压缩的图片列表
                .ignoreBy(100)   // 忽略不压缩图片的大小
                .setTargetDir(getCachePath()) // 设置压缩后文件存储的路径
                .setCompressListener(new OnCompressListener() {   // 设置回调
                    @Override
                    public void onStart() {
                        //TODO 压缩前开始调用 ，可以在方法内启动
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        showResult(photoPath, file);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO  压缩过程中出现问题时调用
                    }
                }).launch();
    }


    private int[] computeSize(String srcImg) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;

        BitmapFactory.decodeFile(srcImg, options);
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/2.jpg";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    private String getCachePath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    public void showResult(final String photos, File file) {
        Logger.e("file==" + file.getPath());
    }

    public void showResult(final List<String> photos, File file) {

    }

}
