package com.lingan.edongspeechlibrary.bean;

import java.util.List;

/**
 * Created by dyx on 2017/12/29.
 *
 */

public class NetFMBean {

    private List<DbdataBean> dbdata;

    public List<DbdataBean> getDbdata() {
        return dbdata;
    }

    public void setDbdata(List<DbdataBean> dbdata) {
        this.dbdata = dbdata;
    }

    public static class DbdataBean {
        /**
         * track : 2、不差钱Ⅱ—小沈阳 沈春阳 杨冰 鸭蛋
         * playSize32 : 2839677
         * id : 12857967
         * trackIntroduction :
         * coverUrlSmall : http://fdfs.xmcdn.com/group11/M09/22/36/wKgDbVba3KSQdmyyAAISXbDQnhQ482_web_meduim.jpg
         * coverUrlLarge : http://fdfs.xmcdn.com/group11/M09/22/36/wKgDbVba3KSQdmyyAAISXbDQnhQ482_mobile_large.jpg
         * coverUrlMiddle : http://fdfs.xmcdn.com/group11/M09/22/36/wKgDbVba3KSQdmyyAAISXbDQnhQ482_web_large.jpg
         * playSize64 : 5679291
         * playUrl64 : http://fdfs.xmcdn.com/group15/M06/15/7C/wKgDZVbawUbAz3GtAFaou_A9FEY489.mp3
         * playUrl32 : http://fdfs.xmcdn.com/group15/M06/15/7D/wKgDZVbawUzisOLFACtUfaQZiiM510.mp3
         * duration : 709
         */

        private String track;
        private String coverUrlSmall;
        private String coverUrlLarge;
        private String coverUrlMiddle;
        private String playUrl64;
        private String playUrl32;

        public String getTrack() {
            return track;
        }

        public void setTrack(String track) {
            this.track = track;
        }

        public String getCoverUrlSmall() {
            return coverUrlSmall;
        }

        public void setCoverUrlSmall(String coverUrlSmall) {
            this.coverUrlSmall = coverUrlSmall;
        }

        public String getCoverUrlLarge() {
            return coverUrlLarge;
        }

        public void setCoverUrlLarge(String coverUrlLarge) {
            this.coverUrlLarge = coverUrlLarge;
        }

        public String getCoverUrlMiddle() {
            return coverUrlMiddle;
        }

        public void setCoverUrlMiddle(String coverUrlMiddle) {
            this.coverUrlMiddle = coverUrlMiddle;
        }

        public String getPlayUrl64() {
            return playUrl64;
        }

        public void setPlayUrl64(String playUrl64) {
            this.playUrl64 = playUrl64;
        }

        public String getPlayUrl32() {
            return playUrl32;
        }

        public void setPlayUrl32(String playUrl32) {
            this.playUrl32 = playUrl32;
        }
    }
}
