package com.wuba.car.myspace.entity;

import java.util.List;

public class AllBean {

    private List<PodcastsBean> podcasts;
    private List<EpisodesBean> episodes;

    public List<PodcastsBean> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<PodcastsBean> podcasts) {
        this.podcasts = podcasts;
    }

    public List<EpisodesBean> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodesBean> episodes) {
        this.episodes = episodes;
    }

    public static class PodcastsBean {
        /**
         * id : 1236
         * itune_id : 657765158
         * name : 大内密谈
         * author : 大内密谈
         * url : https://podcasts.apple.com/us/podcast/id657765158
         * feed : http://rss.lizhi.fm/rss/14275.xml
         * category : 表演
         * image : https://is3-ssl.mzstatic.com/image/thumb/Podcasts113/v4/ea/5e/27/ea5e2748-1ee3-d2e0-84b8-c438b57b3a57/mza_368630560618801911.jpg/600x600bb.jpg
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

    public static class EpisodesBean {
        /**
         * id : 781
         * art_url : {"image_small":"http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_200x200.jpg","image_middle":"http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_400x400.jpg","image_big":"http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_800x800.jpg"}
         * itune_id : http://cdn.lizhi.fm/audio/2013/10/24/6866116188130694_hd.mp3
         * title : vol.39 听我唱支黄河谣(上)
         * subtitle : &nbsp;&nbs...
         * description : “黄河的水不停地流，流过了家流过了兰州，月亮照在铁桥上，我就对着黄河唱。”每次，野孩子的音乐会临近结束，张佺、张玮玮、郭龙三人就会放下手上所有的乐器，闭上眼睛，双手扣在膝盖上，庄重地清唱出这首歌。

         上期备受欢迎的大内密探民谣专题里，我们提到野孩子乐队与河酒吧是中国当代民谣的发轫端。这支传奇的民谣乐队来自甘肃，血液连着大地，他们把西北的民间音乐带到了城市，并用现代的方式传承下去。而河酒吧，上世纪北京最乌托邦的那一小片地方，也承载了中国当代民谣几乎所有故事。本期大内密谈继续与民谣经纪人郭小寒一起，专门用130分钟上下两集的内容致敬这段历史，回想过去那些冒着热气的时光，并想想我们如今怎样自由且有尊严地用音乐表达生活。

         大内密谈的新浪微博帐号“大内密谈MidnightTalks”

         大内密谈的微信公共帐号“midnightalks”

         欢迎加入 欢迎互动：）
         * episode_art_url : http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_200x200.jpg
         * published : 1382547876
         * author : null
         * link : http://cdn.lizhi.fm/audio/2013/10/24/6866116188130694_hd.mp3
         * total_time : 3903
         * format : audio/mpeg
         * like_cnt : 0
         * podcast : {"id":1236,"itune_id":"657765158","name":"大内密谈","author":"大内密谈","url":"https://podcasts.apple.com/us/podcast/id657765158","feed":"http://rss.lizhi.fm/rss/14275.xml","category":"表演","image":"https://is3-ssl.mzstatic.com/image/thumb/Podcasts113/v4/ea/5e/27/ea5e2748-1ee3-d2e0-84b8-c438b57b3a57/mza_368630560618801911.jpg/600x600bb.jpg"}
         */

        private int id;
        private ArtUrlBean art_url;
        private String itune_id;
        private String title;
        private String subtitle;
        private String description;
        private String episode_art_url;
        private int published;
        private Object author;
        private String link;
        private int total_time;
        private String format;
        private int like_cnt;
        private PodcastBean podcast;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ArtUrlBean getArt_url() {
            return art_url;
        }

        public void setArt_url(ArtUrlBean art_url) {
            this.art_url = art_url;
        }

        public String getItune_id() {
            return itune_id;
        }

        public void setItune_id(String itune_id) {
            this.itune_id = itune_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEpisode_art_url() {
            return episode_art_url;
        }

        public void setEpisode_art_url(String episode_art_url) {
            this.episode_art_url = episode_art_url;
        }

        public int getPublished() {
            return published;
        }

        public void setPublished(int published) {
            this.published = published;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getTotal_time() {
            return total_time;
        }

        public void setTotal_time(int total_time) {
            this.total_time = total_time;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public int getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(int like_cnt) {
            this.like_cnt = like_cnt;
        }

        public PodcastBean getPodcast() {
            return podcast;
        }

        public void setPodcast(PodcastBean podcast) {
            this.podcast = podcast;
        }

        public static class ArtUrlBean {
            /**
             * image_small : http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_200x200.jpg
             * image_middle : http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_400x400.jpg
             * image_big : http://cdn.lizhi.fm/audio_cover/2013/10/24/6866116188130439_800x800.jpg
             */

            private String image_small;
            private String image_middle;
            private String image_big;

            public String getImage_small() {
                return image_small;
            }

            public void setImage_small(String image_small) {
                this.image_small = image_small;
            }

            public String getImage_middle() {
                return image_middle;
            }

            public void setImage_middle(String image_middle) {
                this.image_middle = image_middle;
            }

            public String getImage_big() {
                return image_big;
            }

            public void setImage_big(String image_big) {
                this.image_big = image_big;
            }
        }

        public static class PodcastBean {
            /**
             * id : 1236
             * itune_id : 657765158
             * name : 大内密谈
             * author : 大内密谈
             * url : https://podcasts.apple.com/us/podcast/id657765158
             * feed : http://rss.lizhi.fm/rss/14275.xml
             * category : 表演
             * image : https://is3-ssl.mzstatic.com/image/thumb/Podcasts113/v4/ea/5e/27/ea5e2748-1ee3-d2e0-84b8-c438b57b3a57/mza_368630560618801911.jpg/600x600bb.jpg
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
}
