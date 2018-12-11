package a59070103.kmitl.ac.th.mobilefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private EditText _userid,_name,_age,_pass,_quote;
    private  String userId,nameUser,passUser,qouteUser;
    private int ageUser;

    SharedPreferences shared;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        _userid = (EditText) getActivity().findViewById(R.id.inp_p_user);
        _name = (EditText) getActivity().findViewById(R.id.inp_p_name);
        _age = (EditText) getActivity().findViewById(R.id.inp_p_age);
        _pass = (EditText) getActivity().findViewById(R.id.inp_pass);

        shared = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        getuserInfo();

    }


    void getuserInfo(){
        userId = shared.getString("UserId","not_found");
        nameUser = shared.getString("name","not_found");
        ageUser = shared.getInt("age", -1);
        passUser = shared.getString("password","not_found");


    }


}
