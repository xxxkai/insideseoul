package com.example.insideseoul;

public class GraphicLayout {
    static String Names[] = {
            "종로구","중구","용산구","성동구","광진구",
            "동대문구","중랑구","성북구","강북구","도봉구",
            "노원구","은평구","서대문구","마포구","양천구",
            "강서구","구로구","금천구","영등포구","동작구",
            "관악구","서초구","강남구","송파구","강동구"
    };
    static int id_list[] = {
            1, 2
    };
    static String  Colors[] = {
            // <<<여유 - 혼잡>>>
            "#99d8de", "#23b6b6", "#089e9a"
    };

    public static String getName(int index){
        return Names[index];
    }

    public static int getIndex(String name){
        for(int i = 0; i < Names.length; i++)
            if(Names[i].equals(name)) return i;
        return -1;
    }

}
