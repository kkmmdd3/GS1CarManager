package kr.ac.kaist.resl.gs1_car_manager;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by nnlee on 2017-09-14.
 */

public class SecondFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView listView;
    IconTextListAdapter adapter;
    DBHelper dbHelper;

    public SecondFragment() {
    }

    public static SecondFragment newInstance(int sectionNumber) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        listView = (ListView)rootView.findViewById(R.id.listView);

        adapter = new IconTextListAdapter(this.getContext());
        Resources res = getResources();

        String _sgtin_vin = "8801470205832:KMHFG41EBCA166142";
        String manufacturer = "Hyundai";
        String model = "Grandeur HG300";
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_mycar_white_24dp), _sgtin_vin, "제조사: " + manufacturer, "모델: " + model));

        String name, sgtin, seller;
        if (FirstFragment.engine_oil_replacing == 1) {
            name = "Kixx 엔진오일 (휘발유)";
            sgtin = "8801470205832";
            manufacturer = "GS칼텍스";
            seller = "(주)카앤후";
        }
        else {
            name = "모비스 엔진오일(가솔린)";
            sgtin = "8807208103807";
            manufacturer = "현대모비스";
            seller = "(주)돌비웨이";
        }
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_icon_engine_oil_white), name + "(" + sgtin + ")", "제조사: " + manufacturer, "판매사: " + seller));

        if (FirstFragment.airconditioner_filter_replacing == 1) {
            name = "불스원 항균 에어컨 히터 필터(1호)";
            sgtin = "8801324207210";
            manufacturer = "불스원";
            seller = "(주)불스원";
        }
        else {
            name = "초이스엘 에어컨 히터 필터 1호";
            sgtin = "8803966361367";
            manufacturer = "훠링";
            seller = "(주)훠링";
        }
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_icon_airconditioner_white), name + "(" + sgtin + ")", "제조사: " + manufacturer, "판매사: " + seller));

        name = "카파워 CP-20 점프스타터";
        //sgtin = "L000002390274";
        sgtin = "8800026910039";
        manufacturer = "인터아이넷";
        seller = "(주)바보사랑";
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_icon_battery_white), name + "(" + sgtin + ")", "제조사: " + manufacturer, "판매사: " + seller));

        name = "금호타이어 245/45/18 MAJESTY";
        //sgtin = "L000002546243";
        sgtin = "8800026910046";
        manufacturer = "금호타이어";
        seller = "롯데오토케어";
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_icon_tire_white), name + "(" + sgtin + ")", "제조사: " + manufacturer, "판매사: " + seller));

        name = "넥센타이어 245/45/18 NFERA AU5";
        //sgtin = "L000002546210";
        sgtin = "8800026910053";
        manufacturer = "넥센타이어";
        seller = "롯데오토케어";
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_icon_tire_white), name + "(" + sgtin + ")", "제조사: " + manufacturer, "판매사: " + seller));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 수정할 부분 : 자세한 정보 띄워주게 하기.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IconTextItem curItem = (IconTextItem) adapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getActivity().getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();
                Log.w("", "포지션은" + position + "\n");
                // To do : 포지션 별 웹뷰 띄워주기.
                // Toast.makeText(getActivity().getApplicationContext(), "갯수 : " + adapter.getCount(), Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();

        if(FirstFragment.refreshingFragment == 1) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            FirstFragment.refreshingFragment = 0;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}