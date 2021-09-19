package com.smartheard.paymentgateway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Checkout.preload(getApplicationContext())

        payButton.setOnClickListener {
            makePayment()
        }
    }

    private fun makePayment() {
        val co = Checkout()
        try {
            val options = JSONObject()
            options.put("name", "Learning Worldz")
            options.put("description", "Learning Worldz Payment")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount", "50000")//pass amount in currency subunits

//            val retryObj = JSONObject()
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", "aliya.mirza1264@gmail.com")
            prefill.put("contact", "8299704945")

            options.put("prefill", prefill)
            co.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Success: ", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment failed : ", Toast.LENGTH_SHORT).show()
    }
}