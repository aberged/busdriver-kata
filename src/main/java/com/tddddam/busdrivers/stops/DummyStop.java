package com.tddddam.busdrivers.stops;

public class DummyStop {
    final private String title;
    public DummyStop(String s){
        this.title = "Bus stop# "+s;
    }

    @Override
    public String toString(){
        return title;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DummyStop) && obj.toString().equals(title);
    }
}
