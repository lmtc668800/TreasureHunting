package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "tresurehunting-mobilehub-482139222-SpotLocations")

public class SpotLocationsDO {
    private int _itemId;
    private String _bonusContent;
    private int _bonusType;
    private String _description;
    private Double _latitude;
    private Double _longitude;
    private String _name;

    @DynamoDBHashKey(attributeName = "itemId")
    @DynamoDBAttribute(attributeName = "itemId")
    public int getItemId() {
        return _itemId;
    }

    public void setItemId(final int _itemId) {
        this._itemId = _itemId;
    }
    @DynamoDBAttribute(attributeName = "bonusContent")
    public String getBonusContent() {
        return _bonusContent;
    }

    public void setBonusContent(final String _bonusContent) {
        this._bonusContent = _bonusContent;
    }
    @DynamoDBAttribute(attributeName = "bonusType")
    public int getBonusType() {
        return _bonusType;
    }

    public void setBonusType(final int _bonusType) {
        this._bonusType = _bonusType;
    }
    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(final String _description) {
        this._description = _description;
    }
    @DynamoDBAttribute(attributeName = "latitude")
    public Double getLatitude() {
        return _latitude;
    }

    public void setLatitude(final Double _latitude) {
        this._latitude = _latitude;
    }
    @DynamoDBAttribute(attributeName = "longitude")
    public Double getLongitude() {
        return _longitude;
    }

    public void setLongitude(final Double _longitude) {
        this._longitude = _longitude;
    }
    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return _name;
    }

    public void setName(final String _name) {
        this._name = _name;
    }

}
