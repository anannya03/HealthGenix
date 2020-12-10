package com.example.gymtracker.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentActivityOneMonth extends Activity implements PaymentResultListener {
    private static final String TAG = PaymentActivityOneMonth.class.getSimpleName();
    String branch;
    String email;
    String date_tracked;
    String dateEnd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_one_month);
        //setContentView();
        Bundle bundle= getIntent().getExtras();
        branch= bundle.getString("Branch");
        email= bundle.getString("Email");
        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());
        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        TextView privacyPolicy = (TextView) findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {
        //checkout.setKeyID("<YOUR_KEY_ID>");
        /*

          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "HealthGenix");
            options.put("description", "Buy Gym Membership");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "300000");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "healthgenix@gmail.com");
            preFill.put("contact", "9009931900");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }
    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {

            int curr_date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int curr_year = Calendar.getInstance().get(Calendar.YEAR);
            int curr_month = Calendar.getInstance().get(Calendar.MONTH);
            date_tracked= "2020-12-11";
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Calendar c= Calendar.getInstance();
            c.setTime(sdf.parse(date_tracked));
            date_tracked= sdf.format(c.getTime());
            c.add(Calendar.DATE, 30);
            dateEnd= sdf.format(c.getTime());
            Intent intent= new Intent(PaymentActivityOneMonth.this, OrderConfirmation.class);
            intent.putExtra("email", email);
            intent.putExtra("date_start", date_tracked);
            intent.putExtra("date_end", dateEnd);
            startActivity(intent);
           } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}
