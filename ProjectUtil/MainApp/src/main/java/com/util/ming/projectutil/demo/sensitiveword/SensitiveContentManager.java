package com.util.ming.projectutil.demo.sensitiveword;

import android.content.Context;
import android.text.TextUtils;


import com.util.ming.projectutil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 常用语管理类
 */
public class SensitiveContentManager {
    private ArrayList<SensitiveContent> prohibitContentList = new ArrayList<SensitiveContent>();//1禁止语
    private ArrayList<SensitiveContent> serviceContentList = new ArrayList<SensitiveContent>();//2服务类提示语
    private ArrayList<SensitiveContent> attentionContentList = new ArrayList<SensitiveContent>();//3敏感信息提示语

    /**
     * 登录时调用此方法，读取敏感词词汇进行入库操作
     *
     * @param context
     */
    public static void takeSensitiveContentFromLocalFile(Context context) {
        List<String> sList = convertStreamToString(context.getApplicationContext(), R.raw.filterwords);
        initDate(0, sList);
    }

    public static List<String> convertStreamToString(Context context, int id) {
        List<String> sList = new ArrayList<String>();
        InputStream is = context.getResources().openRawResource(id);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                sList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sList;
    }

    //循环加载
    private static void initDate(int beginNum, List<String> sList) {
        //进行入库操作
    }

    public void clearSensitiveContent() {
        prohibitContentList = null;
        serviceContentList = null;
        attentionContentList = null;
    }

    /**
     * 实时获取服务器上的敏感词数据进行加载入库
     */
    public void takeSensitiveContentFromServer() {
    }


    public String isXYZTimes(String txtStr) {
        String str = null;
//        if (txtStr != null) { //是敏感词
//            String mCount = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_REPORT_SENSITIVE_COUNT);
//            if (!TextUtils.isEmpty(mCount)) {
//                int mCountTimes = Integer.parseInt(mCount) + 1;
//                String sParam_X = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_X);
//                String sParam_Y = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_Y);
//                String sParam_Z = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_Z);
//                String sParam_M = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_PROHIBIT_PARAN_M);
//                String sParam_N = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_PROHIBIT_PARAN_N);
//
//                int sXTimes = TextUtils.isEmpty(sParam_X) ? 10 : Integer.parseInt(sParam_X);
//                int sYTimes = TextUtils.isEmpty(sParam_Y) ? 20 : Integer.parseInt(sParam_Y);
//                int sZTimes = TextUtils.isEmpty(sParam_Z) ? 50 : Integer.parseInt(sParam_Z);
//                if (sZTimes == mCountTimes) {
//                    //由于多次发布不当言论，您被禁止发送消息
//                    str = IMApp.getInstance().getString(R.string.chat_forbit_user_forever_tip);
//                } else if (mCountTimes == sYTimes) {
//                    //由于多次发布不当言论，您被禁止发送消息，N小时后失效，如您继续发布不当言论，将被永久禁止发送消息，请注意聊天用语
//                    sParam_N = TextUtils.isEmpty(sParam_N) ? "168" : sParam_N;
//                    str = IMApp.getInstance().getString(R.string.chat_forbit_user_forever_tip) + "，" + sParam_N + IMApp.getInstance().getString(R.string.chat_forbit_user_M_tip) + "，" + IMApp.getInstance().getString(R.string.chat_forbit_user_N_tip);
//                } else if (mCountTimes == sXTimes) {
//                    //由于多次发布不当言论，您被禁止发送消息，M小时后失效
//                    sParam_M = TextUtils.isEmpty(sParam_M) ? "24" : sParam_M;
//                    str = IMApp.getInstance().getString(R.string.chat_forbit_user_forever_tip) + "，" + sParam_M + IMApp.getInstance().getString(R.string.chat_forbit_user_M_tip);
//                }
//            }
//        }
        return str;
    }

    public String isXYZTimesFriendCircle(String txtStr) {
        String str = null;
//        if (txtStr != null) { //是敏感词
//            String mCount = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_REPORT_SENSITIVE_COUNT);
//            if (!TextUtils.isEmpty(mCount)) {
//                int mCountTimes = Integer.parseInt(mCount) + 1;
//                String sParam_X = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_X);
//                String sParam_Y = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_Y);
//                String sParam_Z = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_Z);
//                String sParam_M = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_PROHIBIT_PARAN_M);
//                String sParam_N = SystemParamManager.getSystemParam(SystemParam.BIG_TYPE_SELF_PARAM, SystemParam.SMALL_TYPE_SELF_PARAM_PROHIBIT_PARAN_N);
//
//                int sXTimes = TextUtils.isEmpty(sParam_X) ? 10 : Integer.parseInt(sParam_X);
//                int sYTimes = TextUtils.isEmpty(sParam_Y) ? 20 : Integer.parseInt(sParam_Y);
//                int sZTimes = TextUtils.isEmpty(sParam_Z) ? 50 : Integer.parseInt(sParam_Z);
//                if (sZTimes == mCountTimes) {
//                    //由于多次发布不当言论，您被禁止发送消息
//                    str = IMApp.getInstance().getString(R.string.chat_forbit_user_forever_tip);
//                } else if (mCountTimes == sYTimes) {
//                    //由于多次发布不当言论，您被禁止发送消息，N小时后失效，如您继续发布不当言论，将被永久禁止发送消息，请注意聊天用语
//                    sParam_N = TextUtils.isEmpty(sParam_N) ? "168" : sParam_N;
//                    str = IMApp.getInstance().getString(R.string.chat_forbit_user_forever_tip) + "，" + sParam_N + IMApp.getInstance().getString(R.string.chat_forbit_user_M_tip) + "，" + IMApp.getInstance().getString(R.string.chat_forbit_user_N_tip_friend_circle);
//                } else if (mCountTimes == sXTimes) {
//                    //由于多次发布不当言论，您被禁止发送消息，M小时后失效
//                    sParam_M = TextUtils.isEmpty(sParam_M) ? "24" : sParam_M;
//                    str = IMApp.getInstance().getString(R.string.chat_forbit_user_forever_tip) + "，" + sParam_M + IMApp.getInstance().getString(R.string.chat_forbit_user_M_tip);
//                }
//            }
//        }
        return str;
    }


