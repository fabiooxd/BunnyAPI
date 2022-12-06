package me.fabio.objects;

import me.fabio.HttpUtils;

public record Delivery(String message, int code, Object object, Class<?> type) {

    public boolean isSuccessful() {
        return HttpUtils.successful(code);
    }

    @SuppressWarnings("unchecked")
    public <T> T toObject() {
        return (T) type.cast(object);
    }
}
