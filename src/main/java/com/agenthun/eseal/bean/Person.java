package com.agenthun.eseal.bean;

/**
 * Created by agenthun on 2017/2/27.
 */
public class Person {
    private String mUsername;
    private String mPassword;

    public Person() {
    }

    public Person(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return mUsername != null ? mUsername.equals(person.mUsername) : person.mUsername == null;
    }

    @Override
    public int hashCode() {
        return mUsername != null ? mUsername.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mUsername='" + mUsername + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
