package com.util.ming.projectutil.demo.fastjson;

import java.util.List;

/**
 * Created by ming on 17/10/16.
 */

public class FastJsonUser {


    /**
     * name :
     * number : 0
     * study : [{"studyName":""},{"studyName":""},{"studyName":""}]
     */

    private String name;
    private String number;
    private List<StudyBean> study;

    public FastJsonUser() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<StudyBean> getStudy() {
        return study;
    }

    public void setStudy(List<StudyBean> study) {
        this.study = study;
    }

    public class StudyBean {
        /**
         * studyName :
         */

        private String studyName;

        public String getStudyName() {
            return studyName;
        }

        public void setStudyName(String studyName) {
            this.studyName = studyName;
        }
    }

    @Override
    public String toString() {
        String str = "name=" + name + ",number=" + number;
        for (StudyBean bean : study) {
            str += ",study=" + bean.getStudyName();
        }
        return str;
    }
}
