package com.util.ming.projectutil.designpattern.factorymethod.common;

/**
 * Created by ming on 17/10/10.
 */
public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mailsender!");
    }
}
