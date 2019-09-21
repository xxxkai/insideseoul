package com.example.insideseoul.OpenAPI;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MetroJSONParser extends AsyncTask<Void, Integer, List> {
    // OpenAPI key
    private static final String clientKey = "5a74686b4172656437387247756668";
    // 서울시 주민등록인구 (구별) 통계
    // https://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12914&srvType=S&serviceKind=1&currentPageNo=1&searchValue=&searchKey=null
    private String serviceKey = "CardSubwayStatsNew";
    private static final int endOfStation = 300;
    // 강서구
    private static final int[] gangseo = {120, 121, 123, 124, 125, 126, 127, 128, 276, 277, 278, 279, 280, 281, 282, 283, 285};
    // 광진구
    private static final int[] gangjin = {23, 24, 155, 156, 223, 224, 225, 226, 227};
    // 성동구
    private static final int[] seongdonggu = {17, 19, 20, 21, 54, 55, 75, 76, 148, 150, 151, 152, 159};
    // 중랑구
    private static final int[] gunrangu = {207, 217, 218, 219, 220, 221, 222};
    // 동대문구
    private static final int[] dongdaemongu = {8, 9, 56, 60, 153};
    // 성북구
    private static final int[] seungbukgu = {103, 104, 198, 199, 200, 201, 202, 204};
    // 노원구
    private static final int[] nowongu = {94, 95, 96, 204, 206, 210, 211, 213, 214, 215, 216};
    // 강북구
    private static final int[] gangbukgu = {99, 100, 101};
    // 도봉구
    private static final int[] dobonggu = {97, 98, 209};
    // 용산구
    private static final int[] yangsangu = {1, 114, 115, 187, 188, 189, 190, 191};
    // 중구
    private static final int[] gunggu = {1, 2, 12, 13, 14, 16, 73, 74, 107, 108, 109, 110, 141, 192, 194};
    // 종로구
    private static final int[] gangrogu = {3, 4, 5, 68, 69, 70, 105, 106, 142, 143, 196, 197};
    // 서대문구
    private static final int[] seudaemongu = {50, 51, 52, 53, 66, 67, 68};
    // 마포구
    private static final int[] mapogu = {49, 50, 51, 52, 138, 140, 178, 179, 180, 181, 182, 183, 184, 185, 186};
    // 은평구
    private static final int[] enpangu = {62, 63, 64, 65, 171, 172, 174, 176, 177, 178};
    // 송파구
    private static final int[] seungpagu = {25, 26, 27, 28, 92, 93, 104, 157, 165, 166, 169, 170, 262, 264, 265, 266, 267, 268, 269};
    // 강동구
    private static final int[] gangdongu = {102, 158, 160, 161, 162, 163, 259, 260, 261};
    // 강남구
    private static final int[] gangnamgu = {29, 30, 31, 32, 77, 78, 83, 85, 86, 86, 87, 88, 89, 90, 228, 229, 230, 231, 300};
    // 서초구
    private static final int[] seuchogu = {32, 34, 35, 79, 81, 82, 83, 118, 119, 232, 234, 235, 295, 296, 297, 298, 299};
    // 관악구
    private static final int[] ganacgu = {37, 38, 39, 40, 41, 118, 119};
    // 동작구
    private static final int[] dongjacgu = {41, 117, 118, 235, 236, 237, 238, 239, 240, 292, 293, 295};
    // 금천구
    private static final int[] ganchangu = {245};
    // 영등포구
    private static final int[] yangdongpogu = {42, 45, 46, 47, 132, 134, 135, 137, 241, 242, 287, 289};
    // 구로구
    private static final int[] gurogu = {42, 244, 43, 57, 44, 249, 248};
    // 양천구
    private static final int[] yangchan = {58, 59, 129, 130, 131, 286};
    private static final int seoulGuCnt = 25;

    private static final String [] seoulGuArr = {
            "gangrogu", "gunggu", "yangsangu", "seongdonggu", "gangjin", "dongdaemongu", "gunrangu", "seungbukgu", "gangbukgu",
            "dobonggu", "nowongu", "enpangu", "seudaemongu", "mapogu", "yangchan", "gangseo", "gurogu", "ganchangu", "yangdongpogu",
            "dongjacgu", "ganacgu", "seuchogu", "gangnamgu", "seungpagu", "gangdongu" };

    private String str, receiveMsg;
    private String totalPage = "1";
    List<MetroEntity> metroEntities = new ArrayList<>();

    @Override
    protected List<Integer> doInBackground(Void... params) {
        String txt = "";
        List tmpList = new ArrayList<Integer>();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 4 일전 (최신) | 업데이트 딜레이 시간 고려
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE) - 4);

        String callDate = sdf.format(cal.getTime());
        String path = "http://openapi.seoul.go.kr:8088/" + clientKey + "/json/" + serviceKey + "/1/" + endOfStation + "/" + callDate;
        System.out.println("paks >>>>>>>>>>>> path: " + path);

        txt = getAPI(path);
        metroEntities = multiEntity(txt, serviceKey, "row");

        int gangroguTotal = 0;
        int gungguTotal = 0;
        int yangsanguTotal = 0;
        int seongdongguTotal = 0;
        int gangjinguTotal = 0;
        int dongdaemonguTotal = 0;
        int guTotalnranguTotal = 0;
        int seungbukguTotal = 0;
        int gangbukguTotal = 0;
        int dobongguTotal = 0;
        int nowonguTotal = 0;
        int enpanguTotal = 0;
        int seudaemonguTotal = 0;
        int mapoguTotal = 0;
        int yangchanguTotal = 0;
        int gangseoguTotal = 0;
        int guroguTotal = 0;
        int ganchanguTotal = 0;
        int yangdongpoguTotal = 0;
        int dongjacguTotal = 0;
        int ganacguTotal = 0;
        int seuchoguTotal = 0;
        int gangnamguTotal = 0;
        int seungpaguTotal = 0;
        int gangdonguTotal = 0;

        if(metroEntities != null) {
            try {
                for (int serialnum : gangrogu) { gangroguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gunggu) { gungguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : yangsangu) { yangsanguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : seongdonggu) { seongdongguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gangjin) { gangjinguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : dongdaemongu) { dongdaemonguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gunrangu) { guTotalnranguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : seungbukgu) { seungbukguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gangbukgu) { gangbukguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : dobonggu) { dobongguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : nowongu) { nowonguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : enpangu) { enpanguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : seudaemongu) { seudaemonguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : mapogu) { mapoguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : yangchan) { yangchanguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gangseo) { gangseoguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gurogu) { guroguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : ganchangu) { ganchanguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : yangdongpogu) { yangdongpoguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : dongjacgu) { dongjacguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : ganacgu) { ganacguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : seuchogu) { seuchoguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gangnamgu) { gangnamguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : seungpagu) { seungpaguTotal = metroUsageTotal(metroEntities, serialnum); }
                for (int serialnum : gangdongu) { gangdonguTotal = metroUsageTotal(metroEntities, serialnum); }
            } catch (Exception e) {
                e.printStackTrace();
            }

            tmpList.add(gangroguTotal);
            tmpList.add(gungguTotal);
            tmpList.add(yangsanguTotal);
            tmpList.add(seongdongguTotal);
            tmpList.add(gangjinguTotal);
            tmpList.add(dongdaemonguTotal);
            tmpList.add(guTotalnranguTotal);
            tmpList.add(seungbukguTotal);
            tmpList.add(gangbukguTotal);
            tmpList.add(dobongguTotal);
            tmpList.add(nowonguTotal);
            tmpList.add(enpanguTotal);
            tmpList.add(seudaemonguTotal);
            tmpList.add(mapoguTotal);
            tmpList.add(yangchanguTotal);
            tmpList.add(gangseoguTotal);
            tmpList.add(guroguTotal);
            tmpList.add(ganchanguTotal);
            tmpList.add(yangdongpoguTotal);
            tmpList.add(dongjacguTotal);
            tmpList.add(ganacguTotal);
            tmpList.add(seuchoguTotal);
            tmpList.add(gangnamguTotal);
            tmpList.add(seungpaguTotal);
            tmpList.add(gangdonguTotal);
        } else {
            for(int i = 0; i < seoulGuCnt; i ++) {
                int tmp = 0;
                tmpList.add(tmp);
            }
        }
        return tmpList;
    }

    public int metroUsageTotal(List<MetroEntity> metroEntities, int serialnum) {
        int restult = 0;
        int tmp = serialnum - 1;
        restult = (int) metroEntities.get(tmp).getALIGHT_PASGR_NUM();

        return restult;
    }

    /**
     * api 값 불러오기
     * @param path
     * @return
     */
    public String getAPI(String path) {
        URL url;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            receiveMsg = buffer.toString();
            Log.i("receiveMsg : ", receiveMsg);

            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }

    /**
     * 값 리스트로 받아오기
     * @param json : 전체 json 값
     * @param container : 최상단 row
     * @param findRow : 찾고자 하는 row
     * @return
     */
    public List<MetroEntity> multiEntity(String json, String container, String findRow) {
        JSONArray jsonArr = null;
        List list = new ArrayList<MetroEntity>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject keyObj = (JSONObject) jsonObject.get(container);

            jsonArr = keyObj.getJSONArray(findRow);

            for(int i = 0; i < jsonArr.length(); i ++) {
                JSONObject tmp = (JSONObject) jsonArr.get(i);
                MetroEntity metroEntity = new MetroEntity();

                metroEntity.setUSE_DT((String)tmp.get("USE_DT"));
                metroEntity.setLINE_NUM((String)tmp.get("LINE_NUM"));
                metroEntity.setSUB_STA_NM((String)tmp.get("SUB_STA_NM"));
                metroEntity.setRIDE_PASGR_NUM((Double)tmp.get("RIDE_PASGR_NUM"));
                metroEntity.setALIGHT_PASGR_NUM((Double)tmp.get("ALIGHT_PASGR_NUM"));
                metroEntity.setWORK_DT((String)tmp.get("WORK_DT"));

                list.add(metroEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
