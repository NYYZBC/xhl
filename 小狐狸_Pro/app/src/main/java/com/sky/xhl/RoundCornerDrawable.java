package com.sky.xhl;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class RoundCornerDrawable extends Drawable {
    private int radius; // 圆角半径
    private Paint paint; // 画笔
    private int backgroundColor; // 圆角背景颜色

    public RoundCornerDrawable(int radius, int backgroundColor) {
        this.radius = radius;
        this.backgroundColor = backgroundColor;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(backgroundColor);
    }

    @Override
    public void draw(Canvas canvas) {
        RectF rect = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}