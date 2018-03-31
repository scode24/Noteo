package com.code.myspace.noteo;

/**
 * Created by myspace on 3/24/2018.
 */

public class NotesData {

    String userMail;
    String notes;
    String time;

    public NotesData() {
    }

    public NotesData(String userMail, String notes, String time) {
        this.userMail = userMail;
        this.notes = notes;
        this.time = time;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getNotes() {
        return notes;
    }

    public String getTime() {
        return time;
    }
}
