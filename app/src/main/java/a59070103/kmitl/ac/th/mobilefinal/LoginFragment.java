package a59070103.kmitl.ac.th.mobilefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import a59070103.kmitl.ac.th.mobilefinal.Model.User;
import a59070103.kmitl.ac.th.mobilefinal.SQLite.MyDB;

public class LoginFragment extends Fragment {

    private MyDB myDB;
    private EditText _user,_pass;
    private String userStr,passStr;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myDB = new MyDB(getActivity());


        //Check currentUser Login


        final SharedPreferences shared = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);



        String userId = shared.getString("UserId","not_found");
        String name = shared.getString("name","not_found");


        if(!userId.equals("not_found")){

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new HomeFragment())
                    .addToBackStack(null)
                    .commit();



        }



        _user = getActivity().findViewById(R.id.inp_user);
        _pass = getActivity().findViewById(R.id.inp_pass);














        final Button loginbtn = getActivity().findViewById(R.id.btn_login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userStr = _user.getText().toString();
                passStr = _pass.getText().toString();


                if(userStr.isEmpty() || passStr.isEmpty()){
                    Toast.makeText(getActivity(), "กรอกข้อมูลไม่ครบ", Toast.LENGTH_SHORT).show();
                } else {


                    User user = new User(userStr,passStr);
                    User checkLogin = myDB.checkLogin(user);

                    if(checkLogin==null){
                        Toast.makeText(getActivity(), "Usesharedrname หรือ Password ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                    } else  {

                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("UserId", checkLogin.getUserid());
                        editor.putString("name", checkLogin.getName());
                        editor.putInt("age", checkLogin.getAge());
                        editor.putString("password", checkLogin.getPassword());
                        editor.commit();


                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new HomeFragment())
                                .addToBackStack(null)
                                .commit();
                    }

                }



            }
        });



        final TextView registerLink = getActivity().findViewById(R.id.link_register);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
