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

package modder.hub.dexeditor.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

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
        final boolean[] switched = {false}; // Use an array to track the state

        final GestureDetector gestureDetector = new GestureDetector(visibleView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffX = e2.getX() - e1.getX();
                    float diffY = e2.getY() - e1.getY();

                    if (Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {  // Right to Left  (Swipe Right -  "Back" to original state)
                            if (switched[0]) { // If the views are currently switched
                                slideOutRight(visibleView); // Slide out current visible to the right
                                slideInLeft(hiddenView);   // Slide in the initially hidden from the left
                                switched[0] = false; // Back to initial state

                                result = true;
                            }

                        } else { // Left to right (Swipe Left - Switch Views)
                            if (!switched[0]) { // If the views are NOT currently switched
                                slideOutLeft(visibleView);  // Slide out current visible to the left
                                slideInRight(hiddenView);   // Slide in the initially hidden from the right
                                switched[0] = true;  // Views are now switched

                                result = true;
                            }

                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        });

        visibleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }
}
