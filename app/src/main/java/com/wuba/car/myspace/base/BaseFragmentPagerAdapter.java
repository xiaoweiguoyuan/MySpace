package com.wuba.car.myspace.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wuba.car.myspace.fragment.AllFragment;
import com.wuba.car.myspace.fragment.ProgramFragment;
import com.wuba.car.myspace.fragment.SingleFragment;
import com.wuba.car.myspace.fragment.SubscribeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goldze on 2017/7/17.
 * FragmentPager适配器
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;//ViewPager要填充的fragment列表
    private List<String> title;//tab中的title文字列表

    //使用构造方法来将数据传进去
    public BaseFragmentPagerAdapter(FragmentManager fm,String keyword) {
        super(fm);
        this.list = pagerFragment(keyword);
        this.title = pagerTitleString();
    }

    @Override
    public Fragment getItem(int position) {//获得position中的fragment来填充
        return list.get(position);
    }

    @Override
    public int getCount() {//返回FragmentPager的个数
        return list.size();
    }

    //FragmentPager的标题,如果重写这个方法就显示不出tab的标题内容
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    private List<Fragment> pagerFragment(String keyword) {
        List<Fragment> list = new ArrayList<>();
        list.add(AllFragment.newInstance(keyword));
        list.add(ProgramFragment.newInstance(keyword));
        list.add(SingleFragment.newInstance(keyword));
        return list;
    }



    private List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("节目");
        list.add("单集");
        return list;
    }
}
