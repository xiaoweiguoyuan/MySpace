package com.puci.qs.myspace.entity;

import java.util.List;

public class ProgramBean {

    /**
     * podcasts : [{"id":1596,"itune_id":"941490670","name":"南锣鼓巷的一只猫","author":"南锣鼓巷的一只猫","url":"https://podcasts.apple.com/us/podcast/id941490670","feed":"http://rss.lizhi.fm/rss/845754.xml","category":"图书","image":"https://is4-ssl.mzstatic.com/image/thumb/Podcasts113/v4/34/7d/52/347d5291-a8d4-8220-c717-64a28d1c4ca9/mza_3110443508698993291.jpg/600x600bb.jpg"}]
     * page_size : 10
     * cursor : 1596
     */

    private int page_size;
    private int cursor;
    private List<PodcastsBean> podcasts;

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public List<PodcastsBean> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastsBean> podcasts) {
        this.podcasts = podcasts;
    }

    public static class PodcastsBean {
        /**
         * id : 1596
         * itune_id : 941490670
         * name : 南锣鼓巷的一只猫
         * author : 南锣鼓巷的一只猫
         * url : https://podcasts.apple.com/us/podcast/id941490670
         * feed : http://rss.lizhi.fm/rss/845754.xml
         * category : 图书
         * image : https://is4-ssl.mzstatic.com/image/thumb/Podcasts113/v4/34/7d/52/347d5291-a8d4-8220-c717-64a28d1c4ca9/mza_3110443508698993291.jpg/600x600bb.jpg
         */

        private int id;
        private String itune_id;
        private String name;
        private String author;
        private String url;
        private String feed;
        private String category;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItune_id() {
            return itune_id;
        }

        public void setItune_id(String itune_id) {
            this.itune_id = itune_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
