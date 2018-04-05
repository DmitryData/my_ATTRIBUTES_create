package CustomViewClass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import studycod.my_attributes_create.R;

/**
 * Created by Lenovo on 05.04.2018.
 * Edited by Karpovich Dmitry.
 */

public class MyView extends View {

    private int SQUARE_SIZE_DEF = 200;
    private Rect mRectSquare;
    private Paint mPaintSquare;
    private int mSquareColor;
    private int mSquareSize;


    public MyView(Context context) {
        super(context);
        init(null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

        mRectSquare = new Rect();                               // create object square
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);        // object for painting and in brackets smoothing

        if (set==null) return;

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyView);

        mSquareColor = ta.getColor(R.styleable.MyView_square_color, mSquareColor);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.MyView_square_size,SQUARE_SIZE_DEF);

        mPaintSquare.setColor(mSquareColor);                     // selected color for painting

        ta.recycle();
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
    }

    public void swap_color(){

        // change color depending on color

        mPaintSquare.setColor(mPaintSquare.getColor() == mSquareColor ? Color.RED : mSquareColor);
        postInvalidate();
    }
}