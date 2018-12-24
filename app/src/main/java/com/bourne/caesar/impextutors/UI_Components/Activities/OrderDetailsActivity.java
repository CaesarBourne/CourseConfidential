package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.FixedData;

public class OrderDetailsActivity extends AppCompatActivity {

    public static final String COURSE_PRICE = "price";
    public static final String COURSE_POSITION = "position";
    public static final String COURSE_TITLE = "title";
    TextView courseInitialPriceView, taxPaystackView, totalCostView, courseTitleView;
    ImageView courseImageView;
    Button goToCheckoutPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initialization();
        double nairaToDollarRate = 0.00278;
        if (getIntent().getExtras() != null){
            int coursePosition = getIntent().getExtras().getInt(COURSE_POSITION);
            courseImageView.setImageResource(FixedData.CourseImages[coursePosition]);

            String courseTitleString = getIntent().getExtras().getString(COURSE_TITLE);
            courseTitleView.setText(courseTitleString);

            String coursePriceString = getIntent().getExtras().getString(COURSE_PRICE);
            courseInitialPriceView.setText(coursePriceString);
            double coursePriceDigit = Double.valueOf(coursePriceString.trim());
            final double totalCoursePrice = coursePriceDigit + nairaToDollarRate;
            goToCheckoutPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderDetailsActivity.this, CheckoutPayActiviy.class);
                    intent.putExtra(CheckoutPayActiviy.COURSE_CHECKPUT_PRICE, totalCoursePrice);
                    startActivity(intent);
                }
            });
        }

    }

    private void initialization() {
        courseInitialPriceView = findViewById(R.id.coursePriceSubtotalView);
        taxPaystackView = findViewById(R.id.taxAndPaystack);
        totalCostView = findViewById(R.id.totalCourseFee);
        goToCheckoutPay = findViewById(R.id.goToCheckoutPay);
        courseTitleView = findViewById(R.id.CourseTitle);
        courseImageView = findViewById(R.id.courseImage);
    }

}