    public String getRemindInfo(String txtStr, ArrayList<SensitiveContent> list) {
        if (list != null) {
            for (SensitiveContent sensitiveContent : list) {
                String sc = sensitiveContent.getSensiContent();
                if (sc != null && !("").equals(sc) && txtStr.contains(sc)) {
                    return sensitiveContent.getRemindInfo();
                }
            }
        }
        return null;
    }


    /**
     * 方法简介：将用户发送内容记入禁止类敏感词发送记录表XXX中
     * <p>输入项说明：无
     * <p>返回项说明：无
     */
    public void noteSensitiveContentToServer(long toUserId, String msgContent, String sensiType) {
        HashMap params = new HashMap();
//        params.put("Channel", "IM");
//        params.put("fCode", "I0054");
//        long userId = IMCore.getSelfCard().getUser_id();
//        params.put("FromIMUserId", userId + "");//聊天发送方（必输）
//        params.put("ToIMUserId", toUserId + "");//聊天接收方（必输）
//        params.put("MsgContent", msgContent);//消息内容（必输）
//        params.put("SensiType", sensiType);//敏感词类型：1-禁止语2-服务类禁止语3-敏感信息提示语（必输）
//        params.put(ICBCHttpClientUtil.RETUAN_PACKAGE_TYPE_FIELD, ICBCHttpClientUtil.RETUAN_PACKAGE_TYPE_IM_SERVER);
//        HttpReqEntity req = new HttpReqEntity();
//        req.setReqParams(params);
//        req.setUrl(Constants.IM_SERVER_URL + Constants.IM_COMMON_SERVLET);
//
//        BackThreadHttpServices asyncHttpTask = new BackThreadHttpServices(req, null, new AsyncSuccessCallBack() {
//            @Override
//            public void onCallBack(HttpRespEntity result) {
//                try {
//                    String retCode = result.getPublicValue("RETCODE").toString();
//                    String sTotalTimes = result.getStringValue("TotalTimes");
//                    String sParam_X = result.getStringValue("Param_X");
//                    String sParam_Y = result.getStringValue("Param_Y");
//                    String sParam_Z = result.getStringValue("Param_Z");
//                    String sParam_M = result.getStringValue("Param_M");
//                    String sParam_N = result.getStringValue("Param_N");
//                    SystemParam systemParam = new SystemParam();
//                    //TotalTimes：当前认定举报、敏感词合计数
//                    systemParam.setParam_big_type(SystemParam.BIG_TYPE_SELF_PARAM);
//                    systemParam.setParam_small_type(SystemParam.SMALL_TYPE_SELF_PARAM_REPORT_SENSITIVE_COUNT);
//                    systemParam.setParam_value(sTotalTimes);
//                    SystemParamDAO.getInstance().replaceSystemParam(systemParam);
//                    //Param_X:敏感词次数参数X
//                    systemParam.setParam_big_type(SystemParam.BIG_TYPE_SELF_PARAM);
//                    systemParam.setParam_small_type(SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_X);
//                    systemParam.setParam_value(sParam_X);
//                    SystemParamDAO.getInstance().replaceSystemParam(systemParam);
//                    //Param_Y:敏感词次数参数Y
//                    systemParam.setParam_big_type(SystemParam.BIG_TYPE_SELF_PARAM);
//                    systemParam.setParam_small_type(SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_Y);
//                    systemParam.setParam_value(sParam_Y);
//                    SystemParamDAO.getInstance().replaceSystemParam(systemParam);
//                    //Param_Z:敏感词次数参数Z
//                    systemParam.setParam_big_type(SystemParam.BIG_TYPE_SELF_PARAM);
//                    systemParam.setParam_small_type(SystemParam.SMALL_TYPE_SELF_PARAM_SENSITIVE_PARAN_Z);
//                    systemParam.setParam_value(sParam_Z);
//                    SystemParamDAO.getInstance().replaceSystemParam(systemParam);
//                    //Param_M:禁言时间次数参数M
//                    systemParam.setParam_big_type(SystemParam.BIG_TYPE_SELF_PARAM);
//                    systemParam.setParam_small_type(SystemParam.SMALL_TYPE_SELF_PARAM_PROHIBIT_PARAN_M);
//                    systemParam.setParam_value(sParam_M);
//                    SystemParamDAO.getInstance().replaceSystemParam(systemParam);
//                    //Param_N:禁言时间次数参数N
//                    systemParam.setParam_big_type(SystemParam.BIG_TYPE_SELF_PARAM);
//                    systemParam.setParam_small_type(SystemParam.SMALL_TYPE_SELF_PARAM_PROHIBIT_PARAN_N);
//                    systemParam.setParam_value(sParam_N);
//                    SystemParamDAO.getInstance().replaceSystemParam(systemParam);
//
//                    IMLog.i("SensitiveContentManager:noteSensitiveContentToServer,retCode:" + retCode);
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }, null);
//        asyncHttpTask.doExcute();
    }

}