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

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

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
        email = NavigationMainActivity.login_email;
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
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
           /* DBHelper db;
            db = new DBHelper(getApplicationContext());
            try {
                db.createDatabase();
                db.openDataBase();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            calculateDate();
            db.updateUsersSetGymId(branch, email);
            db.updateUsersSetMemDate(date_tracked, dateEnd, email); */

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

    public void calculateDate(){
        int curr_date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int curr_year = Calendar.getInstance().get(Calendar.YEAR);
        int curr_month = Calendar.getInstance().get(Calendar.MONTH);
        date_tracked=""+curr_year+"-"+curr_month+"-"+curr_date;
        if(curr_month > 11){
            curr_year += 1;
            curr_month = 1;
        }
        int end_month = curr_month+1;

        dateEnd=""+curr_year+"-"+end_month+"-"+curr_date;
    }
}
