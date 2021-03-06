package com.baidu.location.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.location.Location;
import android.net.http.Headers;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.h.c;
import com.baidu.location.h.f;
import com.baidu.location.h.h;
import com.mqunar.BuildConfig;
import com.mqunar.necro.agent.instrumentation.HttpInstrumentation;
import com.mqunar.necro.agent.instrumentation.Instrumented;
import com.mqunar.tools.DateTimeUtils;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONException;
import org.json.JSONObject;

@Instrumented
public class e {
    public static String f = "0";
    private static e j = null;
    private a A;
    private boolean B;
    private boolean C;
    private int D;
    private float E;
    private float F;
    private long G;
    private int H;
    private Handler I;
    private byte[] J;
    private byte[] K;
    private int L;
    private List<Byte> M;
    private boolean N;
    long a;
    Location b;
    Location c;
    StringBuilder d;
    long e;
    int g;
    double h;
    double i;
    private int k;
    private double l;
    private String m;
    private int n;
    private int o;
    private int p;
    private int q;
    private double r;
    private double s;
    private double t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private long z;

    class a extends f {
        String a;
        final /* synthetic */ e b;

        public a(e eVar) {
            this.b = eVar;
            this.a = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = "http://loc.map.baidu.com/cc.php";
            String encode = Jni.encode(this.a);
            this.a = null;
            this.k.put("q", encode);
        }

        public void a(String str) {
            this.a = str;
            e();
        }

        public void a(boolean z) {
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    jSONObject.put(BuildConfig.EPYT_YOLPED, c.d);
                    jSONObject.put("uptime", System.currentTimeMillis());
                    this.b.e(jSONObject.toString());
                } catch (Exception e) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    private e() {
        this.k = 1;
        this.l = 0.699999988079071d;
        this.m = "3G|4G";
        this.n = 1;
        this.o = 307200;
        this.p = 15;
        this.q = 1;
        this.r = 3.5d;
        this.s = 3.0d;
        this.t = 0.5d;
        this.u = 300;
        this.v = 60;
        this.w = 0;
        this.x = 60;
        this.y = 0;
        this.z = 0;
        this.A = null;
        this.B = false;
        this.C = false;
        this.D = 0;
        this.E = 0.0f;
        this.F = 0.0f;
        this.G = 0;
        this.H = 500;
        this.a = 0;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.I = null;
        this.J = new byte[4];
        this.K = null;
        this.L = 0;
        this.M = null;
        this.N = false;
        this.g = 0;
        this.h = 116.22345545d;
        this.i = 40.245667323d;
        this.I = new Handler();
    }

    public static e a() {
        if (j == null) {
            j = new e();
        }
        return j;
    }

