package io.lonmstalker.colearner.storage;

import io.lonmstalker.colearner.model.UserInfo;
import lombok.experimental.UtilityClass;
import org.checkerframework.checker.nullness.qual.Nullable;

@UtilityClass
public class ThreadLocaleStorage {
    private final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }

    public static void setUserInfo(@Nullable final UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static void clear() {
        userInfoThreadLocal.set(null);
    }
}
