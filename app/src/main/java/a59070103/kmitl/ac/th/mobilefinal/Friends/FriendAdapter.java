package a59070103.kmitl.ac.th.mobilefinal.Friends;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import a59070103.kmitl.ac.th.mobilefinal.R;

public class FriendAdapter extends ArrayAdapter<Friend> {

    ArrayList<Friend> friendsts;
    Context context;

    public FriendAdapter(Context context, int resouce, ArrayList<Friend> objects){
        super(context, resouce, objects);
        this.friendsts = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.fragment_friend_item,
                parent,
                false);


        TextView textId = view.findViewById(R.id.text_id);
        TextView textName = view.findViewById(R.id.text_name);
        TextView textEmail = view.findViewById(R.id.text_email);
        TextView textPhone = view.findViewById(R.id.text_phone);
        TextView textWebsite = view.findViewById(R.id.text_website);

        Friend row = friendsts.get(position);
        textId.setText(""+row.getId());
        textName.setText(""+ row.getName());
        textEmail.setText(""+ row.getEmail());
        textPhone.setText(""+ row.getPhone());
        textWebsite.setText(""+ row.getWebsite());
        return view;
    }
}
