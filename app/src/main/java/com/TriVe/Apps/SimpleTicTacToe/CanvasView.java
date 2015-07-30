package com.TriVe.Apps.SimpleTicTacToe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {

    private CanvasEvents mCallback;

    public int width;
    public int height;

    private float thickness;


    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Context context;
    private Paint mPaintGrid, mPaintCross, mPaintCircle, mPaintText;
//    private String mCaseName = "";
    private int mCaseNumber = -1;

    private List<PointF>PointsList;

    private List<Integer>CrossList;
    private List<Integer>CircleList;

    private String DisplayedText = "";
    private int DisplayedTextColor = Color.RED;


    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        // and we set a new Paint with the desired attributes for the grid
        mPaintGrid = new Paint();
        mPaintGrid.setAntiAlias(true);
        mPaintGrid.setColor(Color.BLACK);
        mPaintGrid.setStyle(Paint.Style.STROKE);
        mPaintGrid.setStrokeJoin(Paint.Join.ROUND);
        mPaintGrid.setStrokeWidth(4f);

        // and we set a new Paint with the desired attributes for the grid
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.DKGRAY);
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintText.setFakeBoldText(true);
        mPaintText.setTextAlign(Paint.Align.CENTER);

        PointsList = new ArrayList<>();
        PointsList.add(new PointF(1.0f/6.0f, 1.0f/6.0f));
        PointsList.add(new PointF(1.0f/2.0f, 1.0f/6.0f));
        PointsList.add(new PointF(5.0f/6.0f, 1.0f/6.0f));
        PointsList.add(new PointF(1.0f/6.0f, 1.0f/2.0f));
        PointsList.add(new PointF(1.0f/2.0f, 1.0f/2.0f));
        PointsList.add(new PointF(5.0f/6.0f, 1.0f/2.0f));
        PointsList.add(new PointF(1.0f/6.0f, 5.0f/6.0f));
        PointsList.add(new PointF(1.0f/2.0f, 5.0f/6.0f));
        PointsList.add(new PointF(5.0f/6.0f, 5.0f/6.0f));

        CircleList = new ArrayList<>();
        CrossList = new ArrayList<>();

        // and we set a new Paint with the desired attributes for the grid
        mPaintCross = new Paint();
        mPaintCross.setAntiAlias(true);

        mPaintCross.setColor(Color.rgb(252, 145, 78));
        mPaintCross.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.rgb(220, 255, 175));
        mPaintCircle.setStyle(Paint.Style.STROKE);

        try {
            mCallback = (CanvasEvents)c;
        }
        catch (Exception ex)
        {
            Log.d("Debug", "This is an error.");
        }


    }



    private int coordToCase(float x, float y)
    {
        if (x < mCanvas.getWidth() * 1 / 3)
        {
            if (y < mCanvas.getHeight() * 1 / 3)
                return 0;
            else if (y < mCanvas.getHeight() * 2 /  3)
                return 3;
            else if (y < mCanvas.getHeight())
                return 6;
        }
        else if (x < mCanvas.getWidth() * 2 / 3)
        {
            if (y < mCanvas.getHeight() * 1 / 3)
                return 1;
            else if (y < mCanvas.getHeight() * 2 /  3)
                return 4;
            else if (y < mCanvas.getHeight())
                return 7;
        }
        else if (x < mCanvas.getWidth())
        {
            if (y < mCanvas.getHeight() * 1 / 3)
                return 2;
            else if (y < mCanvas.getHeight() * 2 /  3)
                return 5;
            else if (y < mCanvas.getHeight())
                return 8;
        }

        return -1;
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCanvas = canvas;

        thickness = (mCanvas.getHeight() > getWidth()) ? mCanvas.getWidth() / 50 : mCanvas.getHeight() / 50;

        mPaintText.setTextSize(thickness * 8);
        mPaintCross.setStrokeWidth(thickness * 3);
        mPaintCircle.setStrokeWidth(thickness * 3);
        mPaintGrid.setStrokeWidth(thickness);



        drawGridLines();

        for (int i : CircleList)
            drawCircle(i);

        for (int i : CrossList)
            drawCross(i);

        drawText();

    }


    public boolean AddCircle(int chosenCase)
    {
        if (!CircleList.contains(chosenCase))
        {
            CircleList.add(chosenCase);
            invalidate();
            return true;
        }
        else
            return false;
    }

    public boolean AddCross(int chosenCase)
    {
        if (!CrossList.contains(chosenCase))
        {
            CrossList.add(chosenCase);
            invalidate();
            return true;
        }
        else
            return false;


    }

    public void DrawText(String text, int color)
    {
        DisplayedText = text;
        DisplayedTextColor = color;
        invalidate();
    }


    public void clearCanvas() {
        mCaseNumber = -1;

        CircleList.clear();
        CrossList.clear();

        DisplayedText = "";

        invalidate();
    }


    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mCallback.OnCellTouched(coordToCase(x, y));
                break;
        }
        return true;
    }


    private void drawText()
    {
        int xPos = (mCanvas.getWidth() / 2);
        int yPos = (int) ((mCanvas.getHeight() / 2) - ((mPaintText.descent() + mPaintText.ascent()) / 2)) ;
        mPaintText.setColor(DisplayedTextColor);
        mCanvas.drawText(DisplayedText, xPos, yPos, mPaintText);

        invalidate();
    }

    private void drawGridLines()
    {
        mCanvas.drawLine(0, mCanvas.getHeight() / 3,  mCanvas.getWidth(), mCanvas.getHeight() / 3, mPaintGrid);
        mCanvas.drawLine(0, mCanvas.getHeight() * 2 / 3,  mCanvas.getWidth(), mCanvas.getHeight() * 2 / 3, mPaintGrid);
        mCanvas.drawLine(mCanvas.getWidth() / 3, 0,  mCanvas.getWidth() / 3, mCanvas.getHeight(), mPaintGrid);
        mCanvas.drawLine(mCanvas.getWidth() * 2 / 3, 0,  mCanvas.getWidth() * 2 / 3, mCanvas.getHeight(), mPaintGrid);
        invalidate();
    }

    private void drawCircle(int circleInList )
    {
        float radius = (mCanvas.getHeight() > getWidth()) ? mCanvas.getWidth() / 8: mCanvas.getHeight() / 8;

        mCanvas.drawCircle(PointsList.get(circleInList).x * mCanvas.getWidth(), PointsList.get(circleInList).y * mCanvas.getHeight(), radius, mPaintCircle);
        invalidate();
    }

    private void drawCross(int crossInList )
    {
        float offset = (mCanvas.getHeight() > getWidth()) ? mCanvas.getWidth() / 8: mCanvas.getHeight() / 8;

        mCanvas.drawLine(PointsList.get(crossInList).x * mCanvas.getWidth() - offset, PointsList.get(crossInList).y  * mCanvas.getHeight() - offset,
                PointsList.get(crossInList).x * mCanvas.getWidth() + offset, PointsList.get(crossInList).y * mCanvas.getHeight() + offset, mPaintCross);

        mCanvas.drawLine(PointsList.get(crossInList).x * mCanvas.getWidth() - offset, PointsList.get(crossInList).y * mCanvas.getHeight() + offset,
                PointsList.get(crossInList).x * mCanvas.getWidth() + offset, PointsList.get(crossInList).y * mCanvas.getHeight() - offset, mPaintCross);

        invalidate();
    }


    public interface CanvasEvents
    {
        void OnCellTouched(int cell);
    }

}
