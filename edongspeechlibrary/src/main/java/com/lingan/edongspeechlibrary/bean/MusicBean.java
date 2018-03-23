package com.lingan.edongspeechlibrary.bean;

import java.util.List;

/**
 * Created by dyx on 2017/12/22.
 */

public class MusicBean {

    private List<DbdataBean> dbdata;

    public List<DbdataBean> getDbdata() {
        return dbdata;
    }

    public void setDbdata(List<DbdataBean> dbdata) {
        this.dbdata = dbdata;
    }

    public static class DbdataBean {
        /**
         * imgSize : 25772
         * artistPic : http://cdndown.dorylist.com/uploads/images/2013/06/19/033609875.jpg
         * id : 21675
         * img : http://cdndown.dorylist.com/uploads/images/2013/06/19/033609875.jpg
         * artist : 魏晨
         * album : 其 它
         * lrcUrl : http://cdndown.dorylist.com/uploads/lrc/21675.lrc
         * title : 热雪
         * albumPic :
         * duration : 0
         * url : http://cdndown.dorylist.com/uploads/ogg/2013/09/06/104314930.ogg
         * lrcSize : 1263
         * size : 1069350
         */

        private String artistPic;
        private String id;
        private String img;
        private String artist;
        private String lrcUrl;
        private String title;
        private String url;

        public String getArtistPic() {
            return artistPic;
        }

        public void setArtistPic(String artistPic) {
            this.artistPic = artistPic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getLrcUrl() {
            return lrcUrl;
        }

        public void setLrcUrl(String lrcUrl) {
            this.lrcUrl = lrcUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
