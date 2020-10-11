package  com.example.gymtracker.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gymtracker.R;

public class SettingsFragment extends Fragment
{
    private Button button;
    private Button payment;
    private Button password;
    private Button termsOfUse;
    private Button logOut;
    private Button contactUs;
    private Button version;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        contactUs = (Button)view.findViewById(R.id.contactUs);
        contactUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactUsInstagram.class);
                startActivity(intent);
            }
        });
        button = (Button)view.findViewById(R.id.contact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact.class);
                startActivity(intent);
            }
        });
        payment = (Button)view.findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaymentDetails.class);
                startActivity(intent);
            }
        });
        termsOfUse = view.findViewById(R.id.termsOfUse);
        termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TermsOfUse.class);
                startActivity(intent);
            }
        });
        password = (Button)view.findViewById(R.id.password);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResetPassword.class);
                startActivity(intent);
            }
        });
        logOut = (Button)view.findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LogOutAlert logOutAlert = new LogOutAlert();
                logOutAlert.show(getChildFragmentManager(), "Log Out alert");
            }
        });

        version = (Button) view.findViewById(R.id.version);
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutThisVersion.class);
                startActivity(intent);
            }
        });
        return view;
    }
}