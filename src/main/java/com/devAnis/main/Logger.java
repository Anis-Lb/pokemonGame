package com.devAnis.main;

public class Logger {
    private  String log;
    private static Logger instance;

    public Logger(){
        this.log = "";
    }

    public static Logger getInstance(){
        synchronized (Logger.class){
            if (instance==null){
                instance = new Logger();
            }
        }
        return instance;
    }

    public void setLog(String newLog){
        this.log = this.log + newLog;
    }

    public void init(){
        this.log ="";
    }

    public String getLog() {
        return log;
    }
}
