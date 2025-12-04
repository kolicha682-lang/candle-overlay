package com.overlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private View overlayView;

    @Override
    public void onCreate() {
        super.onCreate();

        // إعداد مدير النوافذ
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // نفخ الـ XML إلى View
        overlayView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);

        // إعداد خصائص النافذة العائمة
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        params.y = 100;

        // إضافة الطبقة العائمة للشاشة
        windowManager.addView(overlayView, params);

        // مثال: تغيير نص الإشارة
        TextView text = overlayView.findViewById(R.id.statusText);
        text.setText("READY ✔");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
