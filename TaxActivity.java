package ca.roumani.taxcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaxActivity extends AppCompatActivity
{
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        linearLayout = (LinearLayout) findViewById(R.id.output);
    }


    public void buttonClicked(View view)
    {
        EditText editText = (EditText) findViewById(R.id.incomeBox);
        linearLayout.removeAllViews();
        TaxModel model = new TaxModel();
        model.setIncome(Double.parseDouble(editText.getText().toString()));
        addTextview("Income Tax:", model.getTax());
        addTextview("Average Rate:", model.getAverageRate());
        addTextview("Marginal Rate:", model.getMarginalRate());
        addTextview("CPP:", model.getCPP());
        addTextview("EI:", model.getEI());
    }

    private void addTextview(String in, String out){
        TextView taxLabel = new TextView(this);
        taxLabel.setText(in);
        linearLayout.addView(taxLabel);

        TextView taxOutput = new TextView(this);
        taxOutput.setText(out);
        linearLayout.addView(taxOutput);
    }
}
