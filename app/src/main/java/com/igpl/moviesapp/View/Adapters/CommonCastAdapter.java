package com.igpl.moviesapp.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.igpl.moviesapp.Models.Cast;
import com.igpl.moviesapp.R;
import com.igpl.moviesapp.Utils.Constants;

import java.util.List;

public class CommonCastAdapter extends RecyclerView.Adapter<CommonCastAdapter.ViewHolder> {

    List<Cast> castList;
    Context context;

    public CommonCastAdapter(Context context, List<Cast> castList) {
        this.castList = castList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_adapter_container, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cast cast = castList.get(position);

        holder.tv_castName.setText(cast.getOriginal_name());

        holder.tv_castRole.setText(cast.getName());

        Glide.with(context).load(Constants.ImageBaseURL + cast.getProfile_path())
                .fitCenter().apply(RequestOptions.circleCropTransform())
                .into(holder.iv_profile);

    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_profile;
        TextView tv_castName, tv_castRole;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_castName = itemView.findViewById(R.id.tv_castName);
            tv_castRole = itemView.findViewById(R.id.tv_castRole);
        }
    }
}
