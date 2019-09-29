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

    int totalBoardCnt = 50;

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
