package com.mygdx.game.ConnectServer;

import com.smartfoxserver.v2.exceptions.SFSException;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.IEventListener;

public class onConnection implements IEventListener {
    @Override
    public void dispatch(BaseEvent baseEvent) throws SFSException {
        boolean success = (boolean) baseEvent.getArguments().get("success");
        if(success){
            System.out.println("connection success");
        }else{
            System.out.println("connection fail");
        }
    }
}
