package com.example.project5236;

public class Light {
    int cord1;
    int cord2;
    int cord3;
    private boolean isOn;

    public Light(int c1, int c2, int c3){
        cord1 = c1;
        cord2 = c2;
        cord3 = c3;
        isOn = true;
    }

    public void switchLight(){
        isOn = !isOn;
    }
}
