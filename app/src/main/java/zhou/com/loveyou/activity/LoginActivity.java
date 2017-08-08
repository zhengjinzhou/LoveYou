package zhou.com.loveyou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zhou.com.loveyou.R;
import zhou.com.loveyou.utils.ContantsValue;
import zhou.com.loveyou.utils.SpUtil;
import zhou.com.loveyou.utils.ToastUtil;

public class LoginActivity extends Activity {

    private EditText et_login_account;
    private EditText et_login_password;
    private Button bt_login_ensure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取
        initUI();
        //确定登录验证
        initLogin();

    }

    private void initLogin() {
        bt_login_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginAccount = et_login_account.getText().toString().trim();
                String loginPassword = et_login_password.getText().toString().trim();
                if(TextUtils.isEmpty(loginAccount) || TextUtils.isEmpty(loginPassword)){
                    ToastUtil.show(getApplicationContext(),"输入不能为空");
                }else{
                    //获取网络请求，匹对账号和密码
                    //requestData();
                    //获取到账号
                    String number = "201441413102";
                    //获取到密码
                    String pwd = "123";
                    if(loginAccount.equals(number) && loginPassword.equals(pwd)){
                        //保存密码
                        SpUtil.putString(getApplicationContext(), ContantsValue.PASSWORD,loginPassword);
                        Intent intent = new Intent(getApplicationContext(),BoundPhoneActivity.class);
                        startActivity(intent);
                        finish();
                        ToastUtil.show(getApplicationContext(),"登录成功");
                    }else{
                        ToastUtil.show(getApplicationContext(),"账号或密码错误");
                    }
                }
            }
        });
    }

    /**
     * 获取网络请求
     */
    private void requestData() {
        //这里获取获取账号和
    }

    private void initUI() {
        et_login_account = findViewById(R.id.et_login_account);
        et_login_password = findViewById(R.id.et_login_password);
        bt_login_ensure = findViewById(R.id.bt_login_ensure);
    }
}
