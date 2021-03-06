package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class ViewCompatLollipop {

    final class AnonymousClass1 implements OnApplyWindowInsetsListener {
        final /* synthetic */ OnApplyWindowInsetsListener val$listener;

        AnonymousClass1(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            this.val$listener = onApplyWindowInsetsListener;
        }

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return ((WindowInsetsCompatApi21) this.val$listener.onApplyWindowInsets(view, new WindowInsetsCompatApi21(windowInsets))).unwrap();
        }
    }

    ViewCompatLollipop() {
    }

    public static void setTransitionName(View view, String str) {
        view.setTransitionName(str);
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static void requestApplyInsets(View view) {
        view.requestApplyInsets();
    }

    public static void setElevation(View view, float f) {
        view.setElevation(f);
    }

    public static float getElevation(View view) {
        return view.getElevation();
    }

    public static void setTranslationZ(View view, float f) {
        view.setTranslationZ(f);
    }

    public static float getTranslationZ(View view) {
        return view.getTranslationZ();
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener(new AnonymousClass1(onApplyWindowInsetsListener));
    }

    public static boolean isImportantForAccessibility(View view) {
        return view.isImportantForAccessibility();
    }

    static ColorStateList getBackgroundTintList(View view) {
        return view.getBackgroundTintList();
    }

    static void setBackgroundTintList(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    static Mode getBackgroundTintMode(View view) {
        return view.getBackgroundTintMode();
    }

    static void setBackgroundTintMode(View view, Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        if (!(windowInsetsCompat instanceof WindowInsetsCompatApi21)) {
            return windowInsetsCompat;
        }
        WindowInsets unwrap = ((WindowInsetsCompatApi21) windowInsetsCompat).unwrap();
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(unwrap);
        if (onApplyWindowInsets != unwrap) {
            return new WindowInsetsCompatApi21(onApplyWindowInsets);
        }
        return windowInsetsCompat;
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        if (!(windowInsetsCompat instanceof WindowInsetsCompatApi21)) {
            return windowInsetsCompat;
        }
        WindowInsets unwrap = ((WindowInsetsCompatApi21) windowInsetsCompat).unwrap();
        WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(unwrap);
        if (dispatchApplyWindowInsets != unwrap) {
            return new WindowInsetsCompatApi21(dispatchApplyWindowInsets);
        }
        return windowInsetsCompat;
    }

    public static void setNestedScrollingEnabled(View view, boolean z) {
        view.setNestedScrollingEnabled(z);
    }

    public static boolean isNestedScrollingEnabled(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static boolean startNestedScroll(View view, int i) {
        return view.startNestedScroll(i);
    }

    public static void stopNestedScroll(View view) {
        view.stopNestedScroll();
    }

    public static boolean hasNestedScrollingParent(View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean dispatchNestedScroll(View view, int i, int i2, int i3, int i4, int[] iArr) {
        return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public static boolean dispatchNestedPreScroll(View view, int i, int i2, int[] iArr, int[] iArr2) {
        return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public static boolean dispatchNestedFling(View view, float f, float f2, boolean z) {
        return view.dispatchNestedFling(f, f2, z);
    }

    public static boolean dispatchNestedPreFling(View view, float f, float f2) {
        return view.dispatchNestedPreFling(f, f2);
    }

    public static float getZ(View view) {
        return view.getZ();
    }
}
