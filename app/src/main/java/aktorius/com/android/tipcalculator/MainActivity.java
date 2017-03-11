package aktorius.com.android.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextBillAmount)
    EditText editBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tipPercent;
    @BindView(R.id.tvTipTotal)
    TextView tipAmount;
    @BindView(R.id.tvBillTotalAmount)
    TextView billTotalAmount;

    private float percentage = 0;
    private float tipTotal = 0;
    private float totalBillAmount = 0;

    private final float DEFAULT_TIP_PERCENT = 15;
    private final float REGULAR_TIP_PERCENT = 10;
    private final float EXCELLENT_TIP_PERCENT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    private void setTipValues() {

        tipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        billTotalAmount.setText(getString(R.string.main_msg_billtotalresult, totalBillAmount));
    }

    @OnClick({R.id.regularService, R.id.goodService, R.id.excellentService})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.regularService:
                percentage = REGULAR_TIP_PERCENT;
                break;
            case R.id.goodService:
                percentage = DEFAULT_TIP_PERCENT;
                break;
            case R.id.excellentService:
                percentage = EXCELLENT_TIP_PERCENT;
                break;
            default:
                percentage = 0;
        }

        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.editTextBillAmount)
    public void onTextChanged(){
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if (!editBillAmount.getText().toString().equals("") && !editBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(editBillAmount.getText().toString());
        else
            totalBillAmount = 0;

        tipTotal = (totalBillAmount*percentage)/100;
        totalBillAmount += tipTotal;
    }
}
