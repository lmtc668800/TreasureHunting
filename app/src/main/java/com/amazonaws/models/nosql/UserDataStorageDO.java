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

@DynamoDBTable(tableName = "tresurehunting-mobilehub-482139222-UserDataStorage")

public class UserDataStorageDO {
    private String _userName;
    private int _coins;
    private Double _latestLatitude;
    private Double _latestLongitude;
    private int _stepNumber;
    private int _unlockHintNumber;

    @DynamoDBHashKey(attributeName = "userName")
    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }
    @DynamoDBAttribute(attributeName = "coins")
    public int getCoins() {
        return _coins;
    }

    public void setCoins(final int _coins) {
        this._coins = _coins;
    }
    @DynamoDBAttribute(attributeName = "latestLatitude")
    public Double getLatestLatitude() {
        return _latestLatitude;
    }

    public void setLatestLatitude(final Double _latestLatitude) {
        this._latestLatitude = _latestLatitude;
    }
    @DynamoDBAttribute(attributeName = "latestLongitude")
    public Double getLatestLongitude() {
        return _latestLongitude;
    }

    public void setLatestLongitude(final Double _latestLongitude) {
        this._latestLongitude = _latestLongitude;
    }
    @DynamoDBAttribute(attributeName = "stepNumber")
    public int getStepNumber() {
        return _stepNumber;
    }

    public void setStepNumber(final int _stepNumber) {
        this._stepNumber = _stepNumber;
    }
    @DynamoDBAttribute(attributeName = "unlockHintNumber")
    public int getUnlockHintNumber() {
        return _unlockHintNumber;
    }

    public void setUnlockHintNumber(final int _unlockHintNumber) {
        this._unlockHintNumber = _unlockHintNumber;
    }

}
