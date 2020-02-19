package com.manas.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.RoutesViewHolder> {

    Context mContext;
    List<Route>mRoute;

    public RoutesAdapter(Context mContext, List<Route> mRoute) {
        this.mContext = mContext;
        this.mRoute = mRoute;
    }




    @NonNull
    @Override
    public RoutesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.list_of_routes,viewGroup,false);
        return new RoutesViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutesViewHolder holder, int position) {

        String route = mRoute.get(position).getFrom() + " - " + mRoute.get(position).getTo();
        holder.fromToTV.setText(route);
        holder.distanceTV.setText(mRoute.get(position).getDictance());
        holder.timeTV.setText(mRoute.get(position).getArrive_time());
        holder.dateTV.setText(mRoute.get(position).getDate());
        holder.priceTV.setText(mRoute.get(position).getPrice());





    }

    @Override
    public int getItemCount() {
        return mRoute.size();
    }

    public class RoutesViewHolder extends RecyclerView.ViewHolder{

        TextView fromToTV, distanceTV, dateTV, timeTV, priceTV;
        public RoutesViewHolder(@NonNull View itemView) {
            super(itemView);
            fromToTV = itemView.findViewById(R.id.fromToTV);
            distanceTV = itemView.findViewById(R.id.distanceTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            timeTV = itemView.findViewById(R.id.timeTV);
            priceTV = itemView.findViewById(R.id.priceTV);
        }
    }

}


/*
*
*

    Context mContext;
    List<Route>mData;


    public RoutesAdapter(Context mContext, List<Route> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.list_of_routes, parent,false);

        return new RoutesViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutesViewHolder routesViewHolder, int position) {

        routesViewHolder.des.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class RoutesViewHolder extends RecyclerView.ViewHolder{

        TextView des;
         public RoutesViewHolder(@NonNull View itemView) {
            super(itemView);
            des = itemView.findViewById(R.id.desTextView);
        }
    }

*
* */