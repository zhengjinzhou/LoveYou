package zhou.com.loveyou.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import zhou.com.loveyou.R;
import zhou.com.loveyou.utils.ContantsValue;
import zhou.com.loveyou.utils.SpUtil;
import zhou.com.loveyou.utils.ToastUtil;

public class DetailsActivity extends Activity implements View.OnClickListener{

    private static final int RESULT_DETAILS = 2 ;
    private Button bt_next_time;
    private Button bt_submit;
    private ImageView iv_detais_back;
    private EditText et_nickname;
    private Button bt_birthday;
    private TextView tv_birthday;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initUI();
        initCilick();

    }

    /**
     * 各个点击按钮事件this
     *
     * RadioGroup  性别选择
     *
     */
    private void initCilick() {
        bt_next_time.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        iv_detais_back.setOnClickListener(this);
        bt_birthday.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(radioButtonId);
                ToastUtil.show(getApplicationContext(),rb.getText()+"");
            }
        });
    }

    private void initUI() {
        bt_next_time = findViewById(R.id.bt_next_time);
        bt_submit = findViewById(R.id.bt_submit);
        iv_detais_back = findViewById(R.id.iv_detais_back);
        et_nickname = findViewById(R.id.et_nickname);
        bt_birthday = findViewById(R.id.bt_birthday);
        tv_birthday = findViewById(R.id.tv_birthday);
        radioGroup = findViewById(R.id.radioGroup);


        //判断b是否存在，如果怕存在，怎将该控件隐藏
        boolean b = SpUtil.getBoolean(getApplicationContext(), ContantsValue.LOGIN_OVER, false);
        if(b){
            bt_next_time.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.bt_birthday:
                //性别选择
                showDateDialog();
                break;
            case R.id.bt_next_time:
                intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                SpUtil.putBoolean(getApplicationContext(), ContantsValue.LOGIN_OVER,true);//用来判断是否进入过登录与注册界面
                finish();
                break;
            case R.id.bt_submit:
                boolean isDetail = SpUtil.getBoolean(getApplicationContext(), ContantsValue.LOGIN_OVER, false);
                //判断是否曾经进入过该界面，如果进入过，则点击的时候返回到DetailsActivity，否则返回到HomeActivity
                if(isDetail){
                    intent = new Intent();
                    intent.putExtra("ee","DetailsActivity");
                    setResult(RESULT_DETAILS,intent);
                    ToastUtil.show(getApplicationContext(),"修改信息成功");
                    finish();
                }else{
                    ToastUtil.show(getApplicationContext(),"修改信息成功");
                    intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                    SpUtil.putBoolean(getApplicationContext(), ContantsValue.LOGIN_OVER,true);//用来判断是否进入过登录与注册界面
                }
                break;
            case R.id.iv_detais_back:
                finish();
                break;
        }
    }

    /**
     * 选择生日
     * 使用DatePicker
     *
     * dialog弹出框
     */
    private void showDateDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("选择日期");
        dialog.setIcon(R.mipmap.fall_in_love);
        final LinearLayout layout_alert = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_dataselect, null);
        dialog.setView(layout_alert);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatePicker datepicker1 = layout_alert.findViewById(R.id.datepicker1);
                int year = datepicker1.getYear();
                int month = datepicker1.getMonth()+1;
                int dayOfMonth = datepicker1.getDayOfMonth();
                tv_birthday.setText(year+"-"+month+"-"+dayOfMonth);
            }
        });
        dialog.show();
    }
}
