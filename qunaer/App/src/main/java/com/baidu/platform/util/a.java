package com.baidu.platform.util;

import com.baidu.platform.comjni.util.AppMD5;
import java.util.LinkedHashMap;
import java.util.Map;

public class a implements ParamBuilder<a> {
    protected Map<String, String> a;

    public a a(String str, String str2) {
        if (this.a == null) {
            this.a = new LinkedHashMap();
        }
        this.a.put(str, str2);
        return this;
    }

    public String a() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        String str = new String();
        int i = 0;
        String str2 = str;
        for (String str3 : this.a.keySet()) {
            str = AppMD5.encodeUrlParamsValue((String) this.a.get(str3));
            str = i == 0 ? str2 + str3 + "=" + str : str2 + "&" + str3 + "=" + str;
            i++;
            str2 = str;
        }
        return str2;
    }
}
