package com.util.ming.projectutil.demo.sensitiveword;

import java.io.Serializable;

/**
 * 常用语
 */
public class SensitiveContent implements Serializable {

    /**
     * 敏感词大类，存储在sysParam表中
     */
    public static final String SYS_PARAM_BIG_TYPE_MANAGER_SENSITIVE_CONTENT = "Manager_Sensitive_Content";
    /**
     * 敏感词上一次更新时间小类，存储在sysParam表中
     */
    public static final String SYS_PARAM_BIG_TYPE_MANAGER_SENSITIVE_CONTENT_UPDATETIME = "Manager_Sensitive_Content_UpdateTime";
    /**
     * 敏感词本地版本，存储在sysParam表中
     * 2017.8月以前没有此字段，8月版更新本地表，设值为1
     */
    public static final String SYS_PARAM_SMALL_TYPE_MANAGER_SENSITIVE_CONTENT_LOCAL_VERSION = "Manager_Sensitive_Content_Local_Version";
    /**
     * 敏感词id
     */
    private long SensiId;
    /**
     * 序号
     */
    private long SensiNo;
    /**
     * 类别
     */
    private String SensiType;
    /**
     * 敏感词内容
     */
    private String SensiContent;

    /**
     * 提示语
     */
    private String RemindInfo;

    /**
     * 是否生效标志（1-生效 0-失效）
     */
    private String EffectFlag;

    /**
     * 版本号
     */
    private String VersionNo;

    public long getSensiId() {
        return SensiId;
    }

    public void setSensiId(long sensiId) {
        SensiId = sensiId;
    }

    public long getSensiNo() {
        return SensiNo;
    }

    public void setSensiNo(long sensiNo) {
        SensiNo = sensiNo;
    }

    public String getSensiType() {
        return SensiType;
    }

    public void setSensiType(String sensiType) {
        SensiType = sensiType;
    }

    public String getSensiContent() {
        return SensiContent;
    }

    public void setSensiContent(String sensiContent) {
        SensiContent = sensiContent;
    }

    public String getRemindInfo() {
        return RemindInfo;
    }

    public void setRemindInfo(String remindInfo) {
        RemindInfo = remindInfo;
    }

    public String getEffectFlag() {
        return EffectFlag;
    }

    public void setEffectFlag(String effectFlag) {
        EffectFlag = effectFlag;
    }

    public String getVersionNo() {
        return VersionNo;
    }

    public void setVersionNo(String versionNo) {
        VersionNo = versionNo;
    }
}
