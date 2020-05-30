package com.example.something.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SPUtils {
    private static volatile SPUtils singleton;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    private SPUtils(Context context, String FILE_NAME) {
        this.sp = context.getSharedPreferences(FILE_NAME, 0);
        this.editor = this.sp.edit();
    }

    public static void init(Context context) {
        if (singleton == null) {
            singleton = new SPUtils(context, "Preferences");
        }

    }

    public static SPUtils getInstance() {
        if (singleton == null) {
            throw new IllegalStateException("Must call init() before using the store");
        } else {
            return singleton;
        }
    }

    public String get(String key) {
        return (String)this.get(key, "");
    }

    public <T> T get(String key, T def) {
        key = this.encrypt(key);
        if (def instanceof String) {
            String val = this.sp.getString(key, (String)def);
            return (T) (TextUtils.isEmpty(val) ? "" : this.decrypt(val));
        } else if (def instanceof Integer) {
            Integer val = this.sp.getInt(key, (Integer)def);
            return (T) val;
        } else if (def instanceof Boolean) {
            Boolean val = this.sp.getBoolean(key, (Boolean)def);
            return (T) val;
        } else if (def instanceof Float) {
            Float val = this.sp.getFloat(key, (Float)def);
            return (T) val;
        } else if (def instanceof Long) {
            Long val = this.sp.getLong(key, (Long)def);
            return (T) val;
        } else {
            return def;
        }
    }

    private String encrypt(String data) {
        try {
            DESKeySpec keySpec = new DESKeySpec("com.sir.library.com".getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key);
            String encode = Base64.encodeToString(cipher.doFinal(data.getBytes("UTF-8")), 0);
            return encode;
        } catch (Exception var7) {

            return data;
        }
    }

    private String decrypt(String data) {
        try {
            DESKeySpec keySpec = new DESKeySpec("com.sir.library.com".getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            byte[] encryptedWithoutB64 = Base64.decode(data, 0);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key);
            byte[] plainTextPwdBytes = cipher.doFinal(encryptedWithoutB64);
            return new String(plainTextPwdBytes);
        } catch (Exception var8) {

            return data;
        }
    }

    public <T> T getEntity(String key, Class<T> classOfT) {
        String json = this.sp.getString(this.encrypt(key), "");
        return (new Gson()).fromJson(json, classOfT);
    }

    public <T> void put(String key, T object) {
        key = this.encrypt(key);
        if (object instanceof String) {
            this.editor.putString(key, this.encrypt((String)object));
        } else if (object instanceof Integer) {
            this.editor.putInt(key, (Integer)object);
        } else if (object instanceof Boolean) {
            this.editor.putBoolean(key, (Boolean)object);
        } else if (object instanceof Float) {
            this.editor.putFloat(key, (Float)object);
        } else if (object instanceof Long) {
            this.editor.putLong(key, (Long)object);
        } else {
            this.editor.putString(key, (new Gson()).toJson(object));
        }

        this.editor.commit();
    }

    public void remove(String key) {
        this.editor.remove(this.encrypt(key));
        this.editor.commit();
    }

    public void clearAll() {
        this.editor.clear();
        this.editor.commit();
    }

    public boolean contains(String key) {
        return this.sp.contains(this.encrypt(key));
    }
}
