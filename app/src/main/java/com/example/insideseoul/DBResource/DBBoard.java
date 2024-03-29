package com.example.insideseoul.DBResource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.insideseoul.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DBBoard extends SQLiteOpenHelper {
    Context context;
    MainActivity mainActivity;
    String tb_name;
    int version;
    SQLiteDatabase.CursorFactory factory;
    String[] seoulGuArr = {"gangrogu", "gunggu", "yangsangu", "seongdonggu", "gangjin", "dongdaemongu", "gunrangu", "seungbukgu", "gangbukgu", "dobonggu", "nowongu", "enpangu", "seudaemongu", "mapogu", "yangchan", "gangseo", "gurogu", "ganchangu", "yangdongpogu", "dongjacgu", "ganacgu", "seuchogu", "gangnamgu", "seungpagu", "gangdongu"};

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DBBoard(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, int init) {
        super(context, name, factory, version);
        this.context = context;
        tb_name = name;
        this.version = version;
        this.factory = factory;

        SQLiteDatabase db = getWritableDatabase();
        if(init == 1) {
            initInsert(db);
        }
    }

    //시작 id부터 일정 갯수의 데이터를 json array로 얻어온다
    public JSONArray getData(int cnt, String guType) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + tb_name + " WHERE gu_type = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(guType)});
        JSONArray resultSet = new JSONArray();
        cursor.moveToPosition(0);
        int index = 0;
        while (index < cnt) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            resultSet.put(rowObject);
            cursor.moveToNext();
            index = index + 1;
        }

        cursor.close();
        return resultSet;
    }

    // 해당구의 한 데이터 호출
    public JSONObject getOneData(int idx) {
        Log.i("guType", String.valueOf(idx));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tb_name + " WHERE idx = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idx)});
        cursor.moveToPosition(0);

        int totalColumn = cursor.getColumnCount();

        System.out.println("getData from Board: " + totalColumn);
        JSONObject rowObject = new JSONObject();

        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null) {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } else {
                        rowObject.put(cursor.getColumnName(i), "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cursor.close();
        db.close();
        return rowObject;
    }

    // 해당구의 한 데이터 호출
    public JSONObject getOneDataByGu(String guType) {
        Log.i("guType", String.valueOf(guType));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tb_name + " WHERE gu_type = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(guType)});
        cursor.moveToPosition(0);

        int totalColumn = cursor.getColumnCount();

        System.out.println("getData from Board: " + totalColumn);
        JSONObject rowObject = new JSONObject();

        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null) {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } else {
                        rowObject.put(cursor.getColumnName(i), "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cursor.close();
        db.close();
        return rowObject;
    }

    // 해당구의 데이터 총량 불러오기
    public int getDataCnt(String guType) {
        Log.i("guType", String.valueOf(guType));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(*) AS sum FROM " + tb_name + " WHERE gu_type = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(guType)});
        cursor.moveToPosition(0);

        int totalColumn = cursor.getColumnCount();
        JSONObject rowObject = new JSONObject();

        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null) {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } else {
                        rowObject.put(cursor.getColumnName(i), "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cursor.close();
        db.close();

        int result = 0;
        try {
            System.out.println(rowObject);
            String tmp_sum = (String)rowObject.get("sum");
            if(tmp_sum != "") result = Integer.parseInt((String)rowObject.get("sum"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    int totalBoardCnt = 39;

    String[] subject = new String[totalBoardCnt];      // 행사 타이틀
    String[] intro_text = new String[totalBoardCnt];    // 소개글
    String[] start_date = new String[totalBoardCnt];    // 시작 일
    String[] end_date = new String[totalBoardCnt];      // 종료 일
    String[] a_link = new String[totalBoardCnt];        // 링크 추가
    String[] event_location = new String[totalBoardCnt];        // 링크 추가
    String[] image1 = new String[totalBoardCnt];        // 이미지1 추가
    String[] image2 = new String[totalBoardCnt];        // 이미지2 추가
    String[] gu = new String[totalBoardCnt];        // 이미지2 추가
    StringBuffer stringBuffer = new StringBuffer();

    // 설정 초기값 입력
    public void initSettingValue() {
        subject[0] = "공기정화식물 키우기 교육";
        start_date[0] = "2019-09-26 09:00";
        end_date[0] = "2019-10-09 12:00";
        a_link[0] = "https://www.jongno.go.kr/portal/app/integrateApp/view.do?id=375&menuNo=388366";
        intro_text[0] = "나날이 늘어가는 미세먼지에 대한 고민! 그저 손놓고 있을 수만은 없습니다. 그래서 이에 대한 해결책으로 종로구에서는 실내 미세먼지 제거 및 주민들 건강관리를 위한 공기정화식물 키우기 교육을 마련하였습니다.\n" +
                "이번 교육은 전문강사와 함께 공기정화식물을 잘 가꾸는 방법에 대한 이론을 배우고, 공기정화식물을 직접 분갈이 해보는 실습도 함께 진행되므로이번 교육에 많이 참여하셔서 몸과 마음의 건강을 모두 챙겨볼 수 있는 시간을 가져보세요.";
        event_location[0] = "짚풀생활사박물관 외 회별 탐방 박물관·문학관";
        image1[0] = "";
        image2[0] = "";
        gu[0] = "GU00";

        subject[1] = "2019 종로 박물관ㆍ미술관 인문학 탐방 - 4권역";
        start_date[1] = "2019-09-03 10:00";
        end_date[1] = "2019-10-04 10:00";
        a_link[1] = "https://www.jongno.go.kr/portal/app/integrateApp/view.do?id=373&menuNo=388366";
        intro_text[1] = "짚풀생활사박물관 외 회별 탐방 박물관·문학관";
        event_location[1] = "짚풀생활사박물관 외 탐방기관";
        image1[1] = "";
        image2[1] = "";
        gu[1] = "GU00";

        subject[2] = "2019년 중구민 아이디어 공모전 개최 안내";
        start_date[2] = "2019-10-01";
        end_date[2] = "2019-10-03";
        a_link[2] = "http://www.junggu.seoul.kr/content.do?cmsid=11329&mode=view&cid=902874807";
        intro_text[2] = "2019년 중구민 아이디어 공모전 개최 안내";
        event_location[2] = "서울 중구 창경궁로 17 기획조정과 제안담당자 앞";
        image1[2] = "";
        image2[2] = "";
        gu[2] = "GU01";

        subject[3] = "심폐소생술 및 자동심장충격기 사용 교육 안내";
        start_date[3] = "2019.09.24 10:00";
        end_date[3] = "2019.09.24 10:00";
        a_link[3] = "http://www.yongsan.go.kr/pms/board/detail.do?boardidn=83&boardseqn=1730&sitecdv=S0000100&decorator=pmsweb&menucdv=04030300";
        intro_text[3] = "용산구보건소 심폐소생술 및 자동심장충격기(AED) 교육 안내";
        event_location[3] = "용산구보건소 지하1층 건강교육실";
        image1[3] = "";
        image2[3] = "";
        gu[3] = "GU02";

        subject[4] = "[성동문화주간] 2019 태조이성계축제 개최";
        start_date[4] = "2019.09.28";
        end_date[4] = "2019.09.28";
        a_link[4] = "http://www.sd.go.kr/sd/main.do?op=view&boardSeq=11708&groupSeq=46&mCode=13E010010000&lop=&preViewNum=2";
        intro_text[4] = "<태조 이성계 축제>";
        event_location[4] = "살곶이다리 등";
        image1[4] = "GU03.jpg";
        image2[4] = "";
        gu[4] = "GU03";

        subject[5] = "하반기(9~10월) 청춘뜨락 공연";
        start_date[5] = "2019.09.07";
        end_date[5] = "2019.10.26";
        a_link[5] = "https://www.gwangjin.go.kr/portal/bbs/B0000045/view.do?nttId=539610&menuNo=201076&pageIndex=1";
        intro_text[5] = "하반기(9~10월) 청춘뜨락 공연";
        event_location[5] = "건대역 2번출구 청춘뜨락";
        image1[5] = "GU04.jpg";
        image2[5] = "";
        gu[5] = "GU04";

        subject[6] = "제25회 서울약령시 한방문화축제 개최 안내";
        start_date[6] = "2019.10.11";
        end_date[6] = "2019.10.12";
        a_link[6] = "http://www.ddm.go.kr/ddm/cultureBBSview.jsp?pid=61881&searchParam1=&searchParam2=&pageNo=1&blockNo=0";
        intro_text[6] = "(사)서울약령시협회에서는 우리민족의 자랑이자 문화유산인 한방의 우수성과 안전성을 널리 홍보하고 대한민국 한의약의 메카인 서울약령시의 발전을 도모하고자 「제25회 서울약령시 한방문화축제」를 아래와 같이 개최하오니 많은 관심과 참여바랍니다.";
        event_location[6] = "서울한방진흥센터 및 약령중앙로 일대";
        image1[6] = "GU05.jpg";
        image2[6] = "";
        gu[6] = "GU05";

        subject[7] = "2019중랑북페스티벌 개최 안내";
        start_date[7] = "2019.10.19 11:00";
        end_date[7] = "2019.10.19 18:00";
        a_link[7] = "http://www.jungnang.go.kr/portal/bbs/view/B0000002/27956.do?menuNo=200473";
        intro_text[7] = "- 가족과 함께 즐기는 마술공연\n - 빡독(빡세게 독서하기)대회 사전신청 : 중랑구립정보도서관 홈페이지 \n- 독서문화행사 신청 \n- 어린이와 함께 하는 독서 골든벨 \n- 채사장 저자강연회";
        event_location[7] = "양원숲속도서관 인근 잔디광장";
        image1[7] = "GU06.jpg";
        image2[7] = "";
        gu[7] = "GU06";

        subject[8] = "제100회 서울시 전국체전 및 제39회 장애인체전 성북구 성화봉송 축하행사";
        start_date[8] = "2019.09.30 11:00";
        end_date[8] = "2019.09.30 12:00";
        a_link[8] = "http://www.sb.go.kr/main/cop/bbs/selectBoardArticle.do?bbsId=B0316_main&nttId=9458901&menuNo=07000000&subMenuNo=07070000&thirdMenuNo=07070100&fourthMenuNo=";
        intro_text[8] = "성화봉송 맞이 행사 및 성화봉송 인계";
        event_location[8] = "한성대 입구역~성북구청R~대광고R";
        image1[8] = "GU07.jpg";
        image2[8] = "";
        gu[8] = "GU07";

        subject[9] = "2019 세계과학도시연합(WTA) 국제행사」 참가 안내";
        start_date[9] = "2019.10.21";
        end_date[9] = "2019.10.23";
        a_link[9] = "http://www.gangbuk.go.kr/www/boardView.do?key=297&boardSeq=49&post=804540";
        intro_text[9] = "- 세계혁신포럼 : 지속가능한 스마트시티를 위하여\n - HI-TECh FAIR : 4차 산업혁명과 도시의 미래\n - UNESCO-WTA 국제공동워크숍 : 스마트시티 관점에서의 과학시술단지 발전";
        event_location[9] = "대전컨벤션센터(DCC)";
        image1[9] = "GU08.jpg";
        image2[9] = "";
        gu[9] = "GU08";

        subject[10] = "‘도봉산에 스며들다’ 2019 도봉산페스티벌 개최";
        start_date[10] = "2019.09.28";
        end_date[10] = "2019.09.29";
        a_link[10] = "http://www.dobong.go.kr/bbs.asp?bmode=D&pCode=12678666&code=10004132";
        intro_text[10] = "9월28~29일 도봉산 일대···‘도봉산에 스며들다’ 주제로 ‘2019 도봉산페스티벌\n 개최 - 9월27일 도봉사에서 전야제 행사로 ‘숲속별밤음악회’, ‘도봉산 야간산책’진행 \n- 템플스테이, 인공암벽체험, 자연해설프로그램, 도봉산복토 캠페인 등 진행 \n- 재즈&레게 파티, 청춘마이크, 프린지, 버스팅 등 볼거리 즐길거리 풍성";
        event_location[10] = "도봉산 일대";
        image1[10] = "";
        image2[10] = "";
        gu[10] = "GU09";

        subject[11] = "2019 제9회 어울림 합창제 참가팀 신청 안내";
        start_date[11] = "2019.10.04 18:30";
        end_date[11] = "2019.10.04 20:00";
        a_link[11] = "http://www.nowon.kr/new/tour/tour.jsp?idx=86634&process=view&mid=560101&pagenum=1&search=&search_str=&title=%C7%E0%BB%E7%BE%C8%B3%BB";
        intro_text[11] = "어울림 합창제";
        event_location[11] = "노원문화예술회관 대공연장";
        image1[11] = "";
        image2[11] = "";
        gu[11] = "GU10";

        subject[12] = "은평구 사회적경제 어울림한마당 행사 개최";
        start_date[12] = "2019.10.04 13:00";
        end_date[12] = "2019.10.05 20:00";
        a_link[12] = "https://www.ep.go.kr/CmsWeb/viewPage.req?idx=PG0000001131&boardId=BO0000000087&CP0000000002_BO0000000087_Action=boardView&CP0000000002_BO0000000087_ViewName=board/BoardView&boardDataId=206935";
        intro_text[12] = "은평구 '사회적경제 어울림한마당 행사'";
        event_location[12] = "연신내 물빛공원";
        image1[12] = "GU11.jpg";
        image2[12] = "";
        gu[12] = "GU11";

        subject[13] = "[무료 강연]인공위성을 만드는 과학자를 만날 수 있는 기회";
        start_date[13] = "2019.10.11 19:00";
        end_date[13] = "2019.10.11 20:00";
        a_link[13] = "http://www.sdm.go.kr/news/news/notice.do";
        intro_text[13] = "우주날씨 이야기";
        event_location[13] = "서대문자연사박물관 1층 시청각실";
        image1[13] = "GU12.jpg";
        image2[13] = "";
        gu[13] = "GU12";

        subject[14] = "2019년도 제5회 마포구청소년자원봉사대회";
        start_date[14] = "2019.11.09 15:00";
        end_date[14] = "2019.11.09 15:00";
        a_link[14] = "https://www.mapo.go.kr/site/main/board/culturevent/229742?cp=1&sortOrder=BA_REGDATE&sortDirection=DESC&listType=list&bcId=culturevent&baNotice=false&baCommSelec=false&baOpenDay=false&baUse=true";
        intro_text[14] = "마포구청소년자원봉사대회";
        event_location[14] = "마포중안도서관 6층 세미나실";
        image1[14] = "GU13.jpg";
        image2[14] = "";
        gu[14] = "GU13";

        subject[15] = "제100회 전국체전 및 제39회 장애인체전 성화봉송 릴레이 등 안내";
        start_date[15] = "2019.10.02 11:00";
        end_date[15] = "2019.10.02 15:00";
        a_link[15] = "http://www.yangcheon.go.kr/site/yangcheon/ex/bbs/View.do?cbIdx=254&bcIdx=221271&parentSeq=221271";
        intro_text[15] = "성화봉송 축하행사 및 출발행사";
        event_location[15] = "신트리공원(신정1동 310-7)";
        image1[15] = "GU14.jpg";
        image2[15] = "";
        gu[15] = "GU14";

        subject[16] = "2019 강서 마을박람회 개최";
        start_date[16] = "2019.10.05 11:00";
        end_date[16] = "2019.10.05 16:00";
        a_link[16] = "http://www.gangseo.seoul.kr/new_portal/announce/main_1_view.jsp?board_id=5&list_id=9007&page=1&s_key=&s_gubun=null&show=null&rboard_id=5";
        intro_text[16] = "2019 강서 마을박람회 개최";
        event_location[16] = "김포공항 롯데몰 1층 잔디광장 일대";
        image1[16] = "GU15.jpg";
        image2[16] = "";
        gu[16] = "GU15";

        subject[17] = "제96회 안양천사랑 가족건강 걷기대회 개최 안내";
        start_date[17] = "2019.10.06 07:00";
        end_date[17] = "2019.10.06 09:00";
        a_link[17] = "https://www.guro.go.kr/www/ntc/NR_view.do?ntcSeq=16071";
        intro_text[17] = "걷기행사, 경품추첨, 에너지절약 캠페인, 안양천 정화활동, 에코마일리지제 가입 등";
        event_location[17] = "안양천 고척교옆 A축구장";
        image1[17] = "";
        image2[17] = "";
        gu[17] = "GU16";

        subject[18] = "2019년 정조대왕 능행차 공동재현 행사 개최";
        start_date[18] = "2019.10.05 09:15";
        end_date[18] = "2019.10.05 18:00";
        a_link[18] = "http://www.geumcheon.go.kr/program/board/detail.jsp?boardTypeID=78&searchSelect=BOARDTITLE&menuID=001006006001%C2%A4tPage=1&boardLines=10&boardID=131100";
        intro_text[18] = "의궤복원(행렬, 격쟁), 주요행사(상설무대 전통공연, 체험프로그램), 시민퍼레이드 등";
        event_location[18] = "은행나무로 34(대우당약국) 및 시흥행궁 일대";
        image1[18] = "";
        image2[18] = "";
        gu[18] = "GU17";

        subject[19] = "제24회 구민의 날 행사 안내";
        start_date[19] = "2019.09.28 16:00";
        end_date[19] = "2019.09.28 18:00";
        a_link[19] = "http://www.ydp.go.kr/main/board/bbs.do?cfgIdx=211&mCode=F040140000&op=view&idxId=234343";
        intro_text[19] = "제24회 『영등포 구민의 날』을 맞이하여 영등포 구민 모두가 다같이 경축하고 기념하고자 다음과 같이 행사를 개최합니다.";
        event_location[19] = "영등포 아트홀 대강당 및 2층 전시실";
        image1[19] = "GU18.jpg";
        image2[19] = "";
        gu[19] = "GU18";

        subject[20] = "제7회 도심속 바다축제";
        start_date[20] = "2019.10.12 11:00";
        end_date[20] = "2019.10.13 18:00";
        a_link[20] = "https://www.dof.or.kr/";
        intro_text[20] = "제7회 도심 속 바다축제";
        event_location[20] = "노량진수산시장 일대";
        image1[20] = "";
        image2[20] = "";
        gu[20] = "GU19";

        subject[21] = "관악 강감찬 축제";
        start_date[21] = "2019.10.17";
        end_date[21] = "2019.10.20";
        a_link[21] = "http://www.gwanak.go.kr/html/gang/sub/sub.html";
        intro_text[21] = "역사를 품은 강감찬도시 관악에서 강감찬 장군과 고려의 역사문화를 만나보세요!";
        event_location[21] = "관악구";
        image1[21] = "GU20.jpg";
        image2[21] = "";
        gu[21] = "GU20";

        subject[22] = "서리풀 페스티벌";
        start_date[22] = "2019.09.21 17:40";
        end_date[22] = "2019.09.28 20:50";
        a_link[22] = "http://www.seoripul.org/kor/about/about01.jsp";
        intro_text[22] = "음악으로 하나되다";
        event_location[22] = "반포대로, 양재천, 악기거리 등 서초구 일대";
        image1[22] = "GU21.jpg";
        image2[22] = "";
        gu[22] = "GU21";

        subject[23] = "2019 강남구『어린이 글짓기·그림그리기 대회』안내문";
        start_date[23] = "2019.09.28 12:00";
        end_date[23] = "2019.09.28 16:00";
        a_link[23] = "http://www.gangnam.go.kr/board/B_000045/1070791/view.do?mid=FM040103";
        intro_text[23] = "강남구 어린이 글짓기 그림 그리기 대회";
        event_location[23] = "개포 동(東)근린공원(개포2동주민센터 옆)";
        image1[23] = "";
        image2[23] = "";
        gu[23] = "GU22";

        subject[24] = "청년 맞춤형 취업 지원 그룹 컨설팅 참여자 모집";
        start_date[24] = "2019.10.23 14:00";
        end_date[24] = "2019.10.25 17:00";
        a_link[24] = "http://www.songpa.go.kr/user.kdf?a=songpa.openadmin.news.NewsApp&c=1002&seq=26090&code=1&cate_id=AG0401000000";
        intro_text[24] = "청년 꿈꾸는 내 일(Job)";
        event_location[24] = "송파구청(신관 8층 송파아카데미)";
        image1[24] = "GU23.jpg";
        image2[24] = "";
        gu[24] = "GU23";

        subject[25] = "제1회 성내1동 마을축제 [함께 나누고 즐기는 행복한 과학여행] 안내";
        start_date[25] = "2019.10.09 10:00";
        end_date[25] = "2019.10.09 16:00";
        a_link[25] = "https://www.gangdong.go.kr/post/41140513421151734?ap=B1140&bbsId=1140";
        intro_text[25] = "행복한 과학여행";
        event_location[25] = "강동구청앞 열린뜰";
        image1[25] = "GU24.jpg";
        image2[25] = "";
        gu[25] = "GU24";

        subject[26] = "중구민과 함께하는 평화기원 가을 남산걷기 안내";
        start_date[26] = "2019. 9. 28 08:30";
        end_date[26] = "2019. 9. 28 11:00";
        a_link[26] = "http://www.junggu.seoul.kr/content.do?cmsid=11329&mode=view&cid=902325572";
        intro_text[26] = " - 1부(걷  기  행  사) : 동별 걷기코스 따라 남산걷기, 버스킹 공연    - 2부(평화기원행사) : 팔각광장에서 평화기원 문화행사 개최";
        event_location[26] = "남산 팔각정  (10:00 집결)";
        image1[26] = "";
        image2[26] = "";
        gu[26] = "GU01";

        subject[27] = "제1회 용산구 마을/자치 박람회 개최";
        start_date[27] = "2019-10-02 10:00";
        end_date[27] = "2019-10-02 16:00";
        a_link[27] = "http://www.yongsan.go.kr/pms/board/detail.do?boardidn=81&sitecdv=S0000100&decorator=pmsweb&menucdv=04030100&boardseqn=8040";
        intro_text[27] = " - 서울마을주간' 기념식 : 용산마을약속, 마을자치 유공 표창 등   - 마을/자치 박람회 :  전시마당, 체험마당, 참여마당";
        event_location[27] = "(주관)용산구 마을자치센터, (주최)용산구";
        image1[27] = "GU02_02.jpg";
        image2[27] = "";
        gu[27] = "GU02";

        subject[28] = "2019년 태양광엑스포개최";
        start_date[28] = "2019.10.23";
        end_date[28] = "2019.10.25";
        a_link[28] = "http://www.sd.go.kr/sd/main.do?op=view&boardSeq=11758&groupSeq=46&mCode=13E010010000&lop=&preViewNum=1";
        intro_text[28] = "서울에너지공사에서 서울시와 함께 태양광 엑스포를 아래와 같이 개최합니다 . 서울에너지공사에서 서울시와 함께 태양광 엑스포를 아래와 같이 개최합니다";
        event_location[28] = "서울광장";
        image1[28] = "";
        image2[28] = "";
        gu[28] = "GU03";

        subject[29] = "[광나루아카데미] 제5회 광나루아카데미";
        start_date[29] = "2019.09.25 09:00";
        end_date[29] = "2019.10.28 18:00";
        a_link[29] = "https://www.gwangjin.go.kr/edu/pgm/edu/view.do?progrmSn=1005&menuNo=400008&pageIndex=1";
        intro_text[29] = "이금희와 함께하는 광나루 Academy";
        event_location[29] = "나루아트센터 대공연장";
        image1[29] = "GU04_02.jpg";
        image2[29] = "";
        gu[29] = "GU04";

        subject[30] = "마을아, 진로를 부탁해!! 해피 Two-Gather 『동대문구 진로직업 박람회』행사 안내";
        start_date[30] = "2019.10.10";
        end_date[30] = "2019.10.11";
        a_link[30] = "http://www.ddm.go.kr/ddm/gujungNewsview.jsp?pid=61868&searchParam1=&searchParam2=&pageNo=1&blockNo=0";
        intro_text[30] = "명사특강, 토크콘서트, 진로마술쇼, 샌드아트, 비보잉 쇼 등";
        event_location[30] = "구청 2층 다목적 강당 및 아트 갤러리, 구청 1층 광장";
        image1[30] = "GU05_02.jpg";
        image2[30] = "";
        gu[30] = "GU05";

        subject[31] = "2019. 광진구 학부모아카데미 '수학은 잘못이 없다.자녀의 미래를 여는 수학공부법'";
        start_date[31] = "2019.09.23 09:00";
        end_date[31] = "2019.10.18 23:00	";
        a_link[31] = "https://www.gwangjin.go.kr/edu/pgm/edu/view.do?progrmSn=1004&ctgryCd=0205&menuNo=400059&pageIndex=1";
        intro_text[31] = "수학은 잘못이 없다 자녀의 미래를 여는 수학공부법";
        event_location[31] = "광진구청 대강당(3층)";
        image1[31] = "GU05_03.jpg";
        image2[31] = "";
        gu[31] = "GU05";

        subject[32] = "2019 흥인지문 바자회";
        start_date[32] = "2019.10.18 08:00";
        end_date[32] = "2019.10.18 18:00";
        a_link[32] = "http://www.ddm.go.kr/ddm/otherNewsview.jsp?pid=61872&searchParam1=&searchParam2=&pageNo=1&blockNo=0";
        intro_text[32] = "2019 흥인지문 바자회  '소박하지만 확실히 행복한 바자회'";
        event_location[32] = "복지관 1층 잔디광장";
        image1[32] = "GU06_02.jpg";
        image2[32] = "";
        gu[32] = "GU06";

        subject[33] = "'한방만두 요리대회' 참가자 모집 안내";
        start_date[33] = "2019.10.11 10:00";
        end_date[33] = "2019.10.12 18:00";
        a_link[33] = "http://www.ddm.go.kr/ddm/otherNewsview.jsp?pid=61872&searchParam1=&searchParam2=&pageNo=1&blockNo=0";
        intro_text[33] = "동대문구 서울한방진흥센터에서는 서울약령시 우수 한방상품 홍보와 지역경제 활성화를 위해     2019년 10월 12일 토요일에 '2019 한방 프리마켓 - 한방에 놀장(場)' 행사를 개최합니다.";
        event_location[33] = "서울한방진흥센터 1층 야외마당";
        image1[33] = "GU06_03.jpg";
        image2[33] = "";
        gu[33] = "GU06";

        subject[34] = "사랑마을 길2시장 참여신청";
        start_date[34] = "2019.10.12 11:00";
        end_date[34] = "2019.10.12 16:00";
        a_link[34] = "http://www.sb.go.kr/PageLink.do?link=forward:/cop/bbs/booth.do&tempParam1=&menuNo=02000000&subMenuNo=02240000&thirdMenuNo=&fourthMenuNo=";
        intro_text[34] = "따뜻한 나눔 정다운 이웃";
        event_location[34] = "래미안길음센터피스 102동 맞은편";
        image1[34] = "GU08_02.jpg";
        image2[34] = "";
        gu[34] = "GU08";

        subject[35] = "2019 구로구 공정무역 포트나잇 개최";
        start_date[35] = "2019-09-28";
        end_date[35] = "2019-10-12";
        a_link[35] = "https://www.guro.go.kr/www/event/NR_view.do?eventSeq=1845";
        intro_text[35] = "공정무역 포트나잇은 마을에서 세상을 바꾸는 2주간의 공정무역 시민축제입니다. 공정무역과 함께하는 특별한 2주 동안, 카페와 생협, 학교와 마을모임 등 구로 곳곳에서 공정무역을 알리고 나누는 다양한 만남과 캠페인이 펼쳐집니다. 공정무역 포럼과 영화제, 공정무역 공부모임부터 공정무역 티파티까지 다양한 호흡과 리듬으로 2주 동안 삶의 곳곳에 공정무역이 깃드는 일상의 축제를 함께 즐겨요  <구로 공정무역 포럼> ○ 일시 : 2019년 9월 30일(월) 오전 10시 30분 ○ 장소 : 성공회대학교 이천환관 시청각실 ○ 문의 : 구로사회적경제통합지원센터 (02-3666-9845)   <구로 공정무역 영화제> ○ 일시 : 2019년 10월 1일(화) 오전 10시 30분 ○ 장소 : 오류문화센터 2층 오류아트홀  ○ 문의 : 구로사회적경제통합지원센터 (02-3666-9845)  **영화제 및 포럼에 사전 신청하고 참여하시면 선착순 200명에 공정무역 쿠폰과 기념품 증정합니다~  <포럼 및 영화제 신청> https://forms.gle/a9tpTiDcHhMR7Fm7A";
        event_location[35] = "";
        image1[35] = "GU16_2.jpg";
        image2[35] = "";
        gu[35] = "GU16";

        subject[36] = "2019 금천 과학 페스티벌";
        start_date[36] = "2019-10-11";
        end_date[36] = "2019-10-12";
        a_link[36] = "https://www.geumcheon.go.kr/program/gc_science_festival/";
        intro_text[36] = "2019 금천 과학 페스티벌 「G-Science Day」 4차 산업혁명의 시대, 과학 도시로 발돋움하는 금천의 첫 번째 과학 이야기! 미래과학기술을 직접 보고, 듣고, 체험할 수 있는 특별한 기회 진로박람회, 미래교육축제와 더불어 더 풍성한 축제 ■ 일 시 2019. 10. 11.(금) ~ 10. 12.(토) 10:00~17:00 ※ 기념식 : 2019. 10. 12.(토) 11:00(예정) ■ 장 소 금천구청 로비 및 광장, 썬큰광장, 금나래공원 일대 ■ 탐(구)! 탐(색)! 탐(험)! 거리 가상현실(VR) : VR 스포츠교실, HTC 바이브 및 구글카드보드 활용 체험, HMD 방식 VR체험, MR(혼합현실) 체험, VR 액티비티 체험 3D 기술 : 3D 전신스캔, 3D 캐릭터 디자인, 3D펜, 3D 모델링 및 프린팅 드론 : 전문가 드론 시뮬레이터 체험, 완구드론 체험, 드론전시(산업용, 농업용, 전문가용) ※ 드론전용 Zone 운영(썬큰광장) 로봇 : 인공지능 로봇 시연, 로봇 코딩, 로봇 댄스 기타 : AR러닝패드, 4D 무비카, 과학체험장비를 이용한 방탈출, 자가 공급 화분 제작, IOT 모형 집 만들기, 센서와 아두이노를 이용한 아이템 제작, 홀로그램 등 진로박람회 : 코딩 전문가, 크리에이터, 스타일리스트, 기상캐스터, 조향사, 반도체전문가, 쥬얼리 전문가, 진로 및 진학상담 등 상시 이벤트 : 인스타그램 자판기, 스마트 캐리커쳐, 타로카드, 페이스페인팅 등";
        event_location[36] = "금천구청 로비 및 광장, 썬큰광장, 금나래공원 일대";
        image1[36] = "GU17_2.png";
        image2[36] = "";
        gu[36] = "GU17";

        subject[37] = "당산골 마을장터 벼룩시장 참여하세요~";
        start_date[37] = "2019-10-19";
        end_date[37] = "2019-10-19";
        a_link[37] = "http://www.ydp.go.kr/main/board/bbs.do?cfgIdx=211&mCode=F040140000&op=view&idxId=234253";
        intro_text[37] = "집에서 잠들어 있는 유아용품, 의류, 도서, 생활용품 등 당산골 마을장터로 가지고 오세요~!! 벼룩시장에 참여하여 이웃과 함께 나누기도 하고, 다양한 체험거리, 먹거리가 있는 마을장터에서 가족과 함께 즐기세요~!!  √ 참여자격: 주민 누구나 √ 참 여 비: 없 음 √ 준 비 물: 돗자리, 팔 물건, 가격표 등 √ 참여신청: 방문, 전화, 이메일 신청(kimmiryang@ydp.go.kr) √ 신청기간: 9월 18일(수) ~ 9월 30일(월) √ 참여문의: 당산1동 주민센터(2670-1106,1117) √ 당산골 마을장터: 10. 19.(토) 11:00~15:00 / 당산골 문화의 거리(영등포로25길 17)  ※ 세부사항은 첨부파일 참고하여주시기 바랍니다~^^";
        event_location[37] = "당산 1동";
        image1[37] = "";
        image2[37] = "";
        gu[37] = "GU18";

        subject[38] = " [2019강남페스티벌] 혜민스님과 함께하는 치유의 시간";
        start_date[38] = "2019-10-01";
        end_date[38] = "2019-10-01";
        a_link[38] = "http://www.gangnam.go.kr/board/article/3431/view.do?mid=FM0508&schArticle=ARTICLE_08";
        intro_text[38] = "‘혜민스님과 함께하는 마음치유 콘서트’가 1일 오후 4시 봉은사 법왕루에서 열린다. 2019 강남페스티벌을 맞아 혜민스님이 특강무대에 선다.   ‘고요할수록 밝아지는 것들’이라는 주제로 진행되는 이번 토크콘서트에서는 고요 속에서 진정한 나를 찾을 수 있는 치유 방법이 소개된다. 삶에 지친 현대인들에게 혜민스님만의 따뜻한 감성으로 응원의 메시지를 전하는 시간이 될 것으로 보인다. ‘고요할수록 밝아지는 것들’은 옛 선사들이 남긴 지혜를 담은 책이다. ‘멈추면 비로소 보이는 것들’(2012), ‘완벽하지 않은 것들에 대한 사랑’(2016)에 이은 혜민의 ‘행복 지침서’ 시리즈 마지막 이야기다. 2018년 12월 출간 이후 교보문고 베스트셀러 8주 연속 1위를 기록했다.    대한민국 대표 힐링멘토인 혜민스님은 ‘멈추면 비로소 보이는 것들’과 ‘완벽하지 않은 것들에 대한 사랑’ 등 다수의 베스트셀러를 집필했다. 마음치유학교를 건립해 경쟁과 스트레스에 휩싸인 현대인들을 돕고 있으며 TV·라디오·SNS 등 다양한 활동으로 대중과 소통하고 있다.   특강은 무료로 진행된다. 구관계자는 “강남의 도심 속 유일 사찰 봉은사가 이번 특강 최적의 장소”라며 “혜민스님과 함께 오롯이 자신에게 집중하는 특별한 힐링 시간이 될 수 있을 것”이라고 말했다.  ";
        event_location[38] = "봉은사 법왕루";
        image1[38] = "GU22_2.jpg";
        image2[38] = "";
        gu[38] = "GU22";
    }

    // 샘플 데이터
    public void initInsert(SQLiteDatabase db) {
        initSettingValue();
        stringBuffer.append("[");
        for(int i = 0; i < totalBoardCnt; i ++) {
            String tmp = "<div class='main-box'><div class='detail-wrap'><div class='detail-list'><h4 class='list-tit'>행사 소개</h4><div class='list-cont'><div class='cont-wrap'><div class='cont-intro'>" + intro_text[i] + "</div></div></div></div><div class='detail-list'><h4 class='list-tit'>행사 내용</h4><div class='list-cont'><div class='cont-wrap'><div class='cont-tit'>" + intro_text[i] + "</div></div></div><div class='detail-list'><h4 class='list-tit'>행사 위치</h4><div class='list-cont'><div class='cont-wrap'><div class='cont-location'>" + event_location[i] + "</div></div></div></div><div class='detail-list'><h4 class='list-tit'>링크</h4><div class='list-cont'><div class='cont-wrap'><div class='cont-location'><a href='" + a_link[i] + "'>" + a_link[i] + "</a></div></div></div></div></div></div></div>";
            stringBuffer.append("{\"create_id\":\"admin1\",");
            stringBuffer.append("\"subject\":\"" + subject[i] + "\",");
            stringBuffer.append("\"startdate\":\"" + start_date[i] + "\",");
            stringBuffer.append("\"enddate\":\"" + end_date[i] + "\",");
            stringBuffer.append("\"intro_content\":\"" + tmp + "\",");
            stringBuffer.append("\"file_path1\":\"" + image1[i] + "\",");
            stringBuffer.append("\"file_path2\":\"" + image2[i] + "\",");
            stringBuffer.append("\"board_type\":\"B001\",");
            if(i == (totalBoardCnt - 1)) {
                stringBuffer.append("\"gu_type\":\"" + gu[i] + "\"} ");
            } else {
                stringBuffer.append("\"gu_type\":\"" + gu[i] + "\"}, ");
            }

        }
        stringBuffer.append("]");

        try {
            JSONArray jsonArray = new JSONArray(stringBuffer.toString());

            for(int i = 0; i < jsonArray.length(); i ++) {
                String create_id = (String)((JSONObject) jsonArray.get(i)).get("create_id");
                String subject = (String)((JSONObject) jsonArray.get(i)).get("subject");
                String startdate = (String)((JSONObject) jsonArray.get(i)).get("startdate");
                String enddate = (String)((JSONObject) jsonArray.get(i)).get("enddate");
                String intro_content = (String)((JSONObject) jsonArray.get(i)).get("intro_content");
                String file_path1 = (String)((JSONObject) jsonArray.get(i)).get("file_path1");
                String board_type = (String)((JSONObject) jsonArray.get(i)).get("board_type");
                String gu_type = (String)((JSONObject) jsonArray.get(i)).get("gu_type");

                ContentValues values = new ContentValues();
                values.put("create_id", create_id);
                values.put("subject", subject);
                values.put("startdate", startdate);
                values.put("enddate", enddate);
                values.put("intro_content", intro_content);
                values.put("file_path1", file_path1);
                values.put("board_type", board_type);
                values.put("gu_type", gu_type);
                db.insert(tb_name, null, values);
                System.out.println("paks >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("paks >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERROR");
        }
    }
}
