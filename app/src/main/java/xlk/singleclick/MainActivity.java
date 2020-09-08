package xlk.singleclick;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import xlk.clicklibrary.SingleClick;

/**
 * @author xlk
 * @date 2020/8/31
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button test_btn1, test_btn2, test_btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        test_btn1 = (Button) findViewById(R.id.btn1);
        test_btn2 = (Button) findViewById(R.id.btn2);
        test_btn3 = (Button) findViewById(R.id.btn3);

        test_btn1.setOnClickListener(this);
        test_btn2.setOnClickListener(this);
        test_btn3.setOnClickListener(new View.OnClickListener() {

            @SingleClick
            @Override
            public void onClick(View v) {
                Log.d("singleClick", "按钮3被点击");
            }
        });
    }

    /**
     * 或者将 except = {R.id.btn2} 替换成 specify = {R.id.btn1}
     * 按钮1每1.5秒只可点击1次 且按钮2可以随意点击
     */
    @SingleClick(value = 1500, except = {R.id.btn2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Log.d("singleClick", "按钮1被点击");
                break;
            case R.id.btn2:
                Log.i("singleClick", "按钮2被点击");
                break;
            default:
                break;
        }
    }
}
