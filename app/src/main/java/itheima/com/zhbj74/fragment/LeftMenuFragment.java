package itheima.com.zhbj74.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import itheima.com.zhbj74.MainActivity;
import itheima.com.zhbj74.R;
import itheima.com.zhbj74.domain.NewsMenu;

/**
 * 侧滑菜单的 Fragment
 * Created by Administrator on 2016/8/15.
 */

public class LeftMenuFragment extends BaseFragment {

    private ListView mListView;

    private int mCurrentPos = 0;// 当前被选中的item的位置

    private ArrayList<NewsMenu.NewsMenuData> mMenuDatas;

    private LeftMenuAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.left_menu_fragment,null);
        mListView = (ListView) view.findViewById(R.id.lv_list);
        return view;
    }

    @Override
    public void initData() {

    }

    /**
     * 设置策划栏的数据
     */
    public void setMenuData(ArrayList<NewsMenu.NewsMenuData> menuData){
        mMenuDatas = menuData;
        mCurrentPos = 0;//当前选中的位置归零
        adapter = new LeftMenuAdapter();
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos = position;
                toggle();//收起侧边栏
                //刷新页面
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void toggle() {
        MainActivity mainActivity = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        slidingMenu.toggle();
    }

    class LeftMenuAdapter extends BaseAdapter{

        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return mMenuDatas.size();
        }

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         *                 data set.
         * @return The data at the specified position.
         */
        @Override
        public NewsMenu.NewsMenuData getItem(int position) {
            return mMenuDatas.get(position);
        }

        /**
         * Get the row id associated with the specified position in the list.
         *
         * @param position The position of the item within the adapter's data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * Get a View that displays the data at the specified position in the data set. You can either
         * create a View manually or inflate it from an XML layout file. When the View is inflated, the
         * parent View (GridView, ListView...) will apply default layout parameters unless you use
         * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
         * to specify a root view and to prevent attachment to the root.
         *
         * @param position    The position of the item within the adapter's data set of the item whose view
         *                    we want.
         * @param convertView The old view to reuse, if possible. Note: You should check that this view
         *                    is non-null and of an appropriate type before using. If it is not possible to convert
         *                    this view to display the correct data, this method can create a new view.
         *                    Heterogeneous lists can specify their number of view types, so that this View is
         *                    always of the right type (see {@link #getViewTypeCount()} and
         *                    {@link #getItemViewType(int)}).
         * @param parent      The parent that this view will eventually be attached to
         * @return A View corresponding to the data at the specified position.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity,R.layout.list_item_left_menu,null);
            TextView textview = (TextView) view.findViewById(R.id.tv_menu);

            textview.setText(getItem(position).title);
            if (mCurrentPos == position){
                textview.setEnabled(true);
            }else {
                textview.setEnabled(false);
            }
            return view;
        }
    }

}
