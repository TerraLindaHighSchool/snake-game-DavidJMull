package com.example.bruce.snake_startercode;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

  private int mScore,mSpriteDim,mBOARD_WIDTH, mBOARD_HEIGHT, mLevel,mCountdown,mMillisDelay, mDegrees,mXLoc,mYLoc, mMillsDelay, getMillisDelay, mXMax, mYMax ;
  private List<SnakeSegment> mSnake;
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
    mGameOver = false;
    mXMax = width/beginningSpriteDim;
    mYMax = height/beginningSpriteDim;
    setAppleCord();
  }
  
  protected void touched(float xTouched, float yTouched){
  
  }
    
  protected void eatApple(){
  
  }
  private void setAppleCord(){
    mAppleCoord[0]= (int)(Math.random() * mBOARD_WIDTH);
    mAppleCoord[1]= (int)(Math.random() * mBOARD_HEIGHT);
  }
    
  protected boolean play(){
    for (int seg = 0; seg < mSnake.size(); seg++){
      mSnake.get(seg).setXLoc(mSnake.get(seg).getXLoc()+1 );
    }
    if(mSnake.get(0).getXLoc() >= mXMax)
      return true;
      return false;
  }

  protected int getDegrees() {
    return mDegrees;
  }

  protected List<SnakeSegment> getBodyParts() {
    return mSnake;
  }


  protected int getSpriteDim() {
    return mSpriteDim;
  }

  protected long getMillisDelay() {
    return mMillsDelay;
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
