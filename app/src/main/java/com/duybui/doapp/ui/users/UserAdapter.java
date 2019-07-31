package com.duybui.doapp.ui.users;

import android.app.Activity;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.duybui.doapp.R;
import com.duybui.doapp.data.model.User;
import com.duybui.doapp.databinding.ItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Activity activity;
    private List<User> userList = new ArrayList<>();

    public UserAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemUserBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.item_user,
                viewGroup, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int i) {
        User user = userList.get(i);
        holder.bindData(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData(List<User> data) {
        userList.clear();
        userList.addAll(data);
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding itemUserBinding;

        public UserViewHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
        }

        public void bindData(User user) {
            itemUserBinding.setUser(user);
        }
    }
}
