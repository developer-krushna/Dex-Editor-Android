package modder.hub.dexeditor.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 Author : @developer-krushna (Krushna Chandra)
 Idea Extracted From MT Manager

 A perfect optimized RecyclerView Fast Scroller for Android
*/

public class FastScrollerRecyclerView extends RecyclerView {

    private final float thumbWidth;
    private final int thumbColor;
    private final boolean isScrollerEnabled = true;
    private final Paint scrollerPaint;
    private final RectF thumbRect;
    private final float thumbHeight;
    private final Runnable hideScrollerRunnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
    private boolean isDragging = false;
    private int lastScrollPosition;
    private boolean isScrollerCurrentlyVisible = false;
    private long lastInteractionTime = 0L;
    private float touchOffset;
    private boolean isDefaultScrollBarEnabled = true;
    private boolean isTrackVisible = true;

    public FastScrollerRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public FastScrollerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastScrollerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.thumbRect = new RectF();
        this.scrollerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        float density = context.getResources().getDisplayMetrics().density;
        this.thumbColor = 0xDD666666; // Standard grey
        this.thumbWidth = 8.0f * density;
        this.thumbHeight = 48.0f * density;

        // Essential: Allow drawing on the RecyclerView itself
        setWillNotDraw(false);

        // Essential: Force redraw during scrolling
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                // Update interaction time so scroller shows up when swiping
                if (!isDragging) {
                    lastInteractionTime = SystemClock.uptimeMillis();
                    removeCallbacks(hideScrollerRunnable);
                    postDelayed(hideScrollerRunnable, 1500);
                }
                invalidate();
            }
        });

        super.setLayoutManager(new LinearLayoutManager(context));

        ItemAnimator animator = getItemAnimator();
        if (animator != null) {
            animator.setAddDuration(100L);
            animator.setRemoveDuration(100L);
            animator.setMoveDuration(200L);
            animator.setChangeDuration(100L);
        }
        lastScrollPosition = -1;
    }

    /**
     * Set whether the scroll track should be visible behind the thumb.
     */
    public void setTrackVisible(boolean visible) {
        this.isTrackVisible = visible;
        invalidate();
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        super.dispatchDraw(canvas);

        if (!isScrollerEnabled || getAdapter() == null) return;

        int itemCount = getAdapter().getItemCount();
        int childCount = getChildCount();

        // Check if list is long enough to show scroller (approx > 4 screens)
        if (childCount <= 0 || (itemCount - childCount) <= 0 || itemCount / childCount <= 4) {
            if (!isDefaultScrollBarEnabled) {
                isDefaultScrollBarEnabled = true;
                setVerticalScrollBarEnabled(true);
            }
            isScrollerCurrentlyVisible = false;
            return;
        }

        int range = computeVerticalScrollRange();
        int extent = computeVerticalScrollExtent();
        int offset = computeVerticalScrollOffset();
        int scrollableRange = range - extent;

        if (scrollableRange <= 0) {
            isScrollerCurrentlyVisible = false;
            return;
        }

        // Fading and Sliding Logic
        float alphaMultiplier = 1.0f;
        float slideOffset = 0.0f;

        boolean isManualVisibilityForced = false;
        if (!isDragging && !isManualVisibilityForced) {
            long timeSinceInteraction = SystemClock.uptimeMillis() - lastInteractionTime;
            if (timeSinceInteraction >= 1500) {
                float fadeProgress = Math.min(1.0f, (timeSinceInteraction - 1500) / 300.0f);
                alphaMultiplier = 1.0f - fadeProgress;
                // Slide out to the right (MT Manager style)
                slideOffset = thumbWidth * fadeProgress;
            }
        }

        if (alphaMultiplier <= 0.0f) {
            isScrollerCurrentlyVisible = false;
            return;
        }

        isScrollerCurrentlyVisible = true;
        if (isDefaultScrollBarEnabled) {
            isDefaultScrollBarEnabled = false;
            setVerticalScrollBarEnabled(false);
        }

        int width = getWidth();
        int height = getHeight();

        // Apply slide animation
        canvas.save();
        canvas.translate(slideOffset, 0);

        // 1. Draw Track
        if (isTrackVisible) {
            int trackColor = 0x11000000; // Very faint track
            int trackAlpha = (int) (Color.alpha(trackColor) * alphaMultiplier);
            scrollerPaint.setColor((trackAlpha << 24) | (trackColor & 0x00FFFFFF));
            float trackLeft = width - thumbWidth;
            canvas.drawRect(trackLeft, 0, width, height, scrollerPaint);
        }

        // 2. Draw Thumb
        int activeColor = isDragging ? 0xFF1E88E5 : thumbColor; // Blue if dragging
        int thumbAlpha = (int) (Color.alpha(activeColor) * alphaMultiplier);
        scrollerPaint.setColor((thumbAlpha << 24) | (activeColor & 0x00FFFFFF));

        float thumbTop = ((float) offset / scrollableRange) * (height - thumbHeight);
        float thumbBottom = thumbTop + thumbHeight;
        float thumbLeft = width - thumbWidth;

        // Update hit-rect for touch events
        // Nearby tapping to toggle the fast scroller
        float density = getContext().getResources().getDisplayMetrics().density;
        float touchWidth = 48.0f * density; // Generous 48dp touch target
        float touchPaddingVertical = 12.0f * density; // Extra 12dp top/bottom sensitivity

        thumbRect.set(width - touchWidth,
                thumbTop - touchPaddingVertical,
                width,
                thumbBottom + touchPaddingVertical);

        canvas.drawRect(thumbLeft, thumbTop, width, thumbBottom, scrollerPaint);

        canvas.restore();

        // Continue animating if in fade-out state
        if (alphaMultiplier < 1.0f) {
            postInvalidateOnAnimation();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScrollerEnabled && isScrollerCurrentlyVisible && ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (thumbRect.contains(ev.getX(), ev.getY())) {
                isDragging = true;
                removeCallbacks(hideScrollerRunnable);
                touchOffset = thumbRect.top - ev.getY();
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDragging) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastScrollPosition = -1;
                    invalidate();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float relativeY = (ev.getY() + touchOffset) / (getHeight() - thumbHeight);
                    relativeY = Math.max(0.0f, Math.min(1.0f, relativeY));

                    int position = 0;
                    if (getAdapter() != null) {
                        position = (int) ((getAdapter().getItemCount() - 1) * relativeY);
                    }
                    if (lastScrollPosition != position) {
                        lastScrollPosition = position;
                        if (getLayoutManager() != null) {
                            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(position, 0);
                        }
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    isDragging = false;
                    lastInteractionTime = SystemClock.uptimeMillis();
                    removeCallbacks(hideScrollerRunnable);
                    postDelayed(hideScrollerRunnable, 1500);
                    invalidate();
                    return true;
            }
        }
        return super.onTouchEvent(ev);
    }
}
