package io.lonmstalker.colearner.storage;

import io.lonmstalker.colearner.model.UserInfo;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Locale;

public class ThreadLocaleStorage {
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Locale> localeThreadLocale = ThreadLocal.withInitial(Locale::getDefault);

    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }

    public static void setUserInfo(@Nullable final UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static Locale getLocale() {
        return localeThreadLocale.get();
    }

    public static void setLocale(@Nullable final Locale locale) {
        localeThreadLocale.set(locale);
    }

    public static void clear() {
        userInfoThreadLocal.set(null);
        localeThreadLocale.set(Locale.getDefault());
    }
}
