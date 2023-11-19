package com.zdk.redis.init;

import com.zdk.redis.util.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;


/**
 * 实现CommandLineRunner接口,并重写run方法
 */
@Component
public class InitCache implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        //获取需要预先缓存的类,获取到Map<beanName,beanType>
        ApplicationContext applicationContext= SpringContextUtil.getApplicationContext();
        //这里的AbstractCache是个抽象类,也是父类,获取到它的
        Map<String, AbstractCache> beanMap = applicationContext.getBeansOfType(AbstractCache.class);
        //调用init方法
        if(beanMap.isEmpty()){
            return;
        }
        //遍历map集合
        for(Map.Entry<String,AbstractCache> entry : beanMap.entrySet()){
            //得到的是一个子类,用父类去接,调用子类的方法
            AbstractCache abstractCache = (AbstractCache) SpringContextUtil.getBean(entry.getValue().getClass());
            abstractCache.initCache();
        }

    }
}