    private String a(File file, String str) {
        String uuid = UUID.randomUUID().toString();
        String str2 = "--";
        String str3 = IOUtils.LINE_SEPARATOR_WINDOWS;
        String str4 = "multipart/form-data";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(str).openConnection());
            httpURLConnection.setReadTimeout(LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL);
            httpURLConnection.setConnectTimeout(LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            httpURLConnection.setRequestProperty("Charset", "utf-8");
            httpURLConnection.setRequestProperty(Headers.CONN_DIRECTIVE, "close");
            httpURLConnection.setRequestProperty("Content-Type", str4 + ";boundary=" + uuid);
            if (file != null && file.exists()) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str2);
                stringBuffer.append(uuid);
                stringBuffer.append(str3);
                stringBuffer.append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + file.getName() + "\"" + str3);
                stringBuffer.append("Content-Type: application/octet-stream; charset=utf-8" + str3);
                stringBuffer.append(str3);
                dataOutputStream.write(stringBuffer.toString().getBytes());
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                dataOutputStream.write(str3.getBytes());
                dataOutputStream.write((str2 + uuid + str2 + str3).getBytes());
                dataOutputStream.flush();
                int responseCode = httpURLConnection.getResponseCode();
                outputStream.close();
                httpURLConnection.disconnect();
                this.y += HttpStatus.SC_BAD_REQUEST;
                c(this.y);
                if (responseCode == 200) {
                    return "1";
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e2) {
        }
        return "0";
    }

    private boolean a(String str, Context context) {
        boolean z = false;
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    boolean z2;
                    if (runningAppProcessInfo.processName.equals(str)) {
                        int i = runningAppProcessInfo.importance;
                        if (i == 200 || i == 100) {
                            z2 = true;
                            z = z2;
                        }
                    }
                    z2 = z;
                    z = z2;
                }
            }
        } catch (Exception e) {
        }
        return z;
    }

    private byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((ViewCompat.MEASURED_STATE_MASK & i) >> 24)};
    }

    private byte[] a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(255);
        byte nextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = nextInt;
        i2 = i + 1;
        bArr[i] = nextInt2;
        return bArr;
    }

    private String b(String str) {
        Calendar instance = Calendar.getInstance();
        return String.format(str, new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5))});
    }

    private void b(int i) {
        byte[] a = a(i);
        for (int i2 = 0; i2 < 4; i2++) {
            this.M.add(Byte.valueOf(a[i2]));
        }
    }

    private void b(Location location) {
        c(location);
        g();
    }

    private void c() {
        if (!this.N) {
            this.N = true;
            d(c.d);
            i();
            d();
        }
    }

    private void c(int i) {
        if (i != 0) {
            try {
                RandomAccessFile randomAccessFile;
                File file = new File(h.a + "/grtcf.dat");
                if (!file.exists()) {
                    File file2 = new File(h.a);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (file.createNewFile()) {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(2);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.seek(8);
                        byte[] bytes = "1980_01_01:0".getBytes();
                        randomAccessFile.writeInt(bytes.length);
                        randomAccessFile.write(bytes);
                        randomAccessFile.seek(200);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.seek(800);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.close();
                    } else {
                        return;
                    }
                }
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(8);
                byte[] bytes2 = (b("%d_%02d_%02d") + ":" + i).getBytes();
                randomAccessFile.writeInt(bytes2.length);
                randomAccessFile.write(bytes2);
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
    }

    private void c(Location location) {
        if (System.currentTimeMillis() - this.a >= ((long) this.H) && location != null) {
            if (location != null && location.hasSpeed() && location.getSpeed() > this.E) {
                this.E = location.getSpeed();
            }
            try {
                if (this.M == null) {
                    this.M = new ArrayList();
                    h();
                    d(location);
                } else {
                    e(location);
                }
            } catch (Exception e) {
            }
            this.L++;
        }
    }

    private void c(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("on")) {
                    this.k = jSONObject.getInt("on");
                }
                if (jSONObject.has("bash")) {
                    this.l = jSONObject.getDouble("bash");
                }
                if (jSONObject.has("net")) {
                    this.m = jSONObject.getString("net");
                }
                if (jSONObject.has("tcon")) {
                    this.n = jSONObject.getInt("tcon");
                }
                if (jSONObject.has("tcsh")) {
                    this.o = jSONObject.getInt("tcsh");
                }
                if (jSONObject.has("per")) {
                    this.p = jSONObject.getInt("per");
                }
                if (jSONObject.has("chdron")) {
                    this.q = jSONObject.getInt("chdron");
                }
                if (jSONObject.has("spsh")) {
                    this.r = jSONObject.getDouble("spsh");
                }
                if (jSONObject.has("acsh")) {
                    this.s = jSONObject.getDouble("acsh");
                }
                if (jSONObject.has("stspsh")) {
                    this.t = jSONObject.getDouble("stspsh");
                }
                if (jSONObject.has("drstsh")) {
                    this.u = jSONObject.getInt("drstsh");
                }
                if (jSONObject.has("stper")) {
                    this.v = jSONObject.getInt("stper");
                }
                if (jSONObject.has("nondron")) {
                    this.w = jSONObject.getInt("nondron");
                }
                if (jSONObject.has("nondrper")) {
                    this.x = jSONObject.getInt("nondrper");
                }
                if (jSONObject.has("uptime")) {
                    this.z = jSONObject.getLong("uptime");
                }
                j();
            } catch (JSONException e) {
            }
        }
    }

    private void d() {
        String str = null;
        if (null == null) {
            str = "6.3.3";
        }
        String[] split = str.split("\\.");
        int length = split.length;
        this.J[0] = (byte) 0;
        this.J[1] = (byte) 0;
        this.J[2] = (byte) 0;
        this.J[3] = (byte) 0;
        if (length >= 4) {
            length = 4;
        }
        int i = 0;
        while (i < length) {
            try {
                this.J[i] = (byte) (Integer.valueOf(split[i]).intValue() & 255);
                i++;
            } catch (Exception e) {
            }
        }
        this.K = a(c.d + ":" + c.a().b);
    }

    private void d(Location location) {
        Object obj = null;
        this.e = System.currentTimeMillis();
        b((int) (this.e / 1000));
        b((int) (location.getLongitude() * 1000000.0d));
        b((int) (location.getLatitude() * 1000000.0d));
        if (location.hasBearing()) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        if (!location.hasSpeed()) {
            int i2 = 1;
        }
        if (obj2 > null) {
            this.M.add(Byte.valueOf((byte) 32));
        } else {
            this.M.add(Byte.valueOf((byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & -33)));
        }
        if (obj > null) {
            this.M.add(Byte.valueOf(Byte.MIN_VALUE));
        } else {
            this.M.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & TransportMediator.KEYCODE_MEDIA_PAUSE)));
        }
        this.b = location;
    }

    private void d(String str) {
        int i = 1;
        try {
            File file = new File(h.a + "/grtcf.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2);
                int readInt = randomAccessFile.readInt();
                randomAccessFile.seek(8);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.read(bArr, 0, readInt2);
                String str2 = new String(bArr);
                if (str2.contains(b("%d_%02d_%02d")) && str2.contains(":")) {
                    try {
                        String[] split = str2.split(":");
                        if (split.length > 1) {
                            this.y = Integer.valueOf(split[1]).intValue();
                        }
                    } catch (Exception e) {
                    }
                }
                while (i <= readInt) {
                    randomAccessFile.seek((long) (i * 2048));
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    str2 = new String(bArr);
                    if (str != null && str2.contains(str)) {
                        c(str2);
                        break;
                    }
                    i++;
                }
                randomAccessFile.close();
            }
        } catch (Exception e2) {
        }
    }

    private void e(Location location) {
        if (location != null) {
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            int longitude = (int) ((location.getLongitude() - this.b.getLongitude()) * 100000.0d);
            int latitude = (int) ((location.getLatitude() - this.b.getLatitude()) * 100000.0d);
            if (location.hasBearing()) {
                obj = null;
            } else {
                int i = 1;
            }
            if (location.hasSpeed()) {
                obj2 = null;
            } else {
                int i2 = 1;
            }
            if (longitude > 0) {
                obj3 = null;
            } else {
                int i3 = 1;
            }
            int abs = Math.abs(longitude);
            if (latitude > 0) {
                obj4 = null;
            } else {
                int i4 = 1;
            }
            int abs2 = Math.abs(latitude);
            if (this.L > 1) {
                this.c = null;
                this.c = this.b;
            }
            this.b = location;
            if (this.b != null && this.c != null && this.b.getTime() > this.c.getTime() && this.b.getTime() - this.c.getTime() < 5000) {
                long time = this.b.getTime() - this.c.getTime();
                float[] fArr = new float[2];
                Location.distanceBetween(this.b.getAltitude(), this.b.getLongitude(), this.c.getLatitude(), this.c.getLongitude(), fArr);
                double speed = (double) ((2.0f * (fArr[0] - (this.c.getSpeed() * ((float) time)))) / ((float) (time * time)));
                if (speed > ((double) this.F)) {
                    this.F = (float) speed;
                }
            }
            this.M.add(Byte.valueOf((byte) (abs & 255)));
            this.M.add(Byte.valueOf((byte) (abs2 & 255)));
            byte b;
            if (obj > null) {
                b = (byte) 32;
                if (obj4 > null) {
                    b = (byte) 96;
                }
                if (obj3 > null) {
                    b = (byte) (b | -128);
                }
                this.M.add(Byte.valueOf(b));
            } else {
                b = (byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & 31);
                if (obj4 > null) {
                    b = (byte) (b | 64);
                }
                if (obj3 > null) {
                    b = (byte) (b | -128);
                }
                this.M.add(Byte.valueOf(b));
            }
            if (obj2 > null) {
                this.M.add(Byte.valueOf(Byte.MIN_VALUE));
                return;
            }
            this.M.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & TransportMediator.KEYCODE_MEDIA_PAUSE)));
        }
    }

    private void e(String str) {
        try {
            File file = new File(h.a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(h.a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(2);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.seek(8);
                    byte[] bytes = "1980_01_01:0".getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes);
                    randomAccessFile.seek(200);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.seek(800);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(2);
            int readInt = randomAccessFile2.readInt();
            int i = 1;
            while (i <= readInt) {
                randomAccessFile2.seek((long) (i * 2048));
                int readInt2 = randomAccessFile2.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile2.read(bArr, 0, readInt2);
                if (new String(bArr).contains(c.d)) {
                    break;
                }
                i++;
            }
            if (i >= readInt) {
                randomAccessFile2.seek(2);
                randomAccessFile2.writeInt(i);
            }
            randomAccessFile2.seek((long) (i * 2048));
            byte[] bytes2 = str.getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception e) {
        }
    }

    private boolean e() {
        if (this.B) {
            if (this.C) {
                if (((double) this.E) < this.t) {
                    this.D += this.p;
                    if (this.D <= this.u || System.currentTimeMillis() - this.G > ((long) (this.v * 1000))) {
                        return true;
                    }
                }
                this.D = 0;
                this.C = false;
                return true;
            } else if (((double) this.E) >= this.t) {
                return true;
            } else {
                this.C = true;
                this.D = 0;
                this.D += this.p;
                return true;
            }
        } else if (((double) this.E) >= this.r || ((double) this.F) >= this.s) {
            this.B = true;
            return true;
        } else if (this.w == 1 && System.currentTimeMillis() - this.G > ((long) (this.x * 1000))) {
            return true;
        }
        return false;
    }

    private void f() {
        this.M = null;
        this.e = 0;
        this.L = 0;
        this.b = null;
        this.c = null;
        this.E = 0.0f;
        this.F = 0.0f;
    }

    private void g() {
        if (this.e != 0 && System.currentTimeMillis() - this.e >= ((long) (this.p * 1000))) {
            if (com.baidu.location.f.getServiceContext().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false)) {
                f();
            } else if (this.n == 1 && !e()) {
                f();
            } else if (!a(c.d, com.baidu.location.f.getServiceContext())) {
                f();
            } else if (this.M != null) {
                int size = this.M.size();
                this.M.set(0, Byte.valueOf((byte) (size & 255)));
                this.M.set(1, Byte.valueOf((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & size) >> 8)));
                this.M.set(3, Byte.valueOf((byte) (this.L & 255)));
                byte[] bArr = new byte[size];
                for (int i = 0; i < size; i++) {
                    bArr[i] = ((Byte) this.M.get(i)).byteValue();
                }
                if (Environment.getExternalStorageState().equals("mounted")) {
                    File file = new File(Environment.getExternalStorageDirectory(), "baidu/tempdata");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (file.exists()) {
                        File file2 = new File(file, "intime.dat");
                        if (file2.exists()) {
                            file2.delete();
                        }
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                            bufferedOutputStream.write(bArr);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            new Thread(this) {
                                final /* synthetic */ e a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a(new File(Environment.getExternalStorageDirectory() + "/baidu/tempdata", "intime.dat"), "http://itsdata.map.baidu.com/long-conn-gps/sdk.php");
                                }
                            }.start();
                        } catch (Exception e) {
                        }
                    }
                }
                f();
                this.G = System.currentTimeMillis();
            }
        }
    }

    private void h() {
        int i = 0;
        this.M.add(Byte.valueOf((byte) 0));
        this.M.add(Byte.valueOf((byte) 0));
        if (f.equals("0")) {
            this.M.add(Byte.valueOf((byte) 110));
        } else {
            this.M.add(Byte.valueOf((byte) 126));
        }
        this.M.add(Byte.valueOf((byte) 0));
        this.M.add(Byte.valueOf(this.J[0]));
        this.M.add(Byte.valueOf(this.J[1]));
        this.M.add(Byte.valueOf(this.J[2]));
        this.M.add(Byte.valueOf(this.J[3]));
        int length = this.K.length;
        this.M.add(Byte.valueOf((byte) ((length + 1) & 255)));
        while (i < length) {
            this.M.add(Byte.valueOf(this.K[i]));
            i++;
        }
    }

    private void i() {
        if (System.currentTimeMillis() - this.z > DateTimeUtils.ONE_DAY) {
            if (this.A == null) {
                this.A = new a(this);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(c.a().a(false));
            stringBuffer.append(com.baidu.location.a.a.a().c());
            this.A.a(stringBuffer.toString());
        }
        j();
    }

    private void j() {
    }

    public void a(final Location location) {
        if (!this.N) {
            c();
        }
        if (this.k != 1 || ((double) b.a().f()) >= this.l * 100.0d || !this.m.contains(com.baidu.location.f.c.a(com.baidu.location.f.c.a().e()))) {
            return;
        }
        if (this.n != 1 || this.y <= this.o) {
            this.I.post(new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    this.b.b(location);
                }
            });
        }
    }

    public void b() {
        if (this.N) {
            this.N = false;
            f();
        }
    }
}
