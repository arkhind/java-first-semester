package com.mipt.nikitaarkhipov;

public abstract class Worker {
    public abstract void work(int value);

    public boolean goHome(String s1, String s2) {
        return s1.equals(s2);
    }
}
