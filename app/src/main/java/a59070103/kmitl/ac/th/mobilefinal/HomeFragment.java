package a59070103.kmitl.ac.th.mobilefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import a59070103.kmitl.ac.th.mobilefinal.Friends.FriendFragment;

public class HomeFragment extends Fragment {

    String userId,nameUser,userAge;
    SharedPreferences shared;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        shared = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);

        getuserInfo();


        final Button linkFriend = getActivity().findViewById(R.id.link_friend);
        linkFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FriendFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });

        final Button linkProfile = getActivity().findViewById(R.id.link_profile);
        linkProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });




        final Button logoutBtn = getActivity().findViewById(R.id.btn_logout);

        final TextView txtName = getActivity().findViewById(R.id.text_name);

        txtName.setText(nameUser);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();




                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }


    void getuserInfo(){
        userId = shared.getString("UserId","not_found");
        nameUser = shared.getString("name","not_found");

    }
}
