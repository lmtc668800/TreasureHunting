package com.examplexample.treasurehunting;

import com.examplexample.treasurehunting.SpotData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 天 on 2017/1/28.
 */

public class DataLoader {


    public List<SpotData> LoadIn(){
        List<SpotData> shinjuku = new ArrayList<>();


//Bonus type:0->gold 1->Hint 2->SmallHint 3->treasure
        SpotData spot1 = new SpotData("花園神社",35.693557,139.705169,"description",0,"hint");
        SpotData spot2 = new SpotData("TOHOシネマズ新宿",35.695415,139.702055,"description",1,"hint");
        SpotData spot3 = new SpotData("新宿ピカデリー",35.692676,139.703616,"description",1,"hint");
        SpotData spot4 = new SpotData("歌舞伎町",35.694937,139.702861,"description",2,"hint");
        SpotData spot5 = new SpotData("新宿野村ビル",35.693014,139.695375,"description",0,"hint");
        SpotData spot6 = new SpotData("東京モード学園",35.691529,139.696872,"description",0,"hint");
        SpotData spot7 = new SpotData("ヤマダ電機 LABI新宿東口館",35.693320,139.700820,"description",0,"hint");
        SpotData spot8 = new SpotData("伊勢丹 新宿店",35.691637,139.704616,"description",0,"hint");
        SpotData spot9 = new SpotData("Kirin City キリンシティプラス新宿東南口",35.690357,139.701309,"description",0,"hint");
        SpotData spot10 = new SpotData("小田急百貨店 新宿店",35.691322,139.699558,"description",0,"hint");
        SpotData spot11 = new SpotData("紀伊国屋書店新宿本店",35.692233,139.703012,"description",0,"hint");
        SpotData spot12 = new SpotData("新宿プリンスホテル",35.694531,139.700161,"description",0,"hint");
        SpotData spot13 = new SpotData("スターバックスコーヒー 新宿新南口店",35.688886,139.702420,"description",0,"hint");
        SpotData spot14 = new SpotData("ビームス 新宿",35.691458,139.701122,"description",0,"hint");
        SpotData spot15 = new SpotData("ビックロ ビックカメラ 新宿東口",35.691399,139.703069,"description",0,"hint");
        SpotData spot16 = new SpotData("損保ジャパン日本興亜美術館",35.692450,139.695846,"description",0,"hint");
        SpotData spot17 = new SpotData("新宿マルイ メン",35.692652,139.706349,"description",0,"hint");
        SpotData spot18 = new SpotData("京王百貨店 新宿店",35.690211,139.699161,"description",0,"hint");
        SpotData spot19 = new SpotData("三菱東京UFJ銀行 新宿通支店",35.690688,139.704508,"description",0,"hint");
        SpotData spot20 = new SpotData("ファミリーマート 新宿損保ジャパン店",35.693045,139.696550,"description",0,"hint");
        SpotData spot21 = new SpotData("新宿ミロード",35.689370,139.699785,"description",0,"hint");
        SpotData spot22 = new SpotData("新宿バルト9",35.690053,139.705803,"description",0,"hint");
        SpotData spot23 = new SpotData("新宿区役所 本庁舎",35.693805,139.703463,"description",0,"hint");
        SpotData spot24 = new SpotData("K’s　cinema",35.690143,139.702769,"description",3,"hint");
        SpotData spot25 = new SpotData("ブルーボトルコーヒー 新宿カフェ",35.688809,139.702068,"description",0,"hint");





        shinjuku.add(spot1);
        shinjuku.add(spot2);
        shinjuku.add(spot3);
        shinjuku.add(spot4);
        shinjuku.add(spot5);
        shinjuku.add(spot6);
        shinjuku.add(spot7);
        shinjuku.add(spot8);
        shinjuku.add(spot9);
        shinjuku.add(spot10);
        shinjuku.add(spot11);
        shinjuku.add(spot12);
        shinjuku.add(spot13);
        shinjuku.add(spot14);
        shinjuku.add(spot15);
        shinjuku.add(spot16);
        shinjuku.add(spot17);
        shinjuku.add(spot18);
        shinjuku.add(spot19);
        shinjuku.add(spot20);
        shinjuku.add(spot21);
        shinjuku.add(spot22);
        shinjuku.add(spot23);
        shinjuku.add(spot24);
        shinjuku.add(spot25);


        return shinjuku;
    }

    public List<String> LoadHints(){
        List<String> hints = new ArrayList<>();
        String hint1= "Near to Caffee store";
        String hint2= "hint2";
        String hint3= "hint3";
        String hint4= "hint4";
        String hint5= "hint5";
        hints.add(hint1);
        hints.add(hint2);
        hints.add(hint3);
        hints.add(hint4);
        hints.add(hint5);
        return hints;
    }

}
