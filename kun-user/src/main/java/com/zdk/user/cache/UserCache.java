package com.zdk.user.cache;

import com.zdk.redis.init.AbstractCache;
import org.springframework.stereotype.Component;

@Component
public class UserCache extends AbstractCache {
    @Override
    public void initCache() {
        super.initCache();
    }

    @Override
    public <T> T getCache(String key) {
        return super.getCache(key);
    }

    @Override
    public void clearCache() {
        super.clearCache();
    }

    @Override
    public void reloadCache() {
        super.reloadCache();
    }
}
