package com.example.emrald_villas.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Navigator {

    // ✅ SIMPLE go (used in WelcomeActivity)
    public static void go(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    // ✅ SIMPLE goTo (used in other screens)
    public static void goTo(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    // ✅ Go and finish current activity
    public static void goAndFinish(Activity activity, Class<?> destination) {
        Intent intent = new Intent(activity, destination);
        activity.startActivity(intent);
        activity.finish();
    }

    // ✅ Go with single extra
    public static void goWithExtra(Context context, Class<?> destination,
                                   String key, long value) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    // ✅ Go with multiple extras
    public static void goWithExtras(Context context, Class<?> destination,
                                    String key1, long value1,
                                    String key2, double value2) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key1, value1);
        intent.putExtra(key2, value2);
        context.startActivity(intent);
    }
}
