package com.easemob.chatuidemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.easemob.chat.EMContactManager;
import com.easemob.chatuidemo.R;
import com.easemob.exceptions.EaseMobException;

@SuppressLint("NewApi") public class SendYanZheng extends Dialog implements
        android.view.View.OnClickListener {
    private EditText editText;
    private Button send_btn;
    private ImageView back;
    private Context context;
    private String s;
    private String toAddUsername;
    private ProgressDialog progressDialog;

    public SendYanZheng(Context context, String toAddUsername) {
        super(context, android.R.style.Theme);
        this.context = context;
        this.toAddUsername = toAddUsername;
        setOwnerActivity((Activity) context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendyanzheng);
        init();
    }

    public void init() {
        editText = (EditText) findViewById(R.id.edit_send);
        send_btn = (Button) findViewById(R.id.sendyanzheng);
        back = (ImageView) findViewById(R.id.send_back);
        send_btn.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @SuppressLint("NewApi") public void sendMes() {
        s = editText.getText().toString();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(context, "消息不能为空", 1).show();
        } else {
            try {
                EMContactManager.getInstance().addContact(toAddUsername, s);
                Builder mBuilder=new Builder(context);
                Notification notification = mBuilder.build();
                notification.flags = Notification.DEFAULT_SOUND;
                dismiss();
                Toast.makeText(context, "消息已发送给" + s + "请等待回复", 1).show();
            } catch (EaseMobException e) {
                e.printStackTrace();
                Toast.makeText(context, "发送失败：" + e.getMessage(), 1).show();
            }
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
        case R.id.send_back:
            dismiss();
            break;
        case R.id.sendyanzheng:
            sendMes();
            break;
        }

    }

}
