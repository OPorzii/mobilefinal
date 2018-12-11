package a59070103.kmitl.ac.th.mobilefinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import a59070103.kmitl.ac.th.mobilefinal.Model.User;
import a59070103.kmitl.ac.th.mobilefinal.SQLite.MyDB;

public class RegisterFragment extends Fragment {


    private MyDB myDB;
    private int ageInt;

    private EditText _inpUserid, _inpName, _inpPass, _inpAge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myDB = new MyDB(getActivity());

        initRegisterBtn();


    }


    void initRegisterBtn() {

        final Button regisBtn = getActivity().findViewById(R.id.btn_register);
        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                _inpUserid = getActivity().findViewById(R.id.inp_regis_user);
                _inpName = getActivity().findViewById(R.id.inp_regis_name);
                _inpAge = getActivity().findViewById(R.id.inp_regis_age);
                _inpPass = getActivity().findViewById(R.id.inp_regis_pass);


                String userStr = _inpUserid.getText().toString();
                String nameStr = _inpName.getText().toString();
                String ageStr = _inpAge.getText().toString();
                String passStr = _inpPass.getText().toString();


                if (userStr.isEmpty() || nameStr.isEmpty() || ageStr.isEmpty() || passStr.isEmpty()) {
                    Toast.makeText(getActivity(), "Don't Empty Fill", Toast.LENGTH_SHORT).show();
                } else {


                    if (passStr.length() <= 6) {
                        Toast.makeText(getActivity(), "รหัสต้องยาวกว่า 6 ตัวอักษร", Toast.LENGTH_SHORT).show();

                    } else if(userStr.length() < 6 && userStr.length() > 12) {
                        Toast.makeText(getActivity(), "User ต้องมีขนาด 6-12 ตัวอักษร", Toast.LENGTH_SHORT).show();

                    } else if (ageStr.matches("[0-9]+")) {
                        Toast.makeText(getActivity(), "ใส่อายุเป็นตัวเลขเเท่านั้น", Toast.LENGTH_SHORT).show();
                        ageInt = Integer.parseInt(ageStr);
                        if(ageInt >= 10 && ageInt <= 80){
                            Toast.makeText(getActivity(), "ใส่ช่วง 10-80 เท่านั้น", Toast.LENGTH_SHORT).show();
                        }



                    } else if(!nameStr.contains(" ")){
                            Toast.makeText(getActivity(), "ต้องใส่ ชื่อ เว้นวรรค นามสกุล", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = new User(userStr, passStr, nameStr, ageInt);
                        long result = myDB.registerUser(user);


                        if (result == -1) {
                            Toast.makeText(getActivity(), "Error Register", Toast.LENGTH_SHORT).show();
                        } else {

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.main_view, new LoginFragment())
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }


                }

            }
        });


    }
}
