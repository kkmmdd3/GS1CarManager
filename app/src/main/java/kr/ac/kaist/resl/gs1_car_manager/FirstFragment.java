package kr.ac.kaist.resl.gs1_car_manager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import static kr.ac.kaist.resl.gs1_car_manager.R.id.imageButton_carRepairShop;

/**
 * Created by nnlee on 2017-09-14.
 */

public class FirstFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    int selected = 999;
    String contents;
    String formatName;
    View view_imageButton_carRepairShop;
    View view_imageButton_engineOil, view_imageButton_airConditioner, view_imageButton_battery, view_imageButton_tire;
    String gln = "8801111111119"; // my home이 gln 초기값(자가 수리 대비)

    public FirstFragment() {
    }

    public static FirstFragment newInstance(int sectionNumber) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        view_imageButton_carRepairShop = rootView.findViewById(imageButton_carRepairShop);
        view_imageButton_engineOil = rootView.findViewById(R.id.imageButton_engineOil);
        view_imageButton_airConditioner = rootView.findViewById(R.id.imageButton_airConditioner);
        view_imageButton_battery = rootView.findViewById(R.id.imageButton_battery);
        view_imageButton_tire = rootView.findViewById(R.id.imageButton_tire);

        view_imageButton_carRepairShop.setOnClickListener(mOnClickListener_shop);
        view_imageButton_engineOil.setOnClickListener(mOnClickListener_engineOil);
        view_imageButton_airConditioner.setOnClickListener(mOnClickListener_airConditioner);
        view_imageButton_battery.setOnClickListener(mOnClickListener_battery);
        view_imageButton_tire.setOnClickListener(mOnClickListener_tire);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", ">>> requestCode = " + requestCode + ", resultCode = " + resultCode);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            Log.i("TAG", ">>> result.getContents()   :  " + result.getContents());
            contents = result.getContents();
            Log.i("TAG", ">>> result.getFormatName()   :  " + result.getFormatName());
            formatName = result.getFormatName();

            if(contents != null) {
                switch(selected){
                    case 0:
                        gln = contents;
                        if(contents.equals("2125783767020")) {
                            Snackbar.make(getView(), "나래모터스(주), 서울 강남구 개포로 258, 02-529-1052", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else if(contents.equals("4349572948041")) {
                            Snackbar.make(getView(), "둔산자동차공업사, 대전 서구 대덕대로 185번길 13, 042-472-8573", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else if(contents.equals("5058396165051")) {
                        Snackbar.make(getView(), "해운대종합정비(주), 부산 해운대로 1101, 4, 051-703-7009", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        }
                        else {
                            Snackbar.make(getView(), "정비소의 바코드(gln)은 " + contents + "입니다.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        break;
                    case 1:
                        if (contents.equals("8801470205832")) {
                            Snackbar.make(getView(), "Kixx 엔진오일 (휘발유), 제조사: GS칼텍스, 판매사: (주)카앤후(경기 하남시 동남로 424번길 79-24, 02-448-1293)", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            SecondFragment.refreshingFragment = 1;
                        }
                        else {
                            Snackbar.make(getView(), "엔진오일의 바코드(sgtin)은 " + contents + "입니다.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        // TO DO : epcis 문서 만들어서 서버에 보내기.
                        // TO DO : db 업데이트하고 Fragment refresh!
                        long time = System.currentTimeMillis();
                        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat dayTime2 = new SimpleDateFormat("hh:mm:ss");
                        String eventTime = dayTime.format(new Date(time)) + "T" + dayTime2.format(new Date(time));
                        String eventTimeZoneOffset = "+03:00";

                        String action = "OBSERVE";
                        String bizStep = "urn:epcglobal:cbv:bizstep:replacing";
                        String disposition = "";
                        String vin = "KMHFG41EBCA166142";
                        String sgtin = contents;

                        String xml_doc = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<!DOCTYPE project>\n" +
                                "<epcis:EPCISDocument xmlns:epcis=\"urn:epcglobal:epcis:xsd:1\"\n" +
                                "\txmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                                "\txmlns:car=\"urn:autoidlabsk:epcisapp:car\" creationDate=\"" + eventTime + ".720-04:00\"\n" +
                                "\tschemaVersion=\"1.2\">\n" +
                                "\t<EPCISBody>\n" +
                                "\t\t<EventList>\n" +
                                "\t\t\t<ObjectEvent>\n" +
                                "\t\t\t\t<eventTime>" + eventTime + "</eventTime>\n" +
                                "\t\t\t\t<eventTimeZoneOffset>"+ eventTimeZoneOffset + "</eventTimeZoneOffset>\n" +
                                "\t\t\t\t<epcList>\n" +
                                "\t\t\t\t\t<epc>urn:epc:id:sgtin:"+ contents + "</epc>\n" +
                                "\t\t\t\t</epcList>\n" +
                                "\t\t\t\t<action>" + action + "</action>\n" +
                                "\t\t\t\t<bizStep>" + bizStep + "</bizStep>\n" +
                                "\t\t\t\t<disposition>" + disposition + "</disposition>\n" +
                                "\t\t\t\t<readPoint>\n" +
                                "\t\t\t\t\t<id>urn:epc:id:gln:" + gln + "</id>\n" +
                                "\t\t\t\t</readPoint>\n" +
                                "\t\t\t\t<bizLocation>\n" +
                                "\t\t\t\t\t<id>urn:epc:id:gln:" + gln + "</id>\n" +
                                "\t\t\t\t</bizLocation>\n" +
                                "\t\t\t\t<extension>\n" +
                                "\t\t\t\t</extension>\n" +
                                "\t\t\t\t<car:vin xmlns:car=\"urn:autoidlabsk:epcisapp:car\">" + vin + "</car:vin>\n" +
                                "\t\t\t\t<car:engine_oil xmlns:car=\"urn:autoidlabsk:epcisapp:car\">" + sgtin + "</car:engine_oil>\n" +
                                "\t\t\t</ObjectEvent>\n" +
                                "\t\t</EventList>\n" +
                                "\t</EPCISBody>\n" +
                                "</epcis:EPCISDocument>";

                        new UploadAsyncTask().execute(xml_doc);
                        Snackbar.make(getView(), "EPCIS에 Engine Oil Replacing 이벤트가 안전하게 저장되었습니다.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 2:
                        Snackbar.make(getView(), "에어컨필터의 바코드(sgtin)은 " + contents + "입니다.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 3:
                        Snackbar.make(getView(), "배터리의 바코드(sgtin)은 " + contents + "입니다.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 4:
                        Snackbar.make(getView(), "타이어의 바코드(sgtin)은 " + contents + "입니다.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                }
            }
            else
                Snackbar.make(getView(), "인식이 어렵습니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
    }

    final View.OnClickListener mOnClickListener_shop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = 0;
            Snackbar.make(v, "정비소의 바코드(gln)를 스캔해주십시오.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    IntentIntegrator integratorForCarRepairShop = new IntentIntegrator(getActivity());
                    integratorForCarRepairShop.setCaptureActivity(AdjustedCaptureActivity.class);
                    integratorForCarRepairShop.setOrientationLocked(false);
                    IntentIntegrator.forSupportFragment(FirstFragment.this).initiateScan();
                }
            }, 500);
        }
    };

    final View.OnClickListener mOnClickListener_engineOil = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = 1;
            Snackbar.make(v, "엔진오일의 바코드(sgtin)를 스캔해주십시오.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    IntentIntegrator integratorForCarRepairShop = new IntentIntegrator(getActivity());
                    integratorForCarRepairShop.setCaptureActivity(AdjustedCaptureActivity.class);
                    integratorForCarRepairShop.setOrientationLocked(false);
                    IntentIntegrator.forSupportFragment(FirstFragment.this).initiateScan();
                }
            }, 500);
        }
    };

    final View.OnClickListener mOnClickListener_airConditioner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = 2;
            Snackbar.make(v, "에어컨필터의 바코드(sgtin)를 스캔해주십시오.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    IntentIntegrator integratorForCarRepairShop = new IntentIntegrator(getActivity());
                    integratorForCarRepairShop.setCaptureActivity(AdjustedCaptureActivity.class);
                    integratorForCarRepairShop.setOrientationLocked(false);
                    IntentIntegrator.forSupportFragment(FirstFragment.this).initiateScan();
                }
            }, 500);
        }
    };

    final View.OnClickListener mOnClickListener_battery = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = 3;
            Snackbar.make(v, "배터리의 바코드(sgtin)를 스캔해주십시오.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    IntentIntegrator integratorForCarRepairShop = new IntentIntegrator(getActivity());
                    integratorForCarRepairShop.setCaptureActivity(AdjustedCaptureActivity.class);
                    integratorForCarRepairShop.setOrientationLocked(false);
                    IntentIntegrator.forSupportFragment(FirstFragment.this).initiateScan();
                }
            }, 500);
        }
    };

    final View.OnClickListener mOnClickListener_tire = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selected = 4;
            Snackbar.make(v, "타이어의 바코드(sgtin)를 스캔해주십시오.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    IntentIntegrator integratorForCarRepairShop = new IntentIntegrator(getActivity());
                    integratorForCarRepairShop.setCaptureActivity(AdjustedCaptureActivity.class);
                    integratorForCarRepairShop.setOrientationLocked(false);
                    IntentIntegrator.forSupportFragment(FirstFragment.this).initiateScan();
                }
            }, 500);
        }
    };
}