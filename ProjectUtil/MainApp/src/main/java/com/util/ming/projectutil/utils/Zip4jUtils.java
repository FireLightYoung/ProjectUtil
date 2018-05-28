package com.util.ming.projectutil.utils;

import android.util.Log;

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
 * Created by ming on 18/1/19.
 */

public class Zip4jUtils {

    /**
     * 标准压缩,无密码
     *
     * @param fileZipPath 生成ZIP文件的路径
     * @param filesToAdd  需要压缩的文件列表
     */
    public static void addZip(String fileZipPath, ArrayList filesToAdd) throws ZipException {
        try {
            ZipFile zipFile = new ZipFile(fileZipPath);

            ZipParameters parameters = new ZipParameters();

            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            throw e;
        }
    }


    /**
     * 标准加密
     *
     * @param fileZipPath 生成ZIP文件的路径
     * @param filesToAdd  需要压缩的文件列表
     * @param passWord    设置密码
     */
    public static void addZip(String fileZipPath, ArrayList filesToAdd, String passWord) throws ZipException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileZipPath);


            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            // Set password
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword(passWord);

            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            throw e;
        }
    }


    /**
     * 标准无密码压缩，压缩某文件夹内文件
     *
     * @param fileZipPath       生成ZIP文件的路径
     * @param filesToAddDirPath 需要压缩的文件夹
     */
    public static void addZip(String fileZipPath, String filesToAddDirPath) throws ZipException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileZipPath);


            String folderToAdd = filesToAddDirPath;

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.addFolder(folderToAdd, parameters);
        } catch (ZipException e) {
            throw e;
        }
    }

    /**
     * 标准密有密码压缩，压缩某文件夹内文件
     *
     * @param fileZipPath       生成ZIP文件的路径
     * @param filesToAddDirPath 需要压缩的文件夹
     * @param passWord          设置密码
     */
    public static void addZip(String fileZipPath, String filesToAddDirPath, String passWord) throws ZipException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileZipPath);


            String folderToAdd = filesToAddDirPath;

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            // Set password
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword(passWord);

            zipFile.addFolder(folderToAdd, parameters);
        } catch (ZipException e) {
            throw e;
        }
    }

    /**
     * 标准解压
     *
     * @param fileZipPath  解压的ZIP文件
     * @param filesDirPath 解压到的文件夹路径
     * @param passWord     密码，无密码则设置为 null
     */
    public static void unZip(String fileZipPath, String filesDirPath, String passWord) throws ZipException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileZipPath);
            if (passWord != null) {
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(passWord);
                }
            }
            zipFile.extractAll(filesDirPath);
        } catch (ZipException e) {
            throw e;
        }
    }


    /**
     * 浏览压缩文件中所有文件
     *
     * @param fileZipPath 解压的ZIP文件
     * @param passWord    密码，无密码则设置为 null
     * @throws ZipException
     */
    public static List showZip(String fileZipPath, String passWord) throws ZipException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileZipPath);
            if (passWord != null) {
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(passWord);
                }
            }
            List fileHeaderList = zipFile.getFileHeaders();
            return fileHeaderList;
        } catch (ZipException e) {
            throw e;
        }
    }


}
