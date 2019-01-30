package com.example.bruce.snake_startercode;

public class PivotPoint {

    int mXLoc, mYLoc, mDegree;

    public PivotPoint(int xLoc,int yLoc,int degree){
        mXLoc = xLoc;
        mYLoc = yLoc;
        mDegree = degree;
    }

    public int getXLoc() {
        return mXLoc;
    }

    public int getYLoc() {
        return mYLoc;
    }

    public int getDegree() {
        return mDegree;
    }
}
