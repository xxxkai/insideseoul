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
import java.util.ArrayList;
import java.util.List;

public class GuJSONParser extends AsyncTask<Void, Integer, List> {
    // OpenAPI key
    private static final String clientKey = "5a74686b4172656437387247756668";
    // 서울시 주민등록인구 (구별) 통계
    // https://data.seoul.go.kr/dataList/datasetView.do?infId=419&srvType=S&serviceKind=2&currentPageNo=1
    private String serviceKey = "octastatapi419";
    private static final int seoulGuCnt = 25;
    private String str, receiveMsg;
    private String totalPage = "1";
    List<GuEntity> guEntityList = new ArrayList<>();

    @Override
    protected List<Integer> doInBackground(Void... params) {
        String txt;
        String path = "http://openapi.seoul.go.kr:8088/" + clientKey + "/json/" + serviceKey + "/";
        List tmpList = new ArrayList<Integer>();

        txt = getAPI(path, totalPage, totalPage);

        if(txt.length() == 0) {
            Log.i("데이터가 존재하지 않습니다.", txt);
        } else {
            initEntity(txt, serviceKey, "list_total_count");

            if (totalPage != "1") {
                int cnt = Integer.parseInt(totalPage) - seoulGuCnt;
                txt = getAPI(path, Integer.toString(cnt), totalPage);

                guEntityList = multiEntity(txt, serviceKey, "row");

                try {
                    for(int i = 0; i < seoulGuCnt; i ++) {
                        int result = 0;
                        result = (int) guEntityList.get(i).getGYE_1();
                        tmpList.add(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // 에러시 더미 데이터 입력
                    for(int i = 0; i < seoulGuCnt; i ++) {
                        int result = 0;
                        tmpList.add(result);
                    }
                }
            } else {
                Log.i("데이터가 존재하지 않습니다2.", txt);
            }
        }
        return tmpList;
    }

    /**
     * api 값 불러오기
     * @param path
     * @param start
     * @param end
     * @return
     */
    public String getAPI(String path, String start, String end) {
        URL url;
        try {
            path += start + "/" + end;
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
     * 초기 값 가져오기 (list_total_count: 전체 값 불러오기)
     * @param json : 전체 json 값
     * @param container : 최상단 row
     * @param findRow : 찾고자 하는 row
     */
    public void initEntity(String json, String container, String findRow) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject keyObj = (JSONObject) jsonObject.get(container);
            totalPage = String.valueOf(keyObj.get(findRow));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 값 리스트로 받아오기
     * @param json : 전체 json 값
     * @param container : 최상단 row
     * @param findRow : 찾고자 하는 row
     * @return
     */
    public List<GuEntity> multiEntity(String json, String container, String findRow) {
        JSONArray jsonArr = null;
        List list = new ArrayList<GuEntity>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject keyObj = (JSONObject) jsonObject.get(container);

            jsonArr = keyObj.getJSONArray(findRow);

            for(int i = 0; i < jsonArr.length(); i ++) {
                JSONObject tmp = (JSONObject) jsonArr.get(i);
                GuEntity tmpEntity = new GuEntity();

                tmpEntity.setGIGAN((String)tmp.get("GIGAN"));
                tmpEntity.setJACHIGU((String)tmp.get("JACHIGU"));
                tmpEntity.setSEDAE(Integer.parseInt((String)tmp.get("SEDAE")));
                tmpEntity.setGYE_1(Integer.parseInt((String)tmp.get("GYE_1")));
                tmpEntity.setNAMJA_1(Integer.parseInt((String)tmp.get("NAMJA_1")));
                tmpEntity.setYEOJA_1(Integer.parseInt((String)tmp.get("YEOJA_1")));
                tmpEntity.setGYE_2(Integer.parseInt((String)tmp.get("GYE_2")));
                tmpEntity.setNAMJA_2(Integer.parseInt((String)tmp.get("NAMJA_2")));
                tmpEntity.setYEOJA_2(Integer.parseInt((String)tmp.get("YEOJA_2")));
                tmpEntity.setGYE_3(Integer.parseInt((String)tmp.get("GYE_3")));
                tmpEntity.setNAMJA_3(Integer.parseInt((String)tmp.get("NAMJA_3")));
                tmpEntity.setYEOJA_3(Integer.parseInt((String)tmp.get("YEOJA_3")));
                tmpEntity.setSEDAEDANGINGU((String)tmp.get("SEDAEDANGINGU"));
                tmpEntity.setN_65SEISANGGORYEONGJA(Integer.parseInt((String)tmp.get("N_65SEISANGGORYEONGJA")));

                list.add(tmpEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
