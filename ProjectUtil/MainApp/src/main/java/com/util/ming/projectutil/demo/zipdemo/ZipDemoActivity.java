package com.util.ming.projectutil.demo.zipdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;
import com.util.ming.projectutil.utils.SDCardUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.io.ZipOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.unzip.UnzipUtil;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ming on 17/5/9.
 */

public class ZipDemoActivity extends BaseActivity implements View.OnClickListener {


    public String filePathA = SDCardUtils.getSDCardPath() + "aaaa.zip";
    public String filePathB = SDCardUtils.getSDCardPath() + "b.zip";
    public String filePathC = SDCardUtils.getSDCardPath() + "c.zip";
    public String filePathD = SDCardUtils.getSDCardPath() + "d.zip";
    public String filePath1 = SDCardUtils.getSDCardPath() + "1.jpg";
    public String filePath2 = SDCardUtils.getSDCardPath() + "2.jpg";
    public String filePath3 = SDCardUtils.getSDCardPath() + "3.mp3";
    public String filePath4 = SDCardUtils.getSDCardPath() + "4.xls";

    private TextView tv_title;
    private TextView tv_progress;
    private Button btn_set;
    private Button btn_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);
        initData();
        initView();
        initEvent();
    }

    private void initData() {


    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.textView_zip);
        tv_progress = (TextView) findViewById(R.id.textView_zip_progress);
        btn_set = (Button) findViewById(R.id.btn_zip_set);
        btn_get = (Button) findViewById(R.id.btn_zip_get);
    }

    private void initEvent() {
        btn_set.setOnClickListener(this);
        btn_get.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zip_set:
                setZip1();
                break;
            case R.id.btn_zip_get:
                getZip1();
                break;
        }
    }


    /**
     * 基本示例
     */
    public void setZip1() {
        new Thread() {
            @Override
            public void run() {
                try {
                    ZipFile zipFile = new ZipFile(filePathA);
                    final ProgressMonitor monitor = zipFile.getProgressMonitor();

                    super.run();
                    while (monitor.getResult() != ProgressMonitor.RESULT_SUCCESS) {
                        Log.i("yang", "getPercentDone==" + monitor.getPercentDone());
                        Log.i("yang", "getState==" + monitor.getState());
                        Log.i("yang", "getTotalWork==" + monitor.getTotalWork());
                        Log.i("yang", "getCurrentOperation==" + monitor.getCurrentOperation());
                        Log.i("yang", "getResult==" + monitor.getResult());
                        Log.i("yang", "getWorkCompleted==" + monitor.getWorkCompleted());

                    }

//            zipFile.setRunInThread(true);
                    ArrayList filesToAdd = new ArrayList();
                    filesToAdd.add(new File(filePath1));
                    filesToAdd.add(new File(filePath2));
                    filesToAdd.add(new File(filePath3));
                    filesToAdd.add(new File(filePath4));


                    ZipParameters parameters = new ZipParameters();
                    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
                    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
                    Log.i("yang", "now time==" + yyyy_MM_DD_HH_mm_ss_format(System.currentTimeMillis()));
                    zipFile.addFiles(filesToAdd, parameters);
                    Log.i("yang", "next time==" + yyyy_MM_DD_HH_mm_ss_format(System.currentTimeMillis()));
                    File file = new File(filePathA);
                    long length = file.length() / 1024 / 1024;

                    Log.i("yang", "length ==" + length);

                } catch (ZipException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static String yyyy_MM_DD_HH_mm_ss_format(long times) {
        String formatString = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(new Date(times));
    }

    /**
     * 标准加密，设置密码
     */

    public void setZip2() {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile("c:\\ZipTest\\test2.zip");


            ArrayList filesToAdd = new ArrayList();
            filesToAdd.add(new File(filePath1));
            filesToAdd.add(new File(filePath2));
            filesToAdd.add(new File(filePath3));
            filesToAdd.add(new File(filePath4));
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

// Set password
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword("test123!");

            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    /**
     * AES加密
     */
    public void setZip3() {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile("c:\\ZipTest\\test3.zip");

            ArrayList filesToAdd = new ArrayList();
            filesToAdd.add(new File(filePath1));
            filesToAdd.add(new File(filePath2));
            filesToAdd.add(new File(filePath3));
            filesToAdd.add(new File(filePath4));
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

// Set password
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword("test123!");

            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    /**
     * 压缩文件夹
     */
    public void setZip4() {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile("c:\\ZipTest\\test4.zip");


            String folderToAdd = "c:\\ZipTest";

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.addFolder(folderToAdd, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解压
     */
    public void getZip1() {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile("c:\\ZipTest\\test1.zip");
            zipFile.extractAll("c:\\ZipTest1");
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压
     */
    public void getZip2() {
        ZipFile zipFile2 = null;
        try {
            zipFile2 = new ZipFile("c:\\ZipTest\\test2.zip");

            if (zipFile2.isEncrypted()) {
                zipFile2.setPassword("test123!");
            }
            List fileHeaderList = zipFile2.getFileHeaders();
            for (int i = 0; i < fileHeaderList.size(); i++) {
                FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
                zipFile2.extractFile(fileHeader, "c:\\ZipTest2\\");
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解压所有文件到流
     *
     * @throws Exception
     */
    public void getZip3() throws Exception {
        ZipFile zipFile = new ZipFile("c:\\ZipTest\\test2.zip");
        if (zipFile.isEncrypted()) {
            zipFile.setPassword("test123!");
        }

        List fileHeaderList = zipFile.getFileHeaders();

        for (int i = 0; i < fileHeaderList.size(); i++) {
            FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
            if (fileHeader != null) {

                String outFilePath = "c:\\ZipTest3\\" + System.getProperty("file.separator") + fileHeader.getFileName();
                File outFile = new File(outFilePath);

                if (fileHeader.isDirectory()) {
                    outFile.mkdirs();
                    continue;
                }

                File parentDir = outFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                ZipInputStream is = zipFile.getInputStream(fileHeader);
                OutputStream os = new FileOutputStream(outFile);

                int readLen = -1;
                byte[] buff = new byte[4096];

                while ((readLen = is.read(buff)) != -1) {
                    os.write(buff, 0, readLen);
                }

                os.close();
                os = null;
                is.close();
                is = null;

                UnzipUtil.applyFileAttributes(fileHeader, outFile);

                System.out.println("Done extracting: " + fileHeader.getFileName());
            } else {
                System.err.println("fileheader is null. Shouldn't be here");
            }
        }
    }


    /**
     * 解压单个文件
     *
     * @throws ZipException
     */
    public void getZip4() throws ZipException {
        ZipFile zipFile = new ZipFile("c:\\ZipTest\\test2.zip");
        if (zipFile.isEncrypted()) {
            zipFile.setPassword("test123!");
        }

        zipFile.extractFile("文件.doc", "c:\\ZipTest4\\");
    }

    /**
     * 创建ZIP流
     *
     * @throws Exception
     */
    public void getZip5() throws Exception {
        ArrayList filesToAdd = new ArrayList();
        filesToAdd.add(new File("c:\\ZipTest\\sample.txt"));
        filesToAdd.add(new File("c:\\ZipTest\\文件.doc"));
        filesToAdd.add(new File("c:\\ZipTest\\파일.xls"));
        filesToAdd.add(new File("c:\\ZipTest\\ファイル.ppt"));

        ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(new File("c:\\ZipTest\\test8.zip")));

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

// Set password
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters.setPassword("test123!");

        for (int i = 0; i < filesToAdd.size(); i++) {
            File file = (File) filesToAdd.get(i);
            outputStream.putNextEntry(file, parameters);

            if (file.isDirectory()) {
                outputStream.closeEntry();
                continue;
            }

            InputStream inputStream = new FileInputStream(file);
            byte[] readBuff = new byte[4096];
            int readLen = -1;
            while ((readLen = inputStream.read(readBuff)) != -1) {
                outputStream.write(readBuff, 0, readLen);
            }

            outputStream.closeEntry();

            inputStream.close();
        }

        outputStream.finish();

        outputStream.close();
    }


    /**
     * 删除压缩文件中某个文件
     *
     * @throws ZipException
     */
    public void deleteZip() throws ZipException {
        ZipFile zipFile = new ZipFile("c:\\ZipTest\\test1.zip");

//删除指定文件
        zipFile.removeFile("sample.txt");

//删除第一个文件
        if (zipFile.getFileHeaders() != null && zipFile.getFileHeaders().size() > 0) {
            zipFile.removeFile((FileHeader) zipFile.getFileHeaders().get(0));
        } else {
            System.out.println("This cannot be demonstrated as zip file does not have any files left");
        }
    }


    /**
     * 浏览压缩文件中所有文件
     *
     * @throws ZipException
     */
    public void showZip() throws ZipException {
        ZipFile zipFile = new ZipFile("c:\\ZipTest\\test5.zip");

        List fileHeaderList = zipFile.getFileHeaders();

        for (int i = 0; i < fileHeaderList.size(); i++) {
            FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
            System.out.println("****File Details for: " + fileHeader.getFileName() + "*****");
            System.out.println("Name: " + fileHeader.getFileName());
            System.out.println("Compressed Size: " + fileHeader.getCompressedSize());
            System.out.println("Uncompressed Size: " + fileHeader.getUncompressedSize());
            System.out.println("CRC: " + fileHeader.getCrc32());
            System.out.println("************************************************************");
        }
    }

    /**
     * 分割压缩文件
     *
     * @throws ZipException
     */
    public void splitZip() throws ZipException {

        ZipFile zipFile = new ZipFile("c:\\ZipTest\\test7.zip");

        ArrayList filesToAdd = new ArrayList();
        filesToAdd.add(new File("c:\\ZipTest\\sample.txt"));
        filesToAdd.add(new File("c:\\ZipTest\\文件.doc"));
        filesToAdd.add(new File("c:\\ZipTest\\파일.xls"));
        filesToAdd.add(new File("c:\\ZipTest\\ファイル.ppt"));

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

// SplitLenth has to be greater than 65536 bytes
// zipFile.createZipFileFromFolder()
        zipFile.createZipFile(filesToAdd, parameters, true, 65536);
    }


    /**
     * 向压缩文件中添加文件
     */
    public void addZip() {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile("c:\\ZipTest\\test1.zip");


            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setFileNameInZip("文件2.doc");
            parameters.setSourceExternalStream(true);

            InputStream is = null;

            is = new FileInputStream("c:\\ZipTest\\文件2.doc");

            zipFile.addStream(is, parameters);

            is.close();
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
