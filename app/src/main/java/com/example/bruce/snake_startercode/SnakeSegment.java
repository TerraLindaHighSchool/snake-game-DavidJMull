package com.example.bruce.snake_startercode;

public class SnakeSegment {


    public enum BodyParts{HEAD, BODY, TAIL};
    BodyParts mbodyParts;
    int mDegrees, mXLoc, mYLoc;


    public SnakeSegment( BodyParts bodyParts , int Degrees, int XLoc, int YLoc){
        mbodyParts = bodyParts;
        mDegrees = Degrees;
        mXLoc = XLoc;
        mYLoc = YLoc;
    };

    public int getDegrees() {
        return mDegrees;
    }

    public void setDegrees(int degrees) {
        mDegrees = degrees;
    }

    public int getXLoc() {
        return mXLoc;
    }

    public void setXLoc(int XLoc) {
        mXLoc = XLoc;
    }

    public int getYLoc() {
        return mYLoc;
    }

    public void setYLoc(int YLoc) {
        mYLoc = YLoc;
    }

    public BodyParts getBodyParts() {
        return mbodyParts;
    }

}
