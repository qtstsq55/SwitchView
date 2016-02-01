package com.example.switchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CommonView extends View {
    private Paint paint;
    public CommonView(Context context) {
        super(context);
        init();
    }

    public CommonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setTextSize(64);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("CommonView",150,500,paint);
    }
}
