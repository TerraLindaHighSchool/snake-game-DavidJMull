package com.example.bruce.snake_startercode;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

  private long mMillisDelay;
  private int mScore,mSpriteDim,mBOARD_WIDTH, mBOARD_HEIGHT, mLevel,mCountdown,  mXMax, mYMax ;
  private List<SnakeSegment> mSnake;
  private List<PivotPoint> mPivotPoints;
  private boolean mGameOver;
  private int[] mAppleCoord;

  public SnakeGame(int beginningDirection, int beginningSpriteDim, int beginningX, int beginningY, int width, int height){
    mSpriteDim = beginningSpriteDim;
    mBOARD_WIDTH = width;
    mBOARD_HEIGHT = height;
    mScore = 0;
    mLevel = 1;
    mCountdown = 12;
    mMillisDelay = 400;
    mAppleCoord = new int[2];
    mSnake = new ArrayList<>();
    mSnake.add(new SnakeSegment(SnakeSegment.BodyParts.HEAD, beginningDirection,beginningX, beginningY));
    mSnake.add(new SnakeSegment(SnakeSegment.BodyParts.BODY, beginningDirection,beginningX - 1 ,beginningY));
    mSnake.add(new SnakeSegment(SnakeSegment.BodyParts.TAIL, beginningDirection,beginningX - 2, beginningY));
    mPivotPoints = new ArrayList<>();
    mGameOver = false;
    mXMax = width/beginningSpriteDim;
    mYMax = height/beginningSpriteDim;
    setAppleCord();
  }
  
  protected void touched(float xTouched, float yTouched){
    int snakeHeadX = mSnake.get(0).getXLoc()*mSpriteDim;
    int snakeHeadY = mSnake.get(0).getYLoc()*mSpriteDim;
    int snakeHeadDir = mSnake.get(0).getDegrees();

    if (snakeHeadDir == 0 ||snakeHeadDir == 180){//left right
      if (snakeHeadY < yTouched){ // up
        mPivotPoints.add(new PivotPoint(snakeHeadX/mSpriteDim,snakeHeadY/mSpriteDim, 90));
      }
      if(snakeHeadY > yTouched){//down
        mPivotPoints.add(new PivotPoint(snakeHeadX/mSpriteDim,snakeHeadY/mSpriteDim, 270));
      }
    }
    if (snakeHeadDir == 90 ||snakeHeadDir == 270){ //up down
      if(snakeHeadX < xTouched){
        mPivotPoints.add(new PivotPoint(snakeHeadX/mSpriteDim,snakeHeadY/mSpriteDim, 0));
      }
      if(snakeHeadX > xTouched){
        mPivotPoints.add(new PivotPoint(snakeHeadX/mSpriteDim,snakeHeadY/mSpriteDim, 180));
      }
    }
  }
    
  protected void eatApple(){
        if(mAppleCoord[0] == mSnake.get(0).getXLoc()*getSpriteDim() && mAppleCoord[1] == mSnake.get(0).getYLoc()*getSpriteDim()){
          growSnake();
          setAppleCord();
      }
  }

  private void growSnake(){
    mSnake.add(mSnake.size()- 2,new SnakeSegment(SnakeSegment.BodyParts.BODY, mSnake.get(mSnake.size()- 1).mDegrees,mSnake.get(mSnake.size()- 1).getXLoc(),mSnake.get(mSnake.size()- 1).mYLoc));
    switch (mSnake.get(mSnake.size()- 1).getDegrees()){
      case 0:
        mSnake.get(mSnake.size()- 1).setXLoc(mSnake.get(mSnake.size()- 1).getXLoc() - 1);
        break;
      case 90:
        mSnake.get(mSnake.size()- 1).setYLoc(mSnake.get(mSnake.size()- 1).getYLoc() - 1);
        break;
      case 180:
        mSnake.get(mSnake.size()- 1).setXLoc(mSnake.get(mSnake.size()- 1).getXLoc() + 1);
        break;
      case 270:
        mSnake.get(mSnake.size()- 1).setYLoc(mSnake.get(mSnake.size()- 1).getYLoc() + 1);
        break;
    }
  }

  private void setAppleCord(){
    mAppleCoord[0]= (int)(Math.random() * (mXMax-1) +1)*mSpriteDim;
    mAppleCoord[1]= (int)(Math.random() * (mYMax-1) +1)*mSpriteDim;
  }
    
  protected boolean play() {
    eatApple();
    for (int seg = 0; seg < mSnake.size(); seg++) {
      for (int point = 0; point < mPivotPoints.size(); point++) {
        if (mPivotPoints.get(point).getXLoc() == mSnake.get(seg).getXLoc() && mPivotPoints.get(point).getYLoc() == mSnake.get(seg).getYLoc()) {
          mSnake.get(seg).setDegrees(mPivotPoints.get(point).mDegree);
          if (mSnake.get(seg).getBodyParts() == SnakeSegment.BodyParts.TAIL) {
            mPivotPoints.remove(point);
          }
        }
      }
      switch (mSnake.get(seg).getDegrees()) {
        case 0:
          mSnake.get(seg).setXLoc(mSnake.get(seg).getXLoc() + 1);
          break;
        case 90:
          mSnake.get(seg).setYLoc(mSnake.get(seg).getYLoc() + 1);
          break;
        case 180:
          mSnake.get(seg).setXLoc(mSnake.get(seg).getXLoc() - 1);
          break;
        case 270:
          mSnake.get(seg).setYLoc(mSnake.get(seg).getYLoc() - 1);
          break;
      }
    }
      if (mSnake.get(0).getXLoc() >= mXMax || mSnake.get(0).getYLoc() >= mYMax || mSnake.get(0).getXLoc() <= 0 || mSnake.get(0).getYLoc() <= 0 )
        return true;
      return false;
    }



  protected int getSpriteDim() {
    return mSpriteDim;
  }

  protected long getMillisDelay() {
    return mMillisDelay;
  }

  protected int[] getAppleCoord() {
    return mAppleCoord;
  }

  protected List<SnakeSegment> getSnake() {
    return mSnake;
  }

  protected int getScore() {
    return mScore;
  }

  protected boolean getGameOver() {
    return mGameOver;
  }

  protected int getLevel() {
    return mLevel;
  }

  protected int getCountdown() {
    return mCountdown;
  }
}
