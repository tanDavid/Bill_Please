package sg.edu.rp.c346.id19002765.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {


    Button btnSpl, btnRes;
    EditText etAmt, etPax, etDiscount;
    ToggleButton tbtnGST, tbtnSVS;
    TextView tvTotal, tvPax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.editAmt);
        etPax = findViewById(R.id.editPax);
        etDiscount = findViewById(R.id.editDisc);
        btnSpl = findViewById(R.id.split);
        btnRes = findViewById(R.id.reset);
        tbtnGST = findViewById(R.id.Gst);
        tbtnSVS = findViewById(R.id.SVS);
        tvTotal = findViewById(R.id.totalB);
        tvPax = findViewById(R.id.EachP);

        btnSpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAmt.getText().toString().trim().length() == 0 ){
                    return;
                }
                if(etPax.getText().toString().trim().length() == 0){
                    return;
                }

                double amt = 0.00;
                if(!tbtnSVS.isChecked() && !tbtnGST.isChecked()){
                    amt = Double.parseDouble(etAmt.getText().toString());
                }
                else if(tbtnSVS.isChecked() && !tbtnGST.isChecked()){
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.10;
                }
                else if(!tbtnSVS.isChecked() && tbtnGST.isChecked()){
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.07;
                }

                else{
                    amt = Double.parseDouble(etAmt.getText().toString()) * 1.17;
                }

                if(etDiscount.getText().toString().trim().length() != 0){
                    double dis = Double.parseDouble(etDiscount.getText().toString().trim());
                    amt = amt * (1-dis/100);
                }


                tvTotal.setText("Total Amount: $" + String.format("%.2f",amt));

                int numPax = Integer.parseInt(etPax.getText().toString());

                tvPax.setText("Each Pays: $" + String.format("%.2f",amt/numPax));

            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmt.setText("");
            }
        });



    }
}
