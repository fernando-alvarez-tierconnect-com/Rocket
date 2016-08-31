package com.trucentrix.test;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 8:19 AM
 * To change this template use File | Settings | File Templates.
 */

import com.thoughtworks.selenium.Wait;

import java.util.Date;

public abstract class AjaxWait extends Wait {
    public AjaxWait(String onFailText) {
        this.wait(onFailText);
    }

    public AjaxWait(String onFailText, long timeOut) {
        this.wait(onFailText, timeOut);
    }

    public AjaxWait(String onFailText, long timeOut, long interval) {
        this.wait(onFailText, timeOut, interval);
    }

    public static void ajaxWait(long timeOut) {
        try {
            System.out.println("1 Date = " + new Date());
            Thread.sleep(timeOut);
            System.out.println("2 Date = " + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
