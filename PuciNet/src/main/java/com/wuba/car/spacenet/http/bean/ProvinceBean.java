package com.wuba.car.spacenet.http.bean;

import java.util.List;

public class ProvinceBean {
    public String code;
    public String name;
    public List<CityBean> citylist;

    public class CityBean {
        public String code;
        public String name;
        public List<AreaBean> arealist;
    }

    public class AreaBean {
        public String name;
        public String code;
    }
}
