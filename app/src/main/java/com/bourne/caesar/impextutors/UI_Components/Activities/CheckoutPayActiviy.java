package com.bourne.caesar.impextutors.UI_Components.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.impextutors.R;
import com.google.firebase.auth.FirebaseAuth;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class CheckoutPayActiviy extends AppCompatActivity {

    public static final String COURSE_CHECKPUT_PRICE = "payprice";
    private Button payButton;
    private Card myCard;
    private Charge charge;
    private AutoCompleteTextView userNameField, emailField, cityField, stateField, nameOnCardField;
    private EditText  cardNumberField, expiryMonthField, expiryYearField, cvvField;
    String email, cardNumber,  cvv;
    TextView courseAmountField;
    private View mProgressView;
    LinearLayout linearLayoutParent;
    int expiryMonth, expiryYear, amountInKobo;
    int newAmountInNaira;
    private static final int VISA = 11;
    private static final int MASTERCARD = 22;
    private static final int VERVE = 33;
    NotificationCompat.Builder notification;
    FirebaseAuth authenticatedUser;
    private static final int uniqueId = 646843;

    private static final String VISA_CARD_PREFIX = "4";
    private static final String MASTER_CARD_PREFIX = "51,52,53,54,55,";
    private static final String VERVE_CARD_PREFIX = "50,65,";

    private ImageView visaCardView, masterCardView, verveCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaystackSdk.initialize(this);
        setContentView(R.layout.activity_checkout_pay_activiy);
        initialization();

        String courseAmountString;
        if (getIntent().getExtras() != null){
            courseAmountString = getIntent().getExtras().getString(COURSE_CHECKPUT_PRICE);
            courseAmountField.setText(courseAmountString);
        }
        authenticatedUser = FirebaseAuth.getInstance();
        String usernameString = authenticatedUser.getInstance().getCurrentUser().getDisplayName();
        nameOnCardField.setText(usernameString);
        userNameField.setText(usernameString);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()){
                    Toast.makeText(CheckoutPayActiviy.this, "Input data is wrong", Toast.LENGTH_LONG).show();
                    return;
                }
                try {

                    email = emailField.getText().toString().trim();
                    cardNumber = cardNumberField.getText().toString().trim();
                    expiryMonth = Integer.parseInt(expiryMonthField.getText().toString().trim());
                    expiryYear = Integer.parseInt(expiryYearField.getText().toString().trim());
                    cvv = cvvField.getText().toString().trim();
                    amountInKobo = Integer.parseInt(courseAmountField.getText().toString().trim());

                    myCard = new Card(cardNumber, expiryMonth, expiryYear, cvv);

                    if (myCard.isValid()){
                        Toast.makeText(CheckoutPayActiviy.this, "Your card is valid, processing.....", Toast.LENGTH_LONG).show();
                        chargeCard();
                    }else {
                        Toast.makeText(CheckoutPayActiviy.this, "Your card details are not valid", Toast.LENGTH_LONG).show();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
//                Intent intent = new Intent(CheckoutPayActiviy.this, PaaymentSuccessfulActivity.class);
//                startActivity(intent);
            }
        });

        cardNumberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String source = s.toString();
                int stringLength = source.length();

                if (stringLength > 4){
                    if (cardType(source) == VISA){
                        visaCardView.setVisibility(View.VISIBLE);
                        masterCardView.setVisibility(View.INVISIBLE);
                        verveCardView.setVisibility(View.INVISIBLE);


                    }
                    else if (cardType(source) == MASTERCARD){
                        visaCardView.setVisibility(View.INVISIBLE);
                        masterCardView.setVisibility(View.VISIBLE);
                        verveCardView.setVisibility(View.INVISIBLE);

                    }
                    else if (cardType(source) == VERVE){
                        visaCardView.setVisibility(View.INVISIBLE);
                        masterCardView.setVisibility(View.INVISIBLE);
                        verveCardView.setVisibility(View.VISIBLE);

                    }
                    else {
                        visaCardView.setVisibility(View.GONE);
                        masterCardView.setVisibility(View.GONE);
                        verveCardView.setVisibility(View.GONE);
                        Toast.makeText(CheckoutPayActiviy.this, "CARD NOT IDENTIFIED", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private int cardType(String source) {
        if (source.substring(0,1).equals(VISA_CARD_PREFIX)){

            return VISA;
        }else if (MASTER_CARD_PREFIX.contains(source.substring(0, 2) + ",")){

            return MASTERCARD;
        }else if (VERVE_CARD_PREFIX.contains(source.substring(0, 2) + ",")){

            return VERVE;
        }else {
            return 0;
        }
    }


    private void chargeCard() {
        showProgress(true);
        newAmountInNaira = amountInKobo * 100;
        charge = new Charge();
        charge.setEmail(email);
        charge.setAmount(newAmountInNaira);//amountInNaira
        charge.setCard(myCard);

        PaystackSdk.chargeCard(CheckoutPayActiviy.this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {

                showProgress(false);
                String paymentReference = transaction.getReference();
                Toast.makeText(CheckoutPayActiviy.this, "Transaction Successful! payment reference: "
                        + paymentReference, Toast.LENGTH_LONG).show();
//                if (statusListenerChild != null){
//                    statusListenerChild.status(paymentReference, "your order is been processed...");
//                    HomeFragment.startHomefragment(TestPayStack.this);
//                }
                Intent intent = new Intent(CheckoutPayActiviy.this, PaaymentSuccessfulActivity.class);
                intent.putExtra(PaaymentSuccessfulActivity.TRANSACTION_ID, "Your order would be delivered shortly thanks for patronage...");

                PendingIntent pendingIntent = PendingIntent.getActivity(CheckoutPayActiviy.this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                notification.setTicker("your Order was Succesful");
                notification.setContentTitle("Impex Tutor");
                notification.setContentText("Payment succesful ");
                notification.setWhen(System.currentTimeMillis());
                notification.setSmallIcon(R.drawable.impexlogo);
                notification.setLights(Color.BLUE, 500, 500);
                long[] pattern ={500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500};
                notification.setVibrate(pattern);
                notification.setStyle(new NotificationCompat.InboxStyle());
                Uri alarmsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notification.setSound(alarmsound);
                notification.setContentIntent(pendingIntent);
                notification.setAutoCancel(true);

                NotificationManager notmanage = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notmanage.notify(uniqueId, notification.build());


                Intent finishedintent = new Intent(CheckoutPayActiviy.this, PaaymentSuccessfulActivity.class);
//                finishedintent.putExtra(OrderActivity.EXTRA_SUCCESFUL_MESSAGE, "Your order would be delivered within 2 weeks...");
                startActivity(finishedintent);


            }

            @Override
            public void beforeValidate(Transaction transaction) {
                showProgress(false);
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                showProgress(false);
                Toast.makeText(CheckoutPayActiviy.this, "Error sorry given as "+ error.getMessage()+
                        " second one =>  " +transaction.getReference() + " na the errors", Toast.LENGTH_LONG
                ).show();

            }
        });
    }

    private void initialization() {
        notification = new NotificationCompat.Builder(this);
        linearLayoutParent  = findViewById(R.id.linearLayoutParent);
        mProgressView = findViewById(R.id.login_progress);
        courseAmountField = findViewById(R.id.checkoutPrice);
        payButton = findViewById(R.id.paybutton);
        userNameField = findViewById(R.id.nameField);
        cityField =findViewById(R.id.cityfIELD);
        stateField = findViewById(R.id.stateField);
        nameOnCardField = findViewById(R.id.nameOnCardField);
        cardNumberField = findViewById(R.id.cardnumberField);
        expiryMonthField = findViewById(R.id.cardmonthexpiry);
        expiryYearField= findViewById(R.id.cardyearexpiry);
        cvvField = findViewById(R.id.cardCVV);
        emailField = findViewById(R.id.emailField);
        visaCardView = findViewById(R.id.visacardimage);
        masterCardView = findViewById(R.id.mastercardimage);
        verveCardView = findViewById(R.id.vervecardimage);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("You must input email");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String city = cityField.getText().toString();
        if (TextUtils.isEmpty(city)) {
            cityField.setError("You must input email");
            valid = false;
        } else {
            cityField.setError(null);
        }
        String cardNumber = cardNumberField.getText().toString();
        if (TextUtils.isEmpty(cardNumber)) {
            cardNumberField.setError("card number required");
            valid = false;
        } else {
            cardNumberField.setError(null);
        }


        String expiryMonth = expiryMonthField.getText().toString();
        if (TextUtils.isEmpty(expiryMonth)) {
            expiryMonthField.setError("field required");
            valid = false;
        } else {
            expiryMonthField.setError(null);
        }

        String expiryYear = expiryYearField.getText().toString();
        if (TextUtils.isEmpty(expiryYear)) {
            expiryYearField.setError("Required.");
            valid = false;
        } else {
            expiryYearField.setError(null);
        }

        String cvv = cvvField.getText().toString();
        if (TextUtils.isEmpty(cvv)) {
            cvvField.setError("cvv is required");
            valid = false;
        } else {
            cvvField.setError(null);
        }

        return valid;
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            linearLayoutParent.setVisibility(show ? View.GONE : View.VISIBLE);
            linearLayoutParent.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    linearLayoutParent.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            linearLayoutParent.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
