package com.example.last_version;

public class postTicket {

    String id, name, email, event;

    public postTicket(){

    }

public postTicket(String id, String name, String email, String event){
        this.id=id;
        this.name=name;
        this.email=email;
        this.event=event;
}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEvent() {
        return event;
    }
}
