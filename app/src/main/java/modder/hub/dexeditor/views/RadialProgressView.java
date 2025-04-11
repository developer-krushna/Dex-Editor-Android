/*
 * Dex-Editor-Android an Advanced Dex Editor for Android 
 * Copyright 2024-25, developer-krushna
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of developer-krushna nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
 
 *     Please contact Krushna by email mt.modder.hub@gmail.com if you need
 *     additional information or have any questions
 */


package modder.hub.dexeditor.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.Keep;
import modder.hub.dexeditor.*;

/*
Author an Sketchware Developer..I forgot his name sorry..But if you are the creator of this code then let me know. Thanks
*/

public class RadialProgressView extends View {
 
        private long lastUpdateTime;
        private float radOffset;
        private float currentCircleLength;
        private boolean risingCircleLength;
        private float currentProgressTime;
        private RectF cicleRect = new RectF();
        private boolean useSelfAlpha;
        private float drawingCircleLenght;
 
        private int progressColor;
 
        private DecelerateInterpolator decelerateInterpolator;
        private AccelerateInterpolator accelerateInterpolator;
        private Paint progressPaint;
        private static final float rotationTime = 2000;
        private static final float risingTime = 500;
        private int size;
 
        private float currentProgress;
        private float progressAnimationStart;
        private int progressTime;
        private float animatedProgress;
        private boolean toCircle;
        private float toCircleProgress;
 
        private boolean noProgress = true;
		
       public RadialProgressView(Context context) {
            super(context);
            
            size = dp(45);
 
            progressColor = 0xff6B7278;
            decelerateInterpolator = new DecelerateInterpolator();
            accelerateInterpolator = new AccelerateInterpolator();
            progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            progressPaint.setStyle(Paint.Style.STROKE);
            progressPaint.setStrokeCap(Paint.Cap.ROUND);
            progressPaint.setStrokeWidth(dp(3));
            progressPaint.setColor(progressColor);
        }
 
        public void setUseSelfAlpha(boolean value) {
            useSelfAlpha = value;
        }
 
        @Keep
        @Override
        public void setAlpha(float alpha) {
            super.setAlpha(alpha);
            if (useSelfAlpha) {
                Drawable background = getBackground();
                int a = (int) (alpha * 255);
                if (background != null) {
                    background.setAlpha(a);
                }
                progressPaint.setAlpha(a);
            }
        }
 
        public void setNoProgress(boolean value) {
            noProgress = value;
        }
 
        public void setProgress(float value) {
            currentProgress = value;
            if (animatedProgress > value) {
                animatedProgress = value;
            }
            progressAnimationStart = animatedProgress;
            progressTime = 0;
        }
 
        private void updateAnimation() {
            long newTime = System.currentTimeMillis();
            long dt = newTime - lastUpdateTime;
            if (dt > 17) {
                dt = 17;
            }
            lastUpdateTime = newTime;
 
            radOffset += 360 * dt / rotationTime;
            int count = (int) (radOffset / 360);
            radOffset -= count * 360;
 
            if (toCircle && toCircleProgress != 1f) {
                toCircleProgress += 16 / 220f;
                if (toCircleProgress > 1f) {
                    toCircleProgress = 1f;
                }
            } else if (!toCircle && toCircleProgress != 0f) {
                toCircleProgress -= 16 / 400f;
                if (toCircleProgress < 0) {
                    toCircleProgress = 0f;
                }
            }
 
            if (noProgress) {
                if (toCircleProgress == 0) {
                    currentProgressTime += dt;
                    if (currentProgressTime >= risingTime) {
                        currentProgressTime = risingTime;
                    }
                    if (risingCircleLength) {
                        currentCircleLength =
                                4
                                        + 266
                                                * accelerateInterpolator.getInterpolation(
                                                        currentProgressTime / risingTime);
                    } else {
                        currentCircleLength =
                                4
                                        - 270
                                                * (1.0f
                                                        - decelerateInterpolator.getInterpolation(
                                                                currentProgressTime / risingTime));
                    }
 
                    if (currentProgressTime == risingTime) {
                        if (risingCircleLength) {
                            radOffset += 270;
                            currentCircleLength = -266;
                        }
                        risingCircleLength = !risingCircleLength;
                        currentProgressTime = 0;
                    }
                } else {
                    if (risingCircleLength) {
                        float old = currentCircleLength;
                        currentCircleLength =
                                4
                                        + 266
                                                * accelerateInterpolator.getInterpolation(
                                                        currentProgressTime / risingTime);
                        currentCircleLength += 360 * toCircleProgress;
                        float dx = old - currentCircleLength;
                        if (dx > 0) {
                            radOffset += old - currentCircleLength;
                        }
                    } else {
                        float old = currentCircleLength;
                        currentCircleLength =
                                4
                                        - 270
                                                * (1.0f
                                                        - decelerateInterpolator.getInterpolation(
                                                                currentProgressTime / risingTime));
                        currentCircleLength -= 364 * toCircleProgress;
                        float dx = old - currentCircleLength;
                        if (dx > 0) {
                            radOffset += old - currentCircleLength;
                        }
                    }
                }
            } else {
                float progressDiff = currentProgress - progressAnimationStart;
                if (progressDiff > 0) {
                    progressTime += dt;
                    if (progressTime >= 200.0f) {
                        animatedProgress = progressAnimationStart = currentProgress;
                        progressTime = 0;
                    } else {
                        animatedProgress =
                                progressAnimationStart
                                        + progressDiff
                                                * decelerateInterpolator
                                                        .getInterpolation(progressTime / 200.0f);
                    }
                }
                currentCircleLength = Math.max(4, 360 * animatedProgress);
            }
            invalidate();
        }
 
        public void setSize(int value) {
            size = value;
            invalidate();
        }
 
        public void setStrokeWidth(float value) {
            progressPaint.setStrokeWidth(dp(value));
        }
 
        public void setProgressColor(int color) {
            progressColor = color;
            progressPaint.setColor(progressColor);
        }
 
        public void toCircle(boolean toCircle, boolean animated) {
            this.toCircle = toCircle;
            if (!animated) {
                toCircleProgress = toCircle ? 1f : 0f;
            }
        }
 
        @Override
        protected void onDraw(Canvas canvas) {
            int x = (getMeasuredWidth() - size) / 2;
            int y = (getMeasuredHeight() - size) / 2;
            cicleRect.set(x, y, x + size, y + size);
            canvas.drawArc(
                    cicleRect,
                    radOffset,
                    drawingCircleLenght = currentCircleLength,
                    false,
                    progressPaint);
            updateAnimation();
        }
 
        public void draw(Canvas canvas, float cx, float cy) {
            cicleRect.set(cx - size / 2f, cy - size / 2f, cx + size / 2f, cy + size / 2f);
            canvas.drawArc(
                    cicleRect,
                    radOffset,
                    drawingCircleLenght = currentCircleLength,
                    false,
                    progressPaint);
            updateAnimation();
        }
 
        public boolean isCircle() {
            return Math.abs(drawingCircleLenght) >= 360;
        }
		
		private int dp(float px) {
			return (int) (getContext().getResources().getDisplayMetrics().density * px);
    }
}