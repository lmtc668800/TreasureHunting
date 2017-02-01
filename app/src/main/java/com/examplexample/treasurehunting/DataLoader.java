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
        SpotData spot1 = new SpotData("花園神社",35.693436,139.705262,"This shrine was founded in the mid-17th century.",1,"Inside/beside a Department Store");
        SpotData spot2 = new SpotData("TOHOシネマズ新宿",35.695100,139.701690,"You can have a discount on every 1st and 14th day of the month!",1,"Go to the East.");
        SpotData spot3 = new SpotData("新宿ピカデリー",35.692676,139.703616,"Find: Tourist Spot ",2,"hint");
        SpotData spot4 = new SpotData("歌舞伎町",35.694887,139.702928,"Find: Giant Monster Movie",2,"hint");
        SpotData spot5 = new SpotData("新宿野村ビル",35.693014,139.695375,"Find: It is a Museum",2,"hint");
        SpotData spot6 = new SpotData("東京モード学園",35.691529,139.696872,"Find: Feeling tiering of working?\nLet’s find somewhere quiet, somewhere in the city, but take us out of the city",2,"hint");
        SpotData spot7 = new SpotData("ヤマダ電機 LABI新宿東口館",35.693481,139.700824,"Need any electric goods? Visit here!",0,"hint");
        SpotData spot8 = new SpotData("伊勢丹 新宿店",35.691929,139.704283,"Find: Flowers.",2,"hint");
        SpotData spot9 = new SpotData("Kirin City キリンシティプラス新宿東南口",35.690357,139.701309,"Find: Here is what you have at night, find somewhere you would like to have in the morning ",2,"hint");
        SpotData spot10 = new SpotData("小田急百貨店 新宿店",35.691322,139.699558,"Find: Let’s explore the Skyscraper Area",2,"hint");
        SpotData spot11 = new SpotData("紀伊国屋書店新宿本店",35.691911,139.702804,"Big book Store! Reading book is a good hobby.",0,"hint");
        SpotData spot12 = new SpotData("新宿プリンスホテル",35.694531,139.700161,"Find: Rabbit",2,"hint");
        SpotData spot13 = new SpotData("スターバックスコーヒー 新宿新南口店",35.688886,139.702420,"Find: somewhere is competitor of here",2,"hint");
        SpotData spot14 = new SpotData("ビームス 新宿",35.691458,139.701122,"A clothing brand which is established in 1976",0,"hint");
        SpotData spot15 = new SpotData("ビックロ ビックカメラ 新宿東口",35.691399,139.703069,"Find: Somewhere opened in 2016",2,"hint");
        SpotData spot16 = new SpotData("損保ジャパン日本興亜美術館",35.692450,139.695846,"description",1,"It is a CINEMA ");
        SpotData spot17 = new SpotData("新宿マルイ メン",35.692652,139.706349,"Find: Try to find another Marui department.",2,"hint");
        SpotData spot18 = new SpotData("京王百貨店 新宿店",35.690211,139.699161,"Find: To left or to right? ",2,"hint");
        SpotData spot19 = new SpotData("三菱東京UFJ銀行 新宿通支店",35.690688,139.704508,"Find: You may on the correct 'way'.",2,"hint");
        SpotData spot20 = new SpotData("ファミリーマート 新宿損保ジャパン店",35.693045,139.696550,"Buy some drinks and take a rest here. Don't worry about the steps.",0,"hint");
        SpotData spot21 = new SpotData("新宿ミロード",35.689370,139.699785,"How did you find me?!",0,"hint");
        SpotData spot22 = new SpotData("新宿バルト9",35.690269,139.705781,"1300yen during 17:30～19：55.",3,"hint");
        SpotData spot23 = new SpotData("新宿区役所 本庁舎",35.693805,139.703463,"Find: somewhere founded in 1930s",2,"hint");
        SpotData spot24 = new SpotData("K’s　cinema",35.690143,139.702769,"Find: Isn’t it close?",2,"hint");
        SpotData spot25 = new SpotData("ブルーボトルコーヒー 新宿カフェ",35.688809,139.702068,"What about have a coffee here?",1,"Having a very famous cafe nearby.");





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
        String hint1= "Inside/beside a Department Store";
        String hint2= "EAST is Best";
        String hint3= "It is a CINEMA";
        String hint4= "Having a store specialises in ART materials near by";
        String hint5= "Shinjuku Marui Annex";
        hints.add(hint1);
        hints.add(hint2);
        hints.add(hint3);
        hints.add(hint4);
        hints.add(hint5);
        return hints;
    }

}
