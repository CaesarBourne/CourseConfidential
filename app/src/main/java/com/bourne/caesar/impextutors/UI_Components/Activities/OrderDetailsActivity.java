package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.bourne.caesar.impextutors.Utilities.FixedData;
import com.bourne.caesar.impextutors.Utilities.SharedPreferencesStorage;

public class OrderDetailsActivity extends AppCompatActivity {

    public static final String COURSE_PRICE_NAIRA = "nairaprice";
    public static final String COURSE_PRICE_DOLLAR = "dollarprice";
    public static final String COURSE_POSITION = "position";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_ID = "courseID";
    TextView courseInitialPriceView, taxPaystackView, totalCostView, courseTitleView, courseTopInitialPriceView;
    ImageView courseImageView;
    Button goToCheckoutPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        initialization();
        double nairaToDollarRate = 1;
        if (getIntent().getExtras() != null){
            int coursePosition = getIntent().getExtras().getInt(COURSE_POSITION);
            courseImageView.setImageResource(FixedData.CourseImages[coursePosition]);

            final String courseTitleString = getIntent().getExtras().getString(COURSE_TITLE);
            courseTitleView.setText(courseTitleString);

            final String courseIDString = getIntent().getExtras().getString(COURSE_ID);

            final String coursePriceNairaString = getIntent().getExtras().getString(COURSE_PRICE_NAIRA);
            final String coursePriceDollarString = getIntent().getExtras().getString(COURSE_PRICE_DOLLAR);

            int integerFee = Integer.parseInt(coursePriceNairaString.trim());
            int totalFee = integerFee +100;

            Double totalDollar = Double.valueOf(coursePriceDollarString) + 0.28;
            if ((SharedPreferencesStorage.getSharedPrefInstance(this).getCurrency() == Constants.IMPEX_DOLLAR)){
                courseInitialPriceView.setText(coursePriceDollarString);
                courseTopInitialPriceView.setText(coursePriceDollarString);

                taxPaystackView.setText("0.28");
                courseInitialPriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
                courseTopInitialPriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
                taxPaystackView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
                totalCostView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
                totalCostView.setText(String.valueOf(totalDollar));
            }
            else {
                courseInitialPriceView.setText(coursePriceNairaString);
                courseTopInitialPriceView.setText(coursePriceNairaString);
                taxPaystackView.setText("100");
                totalCostView.setText(String.valueOf(totalFee));
            }



//            double coursePriceDigit = Double.valueOf(coursePriceNairaString.trim());
//            final double totalCoursePrice = coursePriceDigit + nairaToDollarRate;
//            final String totalPriceString = String.valueOf(totalCoursePrice);
            goToCheckoutPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderDetailsActivity.this, CheckoutPayActiviy.class);
                    intent.putExtra(CheckoutPayActiviy.COURSE_CHECKPUT_PRICE,coursePriceNairaString);
                    intent.putExtra(CheckoutPayActiviy.COURSE_DOLLAR_FEE, coursePriceDollarString);
                    intent.putExtra(CheckoutPayActiviy.COURSE_NAME, courseTitleString);
                    intent.putExtra(CheckoutPayActiviy.COURSE_ID, courseIDString);
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
        courseTopInitialPriceView = findViewById(R.id.coursePrice);
    }

}
