package com.bourne.caesar.impextutors.UI_Components.OnboardingAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.impextutors.R;

public class SliderAdapter extends PagerAdapter {

    LayoutInflater layoutInflater;
    Context newcontext;
    public String [] SliderTitles = {"Home", "E-College", "International", "Certification", "   Learn"};

    public SliderAdapter(Context newcontext) {
        this.newcontext = newcontext;
    }

    public String[] SliderPageDescription = {
            "Welcome to the Trade Professional Development App",
            "This App is an e-College of International Trade",
            "This is a one stop shop for all your need on International Trade Education",
            "Get on board the trade certification from the American Institute of Extended Studies",
            "Learn trade and get certified from America from the comfort of your room," +
                    " You only come to write your examination"
    };
    public int sliderImages[] = {R.drawable.slideone, R.drawable.slidetwo,R.drawable.slidethree, R.drawable.slidefour, R.drawable.slidefive};

    @Override
    public int getCount() {
        return SliderPageDescription.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) newcontext.getSystemService(newcontext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideimageView);
        TextView slideTitle = (TextView) view.findViewById(R.id.slidTitle);
        TextView slideDescription = (TextView) view.findViewById(R.id.slideDescription);

        slideImageView.setImageResource(sliderImages[position]);
        slideTitle.setText(SliderTitles[position]);
        slideDescription.setText(SliderPageDescription[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
