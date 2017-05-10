package com.util.ming.projectutil.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Administrator on 2016/5/9.
 * 文件工具类
 */
public class FileUtils {

    /**
     * 将图片写入到磁盘
     *
     * @param img      图片数据流
     * @param fileName 文件保存时的名称
     */
    public static void writeImageToDisk(String dirUrl, String fileName, byte[] img) throws IOException {

        FileOutputStream fops = null;
        try {
            File filedir = new File(dirUrl);
            if (!filedir.exists()) {
                filedir.mkdir();
            }
            File file = new File(dirUrl + File.separator + fileName);
            fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (fops != null) {
                try {
                    fops.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * String写入文件
     *
     * @param dirUrl
     * @param fileName
     * @param string
     * @throws Exception
     */
    public static void StringToFile(String dirUrl, String fileName, String string) throws IOException {
        FileOutputStream fops = null;
        try {
            File filedir = new File(dirUrl);
            if (!filedir.exists()) {
                filedir.mkdir();
            }
            File file = new File(dirUrl + File.separator + fileName);
            fops = new FileOutputStream(file);
            fops.write(string.getBytes());
            fops.flush();
            fops.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (fops != null) {
                try {
                    fops.close();
                } catch (Exception e) {

                }

            }
        }
    }

    /**
     * 从文件读出String
     *
     * @param Path
     * @return
     * @throws Exception
     */
    public static String FileToString(String Path) throws IOException {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        String str = null;
        try {
            File file = new File(Path);
            if (!file.exists()) {
                file.createNewFile();
            }
            is = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            str = baos.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {

                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
        }
        return str;
    }

    /**
     * 从文件读出String
     *
     * @param Path
     * @return
     * @throws Exception
     */
    public static String FileToString2(String Path) throws IOException {
        StringBuffer sb = null;
        Reader reader = null;
        BufferedReader br = null;
        try {
            File file = new File(Path);
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new FileReader(Path);
            br = new BufferedReader(reader);
            sb = new StringBuffer();
            String data = null;
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 复制gif图片
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) throws IOException {
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            inStream = new FileInputStream(oldfile); //读入原文件
            fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception e) {

                }

            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (Exception e) {

                }
            }
        }

    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (IOException e) {
            throw e;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {

                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {

                }
            }

        }
        return buffer;
    }


    /**
     * 文件转String
     *
     * @param file
     * @return
     */
    public String readTxtFile(File file) throws IOException {
        String content = ""; // 文件内容字符串
        // 如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            Log.d("TestFile", "The File doesn't not exist.");
        } else {
            InputStream instream = null;
            InputStreamReader inputreader = null;
            BufferedReader buffreader = null;
            try {
                instream = new FileInputStream(file);
                if (instream != null) {
                    inputreader = new InputStreamReader(instream);
                    buffreader = new BufferedReader(inputreader);
                    String line;
                    // 分行读取
                    while ((line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }

                }
            } catch (IOException e) {
                throw e;
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (inputreader != null) {
                    try {
                        inputreader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (buffreader != null) {
                    try {
                        buffreader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return content;
    }


    /**
     * String转文件
     *
     * @param f    - 指定的目录
     * @param buff
     */
    public boolean saveByteToFile(File f, byte[] buff) throws IOException {
        FileOutputStream fOut = null;
        try {
            if (buff != null && buff.length != 0) {
                if (f.exists()) {
                    f.delete();
                }
                f.createNewFile();
                fOut = new FileOutputStream(f);
                fOut.write(buff);
                fOut.flush();
            }
            return true;
        } catch (IOException e) {
            throw e;
        } finally {
            if (fOut != null) {
                try {
                    fOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
