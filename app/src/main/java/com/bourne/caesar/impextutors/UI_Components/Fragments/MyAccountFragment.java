package com.bourne.caesar.impextutors.UI_Components.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bourne.caesar.impextutors.R;
import com.bourne.caesar.impextutors.TasksCore.ChangeUserPassword;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    private ChangeUserPassword changeUserPassword;
    private Button changePasswordView;
    private AlertDialog.Builder changePasswordDialog;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeUserPassword = new ChangeUserPassword(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        initialization(view);
        viewsAction();
        return view;
    }



    private void initialization(View view) {
        changePasswordView = view.findViewById(R.id.changePasswordView);
        changePasswordDialog = new AlertDialog.Builder(getActivity());

    }
    private void viewsAction() {
        changePasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangePasswordDialog();

            }
        });
    }

    private void startChangePasswordDialog() {
        changePasswordDialog.setIcon(R.drawable.impexlogo);
        changePasswordDialog.setTitle("Change Password?");

        changePasswordDialog.setMessage("Are you Sure you want to change your password?");
        changePasswordDialog.setCancelable(false);
        changePasswordDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeUserPassword.sendResetPasswordEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            }
        });
        changePasswordDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        changePasswordDialog.show();
    }

    public void passwordResetEmailSent(){
        Toast.makeText(getActivity(), "The password reset email has being sent, check your email to change", Toast.LENGTH_LONG).show();
    }
}
