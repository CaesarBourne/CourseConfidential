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

import com.bourne.caesar.impextutors.MainActivity;
import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.UI_Components.Activities.CheckoutPayActiviy;
import com.bourne.caesar.impextutors.UI_Components.Activities.ProgramFeaturesActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseCourseDialogFragment extends DialogFragment {

    public static final String COURSE_PRICE = "price";
    public static final String COURSE_TITLE = "title";
    Button purchaseButton, cancelButton;
    TextView coursePriceView;
    public PurchaseCourseDialogFragment() {
        // Required empty public constructor
    }
    public static PurchaseCourseDialogFragment newDialogInstance(String  courcePrice, String courseTitle){
        Bundle arguments = new Bundle();
        arguments.putString(COURSE_PRICE, courcePrice);
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
        final String coursePriceString  = getArguments().getString(COURSE_PRICE);
        final String courseTitleString  = getArguments().getString(COURSE_TITLE);
        coursePriceView.setText(coursePriceString);


        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckoutPayActiviy.class);
                intent.putExtra(CheckoutPayActiviy.COURSE_CHECKPUT_PRICE, coursePriceString);
                intent.putExtra(CheckoutPayActiviy.COURSE_ID, courseTitleString);
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
