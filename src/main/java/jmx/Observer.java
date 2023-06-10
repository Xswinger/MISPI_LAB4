package jmx;


import webdeving.dataBase.HitService;

public class Observer implements ObserverMBean {

    private String message = null;

    public Observer() {
        message = "Hello there";
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void sayHello() {
        System.out.println(message);
    }
}
