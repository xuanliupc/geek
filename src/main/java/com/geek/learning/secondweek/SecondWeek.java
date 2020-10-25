package com.geek.learning.secondweek;

import com.geek.learning.secondweek.component.*;

public class SecondWeek {
    public static void main(String[] args) {
        WinForm winForm = new WinForm("WINDOW窗口");

        Picture picture = new Picture("LOGO图片");
        winForm.addComponent(picture);
        Button signInBtn = new Button("登录");
        winForm.addComponent(signInBtn);
        Button signUpBtn = new Button("注册");
        winForm.addComponent(signUpBtn);

        Frame frame = new Frame("FRAME1");
        Label username = new Label("用户名");
        frame.addComponent(username);
        TextBox textBox = new TextBox("文本框");
        frame.addComponent(textBox);
        Label password = new Label("密码");
        frame.addComponent(password);
        PasswordBox passwordBox = new PasswordBox("密码框");
        frame.addComponent(passwordBox);
        CheckBox checkbox = new CheckBox("复选框");
        frame.addComponent(checkbox);
        TextBox readme = new TextBox("记住用户名");
        frame.addComponent(readme);
        LinkLabel linkLabel = new LinkLabel("忘记密码");
        frame.addComponent(linkLabel);
        winForm.addComponent(frame);

        winForm.print();
    }
}
