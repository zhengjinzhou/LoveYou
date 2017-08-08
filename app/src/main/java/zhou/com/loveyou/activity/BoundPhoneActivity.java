package zhou.com.loveyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import zhou.com.loveyou.R;
import zhou.com.loveyou.utils.DownTimeUtil;
import zhou.com.loveyou.utils.PhoneUtil;
import zhou.com.loveyou.utils.ToastUtil;

public class BoundPhoneActivity extends Activity {

    private static final int UPDATE_TEXT = 1;
    private EditText et_phone;
    private EditText et_verification;
    private TextView tv_get_verification;
    private Button bt_next;
    private String etPhone;
    private String etVerification;

    /**
     * 发送验证码UI更新消息机制
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    DownTimeUtil downTimeUtil = new DownTimeUtil(tv_get_verification, 60000, 1000);
                    downTimeUtil.start();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_phone);
        initUI();
        initData();

    }

    private void initData() {

        /**
         * 验证码业务逻辑
         */
        tv_get_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取手机号码
                etPhone = et_phone.getText().toString().trim();
                System.out.println("=====etPhonoe====="+ etPhone);
                if(etPhone.isEmpty()){
                    ToastUtil.show(getApplicationContext(),"手机号码不能为空");
                }else if(!PhoneUtil.isPhoneNumber(etPhone)){
                    ToastUtil.show(getApplicationContext(),"请输入正确的手机号码");
                }else{
                    // 将手机号码发送过去，返回验证码信息，点击下一步的时候将手机号码和验证码一起
                    // 发送到后台，后台对比这来给你个信息后返回数据确定是否登录成功
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            mHandler.sendMessage(message);
                        }
                    }).start();
                }
            }
        });

        /**
         * 下一步的业务逻辑
         */
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取验证码
                etVerification = et_verification.getText().toString().trim();
                System.out.println("=====etVerification====="+ etVerification);

                if(TextUtils.isEmpty(etPhone) || TextUtils.isEmpty(etVerification)){
                    ToastUtil.show(getApplicationContext(),"输入不能为空");
                }
                else {
                    //这里要发送手机号码和验证码到服务器，根据服务器返回的数据确定是否成功
                    String phone = "13631782148";
                    String verification = "123";
                    if(etPhone.equals(phone) && etVerification.equals(verification)){
                        Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        ToastUtil.show(getApplicationContext(),"验证码有误");
                    }
                }

            }
        });
    }

    private void initUI() {
        et_phone = findViewById(R.id.et_phone);
        et_verification = findViewById(R.id.et_verification);
        tv_get_verification = findViewById(R.id.tv_get_verification);
        bt_next = findViewById(R.id.bt_next);
    }
}
