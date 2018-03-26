package com.lingan.edongspeechlibrary.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Done on 2018/3/26.
 */

public class AlarEvent extends DataSupport {

    private long evnetTime;
    private String timeStr;
    private String event;

    public AlarEvent(long evnetTime,String timeStr,String event){
        this.evnetTime = evnetTime;
        this.timeStr = timeStr;
        this.event = event;
    }
    public void setEvnetTime(long evnetTime){
        this.evnetTime = evnetTime;
    }
    public long getEvnetTime(){
        return evnetTime;
    }
    public void setTimeStr(String timeStr){
        this.timeStr = timeStr;
    }
    public String getTimeStr(){
        return timeStr;
    }
    public void setEvent(String event){
        this.event = event;
    }
    public String getEvent(){
        return event;
    }

}
