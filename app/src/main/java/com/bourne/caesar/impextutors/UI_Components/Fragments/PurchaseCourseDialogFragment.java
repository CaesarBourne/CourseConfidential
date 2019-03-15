package com.bourne.caesar.impextutors.UI_Components.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.CheckoutPayActiviy;
import com.bourne.caesar.impextutors.UI_Components.Activities.OrderDetailsActivity;
import com.bourne.caesar.impextutors.Utilities.Constants;
import com.bourne.caesar.impextutors.Utilities.SharedPreferencesStorage;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseCourseDialogFragment extends DialogFragment {

    public static final String COURSE_NAIRA_PRICE = "naira";
    public static final String COURSE_DOLLAR_PRICE = "dollar";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_ID = "courseID";
    Button purchaseButton, cancelButton;
    TextView coursePriceView, coursePaymentFee, courserTotalPrice;
    public PurchaseCourseDialogFragment() {
        // Required empty public constructor
    }
    public static PurchaseCourseDialogFragment newDialogInstance(String  courseNairaPrice,String courseDollarPrice,
            String courseTitle, String courseID){
        Bundle arguments = new Bundle();
        arguments.putString(COURSE_NAIRA_PRICE, courseNairaPrice);
        arguments.putString(COURSE_DOLLAR_PRICE, courseDollarPrice);
        arguments.putString(COURSE_TITLE, courseTitle);
        arguments.putString(COURSE_ID, courseID);

        PurchaseCourseDialogFragment purchaseCourseDialogFragmentInstance = new PurchaseCourseDialogFragment();
        purchaseCourseDialogFragmentInstance.setArguments(arguments);
        return purchaseCourseDialogFragmentInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase_course_dialog, container, false);

        purchaseButton = view.findViewById(R.id.purchaseButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        coursePriceView = view.findViewById(R.id.coursePrice);
        coursePaymentFee = view.findViewById(R.id.coursepaymentFee);
        courserTotalPrice = view.findViewById(R.id.courseTotalPrice);

        final String courseNairaPriceString  = getArguments().getString(COURSE_NAIRA_PRICE);
        final String coursePriceDollarString = getArguments().getString(COURSE_DOLLAR_PRICE);

        final String courseTitleString  = getArguments().getString(COURSE_TITLE);
        final String courseIDString  = getArguments().getString(COURSE_ID);



        int integerFee = Integer.parseInt(courseNairaPriceString.trim());
        int totalFee = integerFee +100;

        Double totalDollar = Double.valueOf(coursePriceDollarString) + 0.28;
        if ((SharedPreferencesStorage.getSharedPrefInstance(getActivity()).getCurrency() == Constants.IMPEX_DOLLAR)){
            coursePriceView.setText(coursePriceDollarString);

            coursePaymentFee.setText("0.28");
            coursePriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
            coursePaymentFee.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
            courserTotalPrice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dollarsymbol,0,0,0);
           courserTotalPrice.setText(String.valueOf(totalDollar));
        }
        else {
            coursePriceView.setText(courseNairaPriceString);

            coursePaymentFee.setText("100");
            coursePriceView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.naira,0,0,0);
            coursePaymentFee.setCompoundDrawablesWithIntrinsicBounds(R.drawable.naira,0,0,0);
            courserTotalPrice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.naira,0,0,0);
            courserTotalPrice.setText(String.valueOf(totalFee));
        }


        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Intent intent = new Intent(getActivity(), CheckoutPayActiviy.class);
//                intent.putExtra(CheckoutPayActiviy.COURSE_CHECKPUT_PRICE, courseNairaPriceString);
//                intent.putExtra(CheckoutPayActiviy.COURSE_NAME, courseTitleString);
//                intent.putExtra( CheckoutPayActiviy.COURSE_ID, courseIDString);

                intent.putExtra(CheckoutPayActiviy.COURSE_CHECKPUT_PRICE,courseNairaPriceString);
                intent.putExtra(CheckoutPayActiviy.COURSE_DOLLAR_FEE, coursePriceDollarString);
                intent.putExtra(CheckoutPayActiviy.COURSE_NAME, courseTitleString);
                intent.putExtra(CheckoutPayActiviy.COURSE_ID, courseIDString);
                startActivity(intent);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
    }

}
