package com.examplexample.treasurehunting;

/**
 * Created by 天 on 2017/1/28.
 */

public class SpotData {

    String name;
    Double latitude;
    Double longitude;
    String description;
    int bonusType;
    String bonusContent;

    SpotData(String name,Double latitude, Double longitude, String description, int bonusType, String bonusContent){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.bonusType = bonusType;
        this.bonusContent = bonusContent;
    }


}
