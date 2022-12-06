package me.fabio;

public class HttpUtils {

    public static boolean successful(int code) {
        return code == 200 || code == 201;
    }
}
