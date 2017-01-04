package com.kaneki.xim;

import com.kaneki.xim.netty.XServerLanuch;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author yueqian
 * @Desctription
 * @date 2016/12/13
 * @email yueqian@mogujie.com
 */

public class ServerInitListener implements InitializingBean {

    public void afterPropertiesSet() throws Exception {
        if (!XServerLanuch.getInstance().isServerStarted())
            XServerLanuch.getInstance().startXServer();
    }
}
