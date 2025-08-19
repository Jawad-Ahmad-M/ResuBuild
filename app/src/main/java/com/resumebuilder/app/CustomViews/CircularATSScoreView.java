package com.resumebuilder.app.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class CircularATSScoreView extends View {

    private static final float STROKE_WIDTH = 20f; // Width of the ring
    private static final float TEXT_SIZE = 40f;    // Size of the score text
    private int score = 0;                         // ATS score (0-100)
    private Paint ringPaint;
    private Paint textPaint;                       // Made class field
    private RectF rectF;

    public CircularATSScoreView(Context context) {
        super(context);
        init();
    }

    public CircularATSScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ringPaint = new Paint();
        ringPaint.setAntiAlias(true);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(STROKE_WIDTH);
        ringPaint.setStrokeCap(Paint.Cap.ROUND); // Smooth arc ends

        textPaint = new Paint(); // Initialized as class field
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);

        rectF = new RectF();
    }

    public void setScore(int score) {
        if (score < 0) score = 0;
        if (score > 100) score = 100;
        this.score = score;
        invalidate(); // Redraw the view
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = Math.min(width, height) / 2f - STROKE_WIDTH;

        // Background ring (full gray circle)
        ringPaint.setColor(Color.GRAY);
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(rectF, 0, 360, false, ringPaint);

        // Progress arc (dynamic gradient color based on score, proportional to score)
        ringPaint.setColor(getColorForScore(score));
        float sweepAngle = (score * 360) / 100f; // 0-360 degrees based on score
        canvas.drawArc(rectF, -90, sweepAngle, false, ringPaint); // -90 starts at 12 o'clock
    }

    private int getColorForScore(int score) {
        // Normalize score to a 0-1 range for gradient interpolation
        float normalizedScore = score / 100f;

        // Gradient from red (low) to green (high)
        // Red component decreases from 1 to 0
        // Green component increases from 0 to 1
        // Blue remains 0 for a red-yellow-green transition
        int red = (int) (255 * (1 - normalizedScore));   // High at low scores, low at high scores
        int green = (int) (255 * normalizedScore);       // Low at low scores, high at high scores
        int blue = 0;                                    // No blue for red-to-green gradient

        // For finer grading (shades):
        // - 0-40: Dark red (deeper red for very low) to light red
        // - 41-70: Orange/yellow tones
        // - 71-100: Light green to dark green (deeper green for excellent)
        if (normalizedScore <= 0.4f) {
            // Dark red (0-20%) to light red (21-40%)
            red = 255; // Full red
            green = (int) (255 * (normalizedScore / 0.4f) * 0.2f); // Slight orange tint for lightness
        } else if (normalizedScore <= 0.7f) {
            // Yellow/orange (41-70%)
            float midNorm = (normalizedScore - 0.4f) / 0.3f; // Normalize within range
            red = 255;
            green = (int) (255 * midNorm); // Increase green for yellow
        } else {
            // Light green (71-85%) to dark green (86-100%)
            float highNorm = (normalizedScore - 0.7f) / 0.3f; // Normalize within range
            red = (int) (255 * (1 - highNorm)); // Decrease red
            green = 255; // Full green
        }

        return Color.rgb(red, green, blue);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size); // Ensure square view
    }
}