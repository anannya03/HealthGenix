package  com.example.gymtracker.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gymtracker.R;
import com.example.gymtracker.Welcome;

import java.util.List;

public class SettingsFragment extends Fragment
{
    ListView listView;
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
        final String[] settings = {"Reset Password", "Contact", "Support Us", "About This Version", "Terms Of Use", "Log Out" };
        listView = (ListView) view.findViewById(R.id.lv);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, settings);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getActivity(), ResetPassword.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(getActivity(), Contact.class);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent = new Intent(getActivity(), ContactUsInstagram.class);
                    startActivity(intent);
                }
                if(position==3){
                    Intent intent = new Intent(getActivity(), AboutThisVersion.class);
                    startActivity(intent);
                }
                if(position==4){
                    Intent intent = new Intent(getActivity(), TermsOfUse.class);
                    startActivity(intent);
                }
                if(position==5){
                    showPopup();
                }
            }
        });
        return view;
    }

    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener()                 {

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }
                }).setNegativeButton("Cancel", null);

        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    private void logout() {
        startActivity(new Intent(getActivity().getApplicationContext(), Welcome.class));

    }
}