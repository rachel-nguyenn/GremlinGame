package gremlins;

import processing.core.PApplet;

public class Timer extends PApplet {

    int interval;
    int begin;
    int num = 0;

    public Timer(double intervalAsSecond) {
        double milliesTime = intervalAsSecond * 1000;
        this.interval = (int) milliesTime;
    }

    public void start() {
        this.begin = millis();
    }

    public boolean finish() {
        if (num == 0) {
            num++;
            return true;
        } 
        else {
            num++;
            int timePassed = millis() - begin;
            if (timePassed >= interval) {
                return true;
            } else {
                return false;
            }
        }
    }

    public double getInterval() {
        return this.interval;
    }

    public double getPassedTime() {
        return millis() - begin;
    }

    public void setIndex() {
        this.num = 1;
    }
}