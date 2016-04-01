package com.cn.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.entity.BaseEntity;
import com.cn.entity.CitySelectPage;
import com.cn.localutils.AutoPosition;
import com.cn.localutils.AutoPosition.PositionListener;
import com.cn.net.ApiHandler;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/3/29.
 */
public class SwitchCityFrag extends Fragment {

    private View mainView;
    @Bind(R.id.hot_city_container)
    protected TableLayout hotCityContainer;
    @Bind(R.id.hot_city_abc_container)
    protected TableLayout hotCityAbcContainer;
    @Bind(R.id.all_city_container)
    protected LinearLayout allCityContainer;
    private List<? extends BaseEntity> hotCityList;
    private List<String> hotCityAbcList;
    Map cityMap;
    Map viewMap = new HashMap<String, View>();
    private Handler mHandler;
    CitySelectPage page;
    @Bind(R.id.refresh_city)
    public TextView autoPosition;
    @Bind(R.id.current_city)
    public TextView currentCity;

    AutoPosition autoPositionController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.frag_switch_city, null);
        ButterKnife.bind(this, mainView);
        initData();
        return mainView;
    }


    private void initData() {
        mHandler = new Handler();
        hotCityList = new ArrayList<BaseEntity>();
        hotCityAbcList = new ArrayList<String>();
        initAutoPosition();
        loadData();
    }


    private void setUpHotCity() {
        if (page.getHotCity() == null) {
            return;
        }
        hotCityList = page.getHotCity();
        //热门城市
        //每行显示五个
        int step = 5;
        int size = hotCityList.size();
        for (int i = 0; i < size / step + 1; i++) {
            TableRow row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.hot_city_row, null);
            for (int j = 0; j < step; j++) {
                int position = i * step + j;
                if (position >= size) {
                    break;
                }
                TextView tv = (TextView) row.getChildAt(j);
                tv.setText(hotCityList.get(position).getName());
                tv.setVisibility(View.VISIBLE);
            }
            hotCityContainer.addView(row);
        }
        //显示字典
        hotCityAbcList = setUpAbc();
        int abcSize = hotCityAbcList.size();
        for (int i = 0; i < abcSize / step + 1; i++) {
            TableRow row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.hot_city_row, null);
            for (int j = 0; j < row.getChildCount(); j++) {
                final int position = i * row.getChildCount() + j;
                if (position >= abcSize) {
                    break;
                }
                TextView tv = (TextView) row.getChildAt(j);
                tv.setText(hotCityAbcList.get(position));
                tv.setVisibility(View.VISIBLE);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key=hotCityAbcList.get(position);
                        View container=(View)viewMap.get(key);
                        if (container!=null){
                            float y=container.getTop()+hotCityAbcContainer.getHeight();
                            mainView.scrollTo(0,(int)y);
                        }

                    }
                });

            }
            hotCityAbcContainer.addView(row);
        }
    }

    /**
     * 65 A,90 Z
     * @return
     */
    private List<String> setUpAbc() {
        ArrayList<String> list = new ArrayList();
        for (int i = 65; i < 91; i++) {
            char c = (char) i;
            list.add(String.valueOf(c));
        }
        return list;
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiHandler apiHandler = ApiHandler.getInstance(getActivity());
                apiHandler.getAllCityList(new Response.Listener<CitySelectPage>() {
                    @Override
                    public void onResponse(CitySelectPage response) {
                        page = response;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                setUpUi();
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        UIHelper.showToast(getActivity(),getText(R.string.network_error).toString(), Toast.LENGTH_LONG);
                        if (error!=null){
                            error.printStackTrace();
                        }
                    }
                });
                return;
            }
        }).start();
    }

    private void setUpUi() {
        if (page == null) {
            return;
        }
        setUpHotCity();
        setUpAllCity();

    }
    private void setUpAllCity() {
        if (page.getAbcCityMap() == null) {
            return;
        }
        cityMap=page.getAbcCityMap();
        int position = 0;
        int step=3;
        for (Object str : cityMap.keySet()) {
            ArrayList<BaseEntity> l = (ArrayList<BaseEntity>) cityMap.get(str);

            LinearLayout container = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.all_city_row, null);
            TableLayout tableLayout = (TableLayout) container.findViewById(R.id.abc_city_tablelayout);
            TextView cityIndex = (TextView) container.findViewById(R.id.city_index);
            cityIndex.setText(str.toString());

            allCityContainer.addView(container);
            if (!viewMap.containsKey(str)) {
                viewMap.put(str, container);
            }

            int times=(int)Math.ceil(l.size()/(float)step);
            for (int j = 0; j <times; j++) {
                TableRow row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.all_city_row_item, null);
                for (int m = 0; m < step; m++) {
                    position = j * step + m;
                    if (position >= l.size()) {
                        break;
                    }
                    TextView tv = (TextView) row.getChildAt(m);
                    tv.setText(l.get(position).getName());
                }
                if (row.getChildCount()!=0){
                    tableLayout.addView(row);
                }
                if (position >= l.size()) {
                    break;
                }
            }
        }




    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        autoPositionController.destroy();
    }
    @OnClick(R.id.refresh_city)
    public void autoPosition(View view){
        autoPosition.setText(getText(R.string.auto_position_ing));
        autoPositionController.startPosition();
    }
    private void initAutoPosition(){
        autoPositionController=new AutoPosition(getActivity().getApplicationContext(), new PositionListener() {
            @Override
            public void onPosition(final int code, final String message) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //定位成功
                        if(code==0){
                            currentCity.setText(message);
                        }else{//定位失败
                            UIHelper.showToast(getActivity(),getText(R.string.auto_position_error_please_manual_position).toString(), Toast.LENGTH_LONG);
                        }
                        autoPosition.setText(R.string.auto_position);
                    }
                });
            }
        });
        autoPositionController.startPosition();
    }
}
