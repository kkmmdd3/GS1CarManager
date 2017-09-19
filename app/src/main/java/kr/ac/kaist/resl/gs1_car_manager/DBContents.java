package kr.ac.kaist.resl.gs1_car_manager;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

/**
 * Created by nnlee on 2017-09-14.
 */

public class DBContents{
    public void fillDB_car_info(DBHelper dbHelper) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);

        String name = "cherie";
        String _sgtin_vin = "8800026910115:KMHFG41EBCA166142";
        String model = "Hyundai Grandeur HG";
        String current_milege = "75020";
        String engine_oil = "8807208103807";
        String airconditioner_filter = "8801324207210";
        String battery = "L000002390274";
        String tires_front = "L000002546243";
        String tires_rear = "L000002546210";
        String create_at = dateString;

        dbHelper.insert_car_info(name, _sgtin_vin, model, current_milege, engine_oil, airconditioner_filter, battery, tires_front, tires_rear, create_at);
    }

    public void fillDB_repair_shop(DBHelper dbHelper) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);

        String name = "나래모터스(주)";
        String gln = "2125783767020";
        String address = "서울 강남구 개포로 258";
        String phone_number = "02-529-1052";
        String gps = "gps_test";
        String create_at = dateString;

        dbHelper.insert_repair_shop(name, gln, address, phone_number, gps, create_at);

        name = "둔산자동차공업사";
        gln = "4349572948041";
        address = "대전 서구 대덕대로 185번길 13";
        phone_number = "042-472-8573";
        gps = "gps_test";
        create_at = dateString;

        dbHelper.insert_repair_shop(name, gln, address, phone_number, gps, create_at);

        name = "해운대종합정비(주)";
        gln = "5058396165051";
        address = "부산 해운대로 1101, 4";
        phone_number = "051-703-7009";
        gps = "gps_test";
        create_at = dateString;

        dbHelper.insert_repair_shop(name, gln, address, phone_number, gps, create_at);
    }

    public void fillDB_product(DBHelper dbHelper) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);

        String name = "Kixx 엔진오일(휘발유)";
        String category = "engineOil";
        String sgtin = "8801470205832";
        String manufacturer = "GS칼텍스";
        String seller = "(주)카앤후";
        String seller_address = "경기 하남시 동남로 424번길 79-24";
        String seller_call = "02-448-1293";
        String seller_website = "www.e-mando.co.kr";
        String create_at = dateString;

        dbHelper.insert_product(name, category, sgtin, manufacturer, seller, seller_address, seller_call, seller_website, create_at);

        name = "모비스 엔진오일(가솔린)";
        category = "engineOil";
        sgtin = "8807208103807";
        manufacturer = "현대모비스";
        seller = "(주)돌비웨이";
        seller_address = "경기 광주시 삼지곡길 52-5";
        seller_call = "031-761-1020";
        seller_website = "www.dolbiway.co.kr";
        create_at = dateString;

        dbHelper.insert_product(name, category, sgtin, manufacturer, seller, seller_address, seller_call, seller_website, create_at);

        name = "불스원 항균 에어컨 히터 필터(1호)";
        category = "airConditionerFilter";
        sgtin = "8801324207210";
        manufacturer = "불스원";
        seller = "(주)불스원";
        seller_address = "서울특별시 강남구 테헤란로 418";
        seller_call = "02-2106-7865";
        seller_website = "http://bullsone.com";
        create_at = dateString;

        dbHelper.insert_product(name, category, sgtin, manufacturer, seller, seller_address, seller_call, seller_website, create_at);

        name = "카파워 CP-20 점프스타터";
        category = "battery";
        sgtin = "L000002390274";
        manufacturer = "(주)인터아이넷";
        seller = "(주)바보사랑";
        seller_address = "서울 강남구 도산대로 159-7";
        seller_call = "1544-7708";
        seller_website = "http://www.babosarang.co.kr";
        create_at = dateString;

        dbHelper.insert_product(name, category, sgtin, manufacturer, seller, seller_address, seller_call, seller_website, create_at);

        name = "금호타이어 245/45/18 MAJESTY";
        category = "tire";
        sgtin = "L000002546243";
        manufacturer = "금호타이어";
        seller = "롯데오토케어";
        seller_address = "서울 영등포구 문래로 83";
        seller_call = "1899-1699";
        seller_website = "-";
        create_at = dateString;

        dbHelper.insert_product(name, category, sgtin, manufacturer, seller, seller_address, seller_call, seller_website, create_at);

        name = "넥센타이어 245/45/18 NFERA AU5";
        category = "tire";
        sgtin = "L000002546210";
        manufacturer = "넥센타이어";
        seller = "롯데오토케어";
        seller_address = "서울 영등포구 문래로 83";
        seller_call = "1899-1699";
        seller_website = "-";
        create_at = dateString;

        dbHelper.insert_product(name, category, sgtin, manufacturer, seller, seller_address, seller_call, seller_website, create_at);
    }
}
