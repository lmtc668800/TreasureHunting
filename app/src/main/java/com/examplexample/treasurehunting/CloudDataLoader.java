package com.examplexample.treasurehunting;

import com.examplexample.treasurehunting.SpotData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 天 on 2017/6/15.
 */

public class CloudDataLoader {


    public List<SpotData> LoadIn(int stage){
        if (stage ==1) {
            List<SpotData> shinjuku = new ArrayList<>();


//Bonus type:0->gold 1->Hint 2->SmallHint 3->treasure/
            SpotData spot1 = new SpotData("花園神社", 35.693436, 139.705262, "This shrine was founded in the mid-17th century.", 1, "Inside/beside a Department Store");
            SpotData spot2 = new SpotData("TOHOシネマズ新宿", 35.695100, 139.701690, "You can have a discount on every 1st and 14th day of the month!", 1, "Go to the East.");
            SpotData spot3 = new SpotData("新宿ピカデリー", 35.692676, 139.703616, "Find: Tourist Spot ", 2, "hint");
            SpotData spot4 = new SpotData("歌舞伎町", 35.693769, 139.701344, "Find: Giant Monster Movie", 2, "hint");
            SpotData spot5 = new SpotData("新宿野村ビル", 35.693311, 139.695985, "Welcome..Uh, maybe not", 0, "hint");
            SpotData spot6 = new SpotData("東京モード学園", 35.691529, 139.696872, "Find: Feeling tiring of working?\nLet’s find somewhere quiet, somewhere in the city, but take us out of the city", 2, "hint");
            SpotData spot7 = new SpotData("ヤマダ電機 LABI新宿東口館", 35.693481, 139.700824, "Need any electric goods? Visit here!", 0, "hint");
            SpotData spot8 = new SpotData("伊勢丹 新宿店", 35.691929, 139.704283, "Find: Some where with Flowers.", 2, "hint");
            SpotData spot9 = new SpotData("Kirin City キリンシティプラス新宿東南口", 35.690357, 139.701309, "Find: Here is what you have at night, find somewhere you would like to have in the morning ", 2, "hint");
            SpotData spot10 = new SpotData("小田急百貨店 新宿店", 35.691284, 139.699321, "Find: Let’s explore the Another Area", 2, "hint");
            SpotData spot11 = new SpotData("紀伊国屋書店新宿本店", 35.691911, 139.702804, "Big book Store! Reading book is a good hobby.", 0, "hint");
            SpotData spot12 = new SpotData("新宿プリンスホテル", 35.694531, 139.700161, "Find: Rabbit", 2, "hint");
            SpotData spot13 = new SpotData("スターバックスコーヒー 新宿新南口店", 35.688886, 139.702420, "Find: somewhere is competitor of here", 2, "hint");
            SpotData spot14 = new SpotData("ビームス 新宿", 35.691458, 139.701122, "A clothing brand which is established in 1976", 0, "hint");
            SpotData spot15 = new SpotData("ビックロ ビックカメラ 新宿東口", 35.691399, 139.703069, "I have a uniqlo, I have a bic Camera.Ah~BiQlo", 0, "hint");
            SpotData spot16 = new SpotData("損保ジャパン日本興亜美術館", 35.692450, 139.695846, "Artist Museum. Visit here next time!", 1, "It is a CINEMA ");
            SpotData spot17 = new SpotData("新宿マルイ メン", 35.692506, 139.706005, "Find: Try to find another 'OIOI' department.", 2, "hint");
            SpotData spot18 = new SpotData("京王百貨店 新宿店", 35.690025, 139.698844, "Find: To left or to right? ", 2, "hint");
            SpotData spot19 = new SpotData("三菱東京UFJ銀行 新宿通支店", 35.690688, 139.704508, "Find: You may on the correct 'way'.", 2, "hint");
            SpotData spot20 = new SpotData("ファミリーマート 新宿損保ジャパン店", 35.693045, 139.696550, "Buy some drinks and take a rest here. Don't worry about the steps.", 0, "hint");
            SpotData spot21 = new SpotData("新宿ミロード", 35.689230, 139.699803, "Welcome", 0, "hint");
            SpotData spot22 = new SpotData("新宿バルト9", 35.690269, 139.705781, "1300yen during 17:30～19：55.", 3, "hint");
            SpotData spot23 = new SpotData("新宿区役所 本庁舎", 35.693805, 139.703463, "Don't forget pay for your tax.", 0, "hint");
            SpotData spot24 = new SpotData("K’s　cinema", 35.690143, 139.702769, "Find: Isn’t it close?", 2, "hint");
            SpotData spot25 = new SpotData("ブルーボトルコーヒー 新宿カフェ", 35.688809, 139.702068, "What about have a coffee here?", 1, "Having a very famous cafe nearby.");

            SpotData spot26 = new SpotData("伊勢丹メンズ館", 35.692585, 139.704564, "Find: If you have three wishes, what would they be", 2, "hint");
            SpotData spot27 = new SpotData("新宿 東南口駐輪場", 35.689603, 139.701626, "Find: Somewhere opened in 2016. Be careful when pass the street.", 2, "hint");
            SpotData spot28 = new SpotData("ドン・キホーテ 新宿東口本店", 35.693762, 139.701682, "Find: somewhere founded in 1930s. ", 2, "hint");
            SpotData spot29 = new SpotData("DEAN & DELUCA MARKET STORES 新宿", 35.689478, 139.701332, "Welcome", 0, "hint");
            SpotData spot30 = new SpotData("新宿西口ハルク", 35.692743, 139.698809, "Find: Yes, you can find something in this area.But who knows what is you really want.", 2, "hint");
            SpotData spot31 = new SpotData("スターバックスコーヒー 新宿エルタワー店", 35.692142, 139.697776, "Welcome", 0, "hint");
            SpotData spot32 = new SpotData("モザイク通り", 35.690969, 139.699362, "Find: How about try seeing from another perspective?", 2, "hint");
            SpotData spot33 = new SpotData("新宿駅東口駐車場", 35.691926, 139.701305, "Find: I hpoe you are coming from west.", 2, "hint");
            SpotData spot34 = new SpotData("マクドナルド 新宿スバルビル店", 35.691405, 139.697807, "Welcome", 0, "hint");
            SpotData spot35 = new SpotData("湖南菜館", 35.694311, 139.701032, "Find: Almost there", 2, "hint");
            SpotData spot36 = new SpotData("河合塾新宿校", 35.693516, 139.696838, "Welcome", 0, "hint");
            SpotData spot37 = new SpotData("六歌仙", 35.693408, 139.698893, "Are we going to the EAST? ", 2, "hint");
            SpotData spot38 = new SpotData("FOREVER21 新宿店", 35.690011, 139.704679, "Welcome", 0, "hint");
            SpotData spot39 = new SpotData("新宿高島屋", 35.687451, 139.702663, "Don't spend too much time here.", 0, "hint");
            SpotData spot40 = new SpotData("びゅうプラザ", 35.688236, 139.700641, "How did you find me lol lol lol", 0, "hint");
            SpotData spot41 = new SpotData("Brooklyn Parlor 新宿", 35.690251, 139.705960, "Find: Sometimes we only need a little confidence and bravery.", 2, "It is a CINEMA ");
            SpotData spot42 = new SpotData("世界堂 新宿本店", 35.689996, 139.706475, "Find: Try to find another Marui department.", 2, "hint");
            SpotData spot43 = new SpotData("百果園", 35.692539, 139.700947, "Have some fruits with you!", 0, "hint");
//            SpotData spot44 = new SpotData("三菱東京UFJ銀行 新宿通支店", 35.690688, 139.704508, "Find: You may on the correct 'way'.", 2, "hint");
//            SpotData spot45 = new SpotData("ファミリーマート 新宿損保ジャパン店", 35.693045, 139.696550, "Buy some drinks and take a rest here. Don't worry about the steps.", 0, "hint");
//            SpotData spot46 = new SpotData("新宿ミロード", 35.689370, 139.699785, "How did you find me?!", 0, "hint");
//            SpotData spot47 = new SpotData("新宿バルト9", 35.690269, 139.705781, "1300yen during 17:30～19：55.", 3, "hint");
//            SpotData spot48 = new SpotData("新宿区役所 本庁舎", 35.693805, 139.703463, "Find: somewhere founded in 1930s", 2, "hint");
//            SpotData spot49 = new SpotData("K’s　cinema", 35.690143, 139.702769, "Find: Isn’t it close?", 2, "hint");
//            SpotData spot50 = new SpotData("ブルーボトルコーヒー 新宿カフェ", 35.688809, 139.702068, "What about have a coffee here?", 1, "Having a very famous cafe nearby.");


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
            shinjuku.add(spot26);
            shinjuku.add(spot27);
            shinjuku.add(spot28);
            shinjuku.add(spot29);
            shinjuku.add(spot30);
            shinjuku.add(spot31);
            shinjuku.add(spot32);
            shinjuku.add(spot33);
            shinjuku.add(spot34);
            shinjuku.add(spot35);
            shinjuku.add(spot36);
            shinjuku.add(spot37);
            shinjuku.add(spot38);
            shinjuku.add(spot39);
            shinjuku.add(spot40);
            shinjuku.add(spot41);
            shinjuku.add(spot42);
            shinjuku.add(spot43);
//            shinjuku.add(spot44);
//            shinjuku.add(spot45);
//            shinjuku.add(spot46);
//            shinjuku.add(spot47);
//            shinjuku.add(spot48);
//            shinjuku.add(spot49);
//            shinjuku.add(spot50);


            return shinjuku;
        }else{

            List<SpotData> waseda = new ArrayList<>();


//Bonus type:0->gold 1->Hint 2->SmallHint 3->treasure
            SpotData spot1 = new SpotData("West Gate", 35.706407, 139.704855, "Find: Some where you would like to visit when you are hungry.", 2, "Inside/beside a Department Store");
            SpotData spot2 = new SpotData("Bld63 canteen", 35.705706, 139.705212, "What about having a bread with a cup of coffee?", 1, "Go to the East.");
            SpotData spot3 = new SpotData("International student lounge", 35.706281, 139.706158, "Find: Some where you can get help at school.", 2, "hint");
            SpotData spot4 = new SpotData("School Bus station", 35.705643, 139.706719, "You can also check the schedule by Waseda mobile app.", 0, "hint");
            SpotData spot5 = new SpotData("Building 51", 35.706057, 139.706644, "The tallest building in this campus.", 1, "hint");
            SpotData spot6 = new SpotData("Bld52 Reading room", 35.705638, 139.707178, "Lot of student even do not know about this reading room.", 0, "hint");
            SpotData spot7 = new SpotData("Student Co-op cafeteria", 35.706322, 139.707894, "You will have fun on visiting here, even if you do not buy anything.", 0, "hint");
            SpotData spot8 = new SpotData("Rest place", 35.705922, 139.707593, "Find: Find a place that you can have a rest no matter rainy or windy.", 2, "hint");
            SpotData spot9 = new SpotData("YuuCho Bank ATM", 35.706465, 139.707070, "If you want to send a mail, there is also a big post office outside school.", 0, "hint");
            SpotData spot10 = new SpotData("Tully's Coffee", 35.706001, 139.708591, "This shop will be closed when the school is not open.", 3, "hint");
            waseda.add(spot1);
            waseda.add(spot2);
            waseda.add(spot3);
            waseda.add(spot4);
            waseda.add(spot5);
            waseda.add(spot6);
            waseda.add(spot7);
            waseda.add(spot8);
            waseda.add(spot9);
            waseda.add(spot10);
            return waseda;
        }
    }

    public List<String> LoadHints(int stage){
        List<String> hints = new ArrayList<>();

        if (stage == 0){
            String hint1 = "Near to one of the gate";
            String hint2 = "Usually closed on holiday";
            String hint3 = "You can get some food and drinks here";
            String hint4 = "On the east side of school";
            String hint5 = "Near to Building 55N";

            hints.add(hint1);
            hints.add(hint2);
            hints.add(hint3);
            hints.add(hint4);
            hints.add(hint5);
        }else {
            String hint1 = "Inside/beside a Department Store";
            String hint2 = "EAST is Best";
            String hint3 = "It is a CINEMA";
            String hint4 = "Having a store specialises in ART materials near by";
            String hint5 = "Shinjuku Marui Annex";

            hints.add(hint1);
            hints.add(hint2);
            hints.add(hint3);
            hints.add(hint4);
            hints.add(hint5);
        }


        return hints;
    }

}
