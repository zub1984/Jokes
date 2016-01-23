package com.jubair.jokes.backend;

/** The object model for the data we are sending through endpoints */
public class JokerBean {

    private String mJokes;

    public String getData() {
        return mJokes;
    }

    public void setData(String data) {
        mJokes = data;
    }
}