package com.mygdx.game.ConnectServer;



import sfs2x.client.SmartFox;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.util.ConfigData;

public class ConnectionToServer {

    SmartFox smartFox = new SmartFox();
    public void Connect(){
        ConfigData config = new ConfigData();
        config.setZone("shooter");
        config.setHost("localhost");
        config.setPort(9933);
        smartFox.connect(config);

        smartFox.addEventListener(SFSEvent.CONNECTION,new onConnection());
    }
}
