package a59070103.kmitl.ac.th.mobilefinal.Friends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import a59070103.kmitl.ac.th.mobilefinal.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FriendFragment extends Fragment {

    ArrayList<Friend> friendsList;
    private ListView showList;
    private OkHttpClient client;
    private FriendAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        friendsList = new ArrayList<>();
        adapter = new FriendAdapter(getActivity(), R.layout.fragment_friend_item, friendsList);

        showList = getActivity().findViewById(R.id.friend_list);
        showList.setAdapter(adapter);

        friendsList.clear();


        String url = "https://jsonplaceholder.typicode.com/users";
        getFriendView(url);




    }


    private void getFriendView(String url){


        Request request = new Request.Builder()
                .url(url)
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String res = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(res);
                                Gson gson = new Gson();

                                Log.d("TEST22",""+jsonArray.toString());

//                                TypeToken<List<Friend>> token = new TypeToken<List<Friend>>(){};
//                                ArrayList<Friend> postData = gson.fromJson(jsonArray.toString(), token.getType());
//                                friendsList.addAll(postData);
//                                adapter.notifyDataSetChanged();
//                                for(Friend item : friendsList){
//                                    Log.d("TEST22",item.getName()+"\n");
//                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }

            }
        });



    }
}
