package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class BitmapDescriptor {
    Bitmap a;
    private Bundle b;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.a = a(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    private Bitmap a(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    byte[] a() {
        Buffer allocate = ByteBuffer.allocate((this.a.getWidth() * this.a.getHeight()) * 4);
        this.a.copyPixelsToBuffer(allocate);
        return allocate.array();
    }

    Bundle b() {
        if (this.a == null) {
            throw new IllegalStateException("the bitmap has been recycled! you can not use it again");
        }
        if (this.b == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("image_width", this.a.getWidth());
            bundle.putInt("image_height", this.a.getHeight());
            byte[] a = a();
            bundle.putByteArray("image_data", a);
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(a, 0, a.length);
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder("");
            for (byte b : digest) {
                stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            bundle.putString("image_hashcode", stringBuilder.toString());
            this.b = bundle;
        }
        return this.b;
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public void recycle() {
        if (this.a != null && !this.a.isRecycled()) {
            this.a.recycle();
            this.a = null;
        }
    }
}
