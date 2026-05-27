/*
 * Dex-Editor-Android an Advanced Dex Editor for Android 
 * Copyright 2024, developer-krushna
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

package modder.hub.dexeditor.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
/*
 * Author - @developer-krushna
 * got idea from stack overflow and some fixes are made by AI
 */
public class ViewAnimationHelper {

    public static void slideOutLeft(final View view) {
        ObjectAnimator slideOut = ObjectAnimator.ofFloat(view, "translationX", 0, -view.getWidth());
        slideOut.setDuration(200); // Adjust duration as needed
        slideOut.setInterpolator(new AccelerateDecelerateInterpolator());

        slideOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });

        slideOut.start();
    }

    public static void slideInRight(final View view) {
        view.setVisibility(View.VISIBLE); // Make view visible before animation
        view.setTranslationX(-view.getWidth()); // Initially position off-screen

        ObjectAnimator slideIn = ObjectAnimator.ofFloat(view, "translationX", -view.getWidth(), 0);
        slideIn.setDuration(200); // Adjust duration as needed
        slideIn.setInterpolator(new AccelerateDecelerateInterpolator());

        slideIn.start();
    }

    public static void slideOutRight(final View view) {
        ObjectAnimator slideOut = ObjectAnimator.ofFloat(view, "translationX", 0, view.getWidth());
        slideOut.setDuration(200); // Adjust duration as needed
        slideOut.setInterpolator(new AccelerateDecelerateInterpolator());

        slideOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });

        slideOut.start();
    }

    public static void slideInLeft(final View view) {
        view.setVisibility(View.VISIBLE); // Make view visible before animation
        view.setTranslationX(view.getWidth()); // Initially position off-screen

        ObjectAnimator slideIn = ObjectAnimator.ofFloat(view, "translationX", view.getWidth(), 0);
        slideIn.setDuration(200); // Adjust duration as needed
        slideIn.setInterpolator(new AccelerateDecelerateInterpolator());

        slideIn.start();
    }
    
    public static void hideViewAndShowViewWithAnimation(final View viewToHide, final View viewToShow) {
		slideOutLeft(viewToHide);
		slideInRight(viewToShow);
	}
    
    public static void enableSwipeViewToggle(final View visibleView, final View hiddenView) {
        final boolean[] switched = {false};
        final int touchSlop = android.view.ViewConfiguration.get(visibleView.getContext()).getScaledTouchSlop();

        final GestureDetector gestureDetector = new GestureDetector(visibleView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                // Don't consume onDown so the RecyclerView can still handle vertical scrolling
                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                if (e1 == null || e2 == null) return false;
                
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();
                
                // Sensitivity: Must be a clear horizontal swipe and exceed thresholds
                // MT Manager style: 2x more horizontal than vertical movement required
                if (Math.abs(diffX) > Math.abs(diffY) * 2 && Math.abs(diffX) > touchSlop * 3 && Math.abs(velocityX) > 500) {
                    if (diffX > 0) { // Swipe Right
                        if (switched[0]) {
                            slideOutRight(visibleView);
                            slideInLeft(hiddenView);
                            switched[0] = false;
                            return true;
                        }
                    } else { // Swipe Left
                        if (!switched[0]) {
                            slideOutLeft(visibleView);
                            slideInRight(hiddenView);
                            switched[0] = true;
                            return true;
                        }
                    }
                }
                return false;
            }
        });

        visibleView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                // Return false to allow the view (RecyclerView) to process its own scroll/click events
                return false;
            }
        });
    }
}
