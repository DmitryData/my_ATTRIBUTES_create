package CustomViewClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import studycod.my_attributes_create.R;

/**
 * Created by Lenovo on 05.04.2018.
 * Edited by Karpovich Dmitry.
 */

public class MyView extends View {

    private float mCircleX, mCircleY;
    private float mCircleRadius = 100f;

    private int SQUARE_SIZE_DEF = 200;
    private int mSquareColor;
    private int mSquareSize;

    private Paint mPaintSquare;
    private Paint mPaintCircle;

    private Rect mRectSquare;


    public MyView(Context context) {
        super(context);
        init(null);
    }
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        // AttributeSet - set of attributes found in the xml file
    }
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        // defStyleAttr An attribute in the current theme that contains a reference to a style resource that supplies defaults values for the StyledAttributes.
    }
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
        // defStyleRes A resource identifier of a style resource that supplies default values for the StyledAttributes, used only if defStyleAttr is 0 or can not be found in the theme.
    }

    private void init(@Nullable AttributeSet set){

        mRectSquare = new Rect();                               // create object square
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);        // object for painting and in brackets smoothing
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);                        // set or cleaning bits for smothing
        mPaintCircle.setColor(Color.parseColor("#00ccff")); // convert from string color code to Color

        if (set==null) return;                                  // if no attributes in xml, return

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyView); // obtainStyledAttributes - Return a TypedArray holding the values defined by the style resource resid which are listed in attrs.

        mSquareColor = ta.getColor(R.styleable.MyView_square_color, mSquareColor);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.MyView_square_size,SQUARE_SIZE_DEF);  // Retrieve a dimensional for a particular resource ID for use as a size in raw pixels.

        mPaintSquare.setColor(mSquareColor);                     // selected color for painting

        ta.recycle();                                            // clean memory
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);      // set background
                                           // set coordinates
                                           // left top angle and right bottom angle
        mRectSquare.left = 50;
        mRectSquare.top = 50;
        mRectSquare.bottom = mRectSquare.top + mSquareSize;
        mRectSquare.right = mRectSquare.left + mSquareSize;

        canvas.drawRect(mRectSquare,mPaintSquare);       // apply settings paint and dimension for create square

        if (mCircleX == 0f || mCircleY == 0f){
            mCircleX = getWidth()/2;
            mCircleY = getHeight()/2;
        }

        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle);

    }
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event){
        // MotionEvent - Object used to report movement (mouse, pen, finger, trackball) events. Motion events may hold either absolute or relative movements and other data, depending on the type of device.

        boolean value = super.onTouchEvent(event);

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:{
                // click
                float x = event.getX();
                float y = event.getY();
                if (mRectSquare.left < x && mRectSquare.right > x)
                    if (mRectSquare.top < y && mRectSquare.bottom > y){
                    mCircleRadius += 10f;
                    postInvalidate();
                    }
                return true;
            }

            case MotionEvent.ACTION_MOVE:{
                // move
                float x = event.getX();
                float y = event.getY();
                double dx = Math.pow(x - mCircleX, 2);
                double dy = Math.pow(y - mCircleY, 2);
                if (dx + dy < Math.pow(mCircleRadius, 2)){
                    //Touched
                    mCircleX = x;
                    mCircleY = y;
                    postInvalidate();
                    return true;
                }
                return value;
            }
        }
       return true;
    }
    public void swap_color(){

        // change color depending on color

        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor ? Color.RED : mSquareColor);
        postInvalidate();
    }
}