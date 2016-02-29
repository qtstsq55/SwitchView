package com.example.switchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class SfView extends SurfaceView  implements SurfaceHolder.Callback{

    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    public SfView(Context context) {
        super(context);
        init();
    }

    public SfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(64);
        this.setZOrderOnTop(true);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
//        surfaceHolder.setKeepScreenOn(true);
//        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void drawView(){
        System.out.println("canvas==null:"+(canvas==null));
        if(canvas != null){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
            paint.setColor(Color.YELLOW);
            canvas.drawText("SurfaceView", 150, 500, paint);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        holder.setFormat(PixelFormat.TRANSLUCENT);
        canvas = holder.lockCanvas();
        drawView();
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
