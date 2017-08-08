package zhou.com.loveyou.activity;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import zhou.com.loveyou.R;
import zhou.com.loveyou.fragment.HomeFragment;
import zhou.com.loveyou.fragment.MeFragment;
import zhou.com.loveyou.fragment.TaskFragment;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * 这里应该是不在修改了，在HomeFragment改吧
     */

    private TextView tv_home;
    private TextView tv_task;
    private TextView tv_me;
    private HomeFragment hmoeFragment;
    private MeFragment meFragment;
    private TaskFragment taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();

        //设置默认的Fragment
        setDefaultFragment();
    }

    /**
     * 设置默认的Fragment
     */
    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        hmoeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_content, hmoeFragment);
        fragmentTransaction.commit();
    }

    private void initUI() {
        tv_home =  (TextView) findViewById(R.id.tv_home);
        tv_task = (TextView)findViewById(R.id.tv_task);
        tv_me = (TextView)findViewById(R.id.tv_me);

        tv_home.setOnClickListener(this);
        tv_task.setOnClickListener(this);
        tv_me.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.tv_home:
                Toast.makeText(this,"home",Toast.LENGTH_LONG).show();
                if(hmoeFragment==null){
                    hmoeFragment = new HomeFragment();
                }
                transaction.replace(R.id.fragment_content,hmoeFragment);
                break;
            case R.id.tv_me:
                Toast.makeText(this,"me",Toast.LENGTH_LONG).show();
                if(meFragment == null){
                    meFragment = new MeFragment();
                }
                transaction.replace(R.id.fragment_content,meFragment);
                break;
            case R.id.tv_task:
                Toast.makeText(this,"task",Toast.LENGTH_LONG).show();
                if(taskFragment==null){
                    taskFragment = new TaskFragment();
                }
                transaction.replace(R.id.fragment_content,taskFragment);
                break;
        }
        //提交事物
        transaction.commit();
    }
}

