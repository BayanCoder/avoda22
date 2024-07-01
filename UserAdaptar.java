package com.example.avoda2;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.avoda2.databinding.UserItemBinding;
import com.example.avoda2.model.User;
import com.squareup.picasso.Picasso;
public class UserAdaptar extends ListAdapter<User, UserAdaptar.UserViewHolder>(UserDiffCallback())  {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        }

class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
        binding.userNameTextView.text = "${user.firstName} ${user.lastName}"
        binding.userLocationTextView.text = "${user.city}, ${user.country}"
        Picasso.get().load(user.picture).into(binding.userImageView)
        }
        }

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
        }
        }
}
