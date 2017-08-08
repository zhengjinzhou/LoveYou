package zhou.com.loveyou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import zhou.com.loveyou.R;

public class AboutActivity extends Activity {

    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initUI();
        initClick();
    }

    private void initClick() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initUI() {
        iv_back = findViewById(R.id.iv_about_back);
    }
}
