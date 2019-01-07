package com.example.scollex.jobbu;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    Context mContext;
    List<JobClass> mData;
    Dialog myDialog;
    Button favbutton;

    public RecyclerViewAdapter(Context mContext, List<JobClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.home_card_view, parent,false);
        final myViewHolder vHolder = new myViewHolder(v);

        //Dialog initial
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.listview_layout);

        vHolder.jobList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView dialog_JobImg = (ImageView) myDialog.findViewById(R.id.viewImg);
                TextView dialog_JobName = (TextView) myDialog.findViewById(R.id.textView_jobName);
                TextView dialog_JobLocation = (TextView) myDialog.findViewById(R.id.textView_jobLocation);
                TextView dialog_JobSalary = (TextView) myDialog.findViewById(R.id.textView_jobSalary);
                TextView dialog_JobDesc = (TextView) myDialog.findViewById(R.id.textView_jobDesc);
                TextView dialog_JobType = (TextView) myDialog.findViewById(R.id.textView_jobType);
                TextView dialog_JobTime = (TextView) myDialog.findViewById(R.id.textView_jobTime);

                dialog_JobImg.setImageResource(mData.get(vHolder.getAdapterPosition()).getJobThumbnail());
                dialog_JobName.setText(mData.get(vHolder.getAdapterPosition()).getJobName());
                dialog_JobLocation.setText(mData.get(vHolder.getAdapterPosition()).getJobLocation());
                dialog_JobSalary.setText(mData.get(vHolder.getAdapterPosition()).getSalary());
                dialog_JobDesc.setText(mData.get(vHolder.getAdapterPosition()).getJobDesc());
                dialog_JobType.setText(mData.get(vHolder.getAdapterPosition()).getJobType());
                dialog_JobTime.setText(mData.get(vHolder.getAdapterPosition()).getJobWorkingTime());

                myDialog.show();
            }

        });
      //  favbutton = (Button) v.findViewById(R.id.btnFav);
       // if(v==favbutton){
       //     Toast.makeText(mContext, "Favourite Added", Toast.LENGTH_SHORT).show();
       // }


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        holder.jobimg.setImageResource(mData.get(position).getJobThumbnail());
        holder.jobTitle.setText(mData.get(position).getJobName());
        holder.jobLocation.setText(mData.get(position).getJobLocation());
        holder.jobSalary.setText(mData.get(position).getSalary());
        holder.jobDesc.setText(mData.get(position).getJobDesc());
        holder.jobType.setText(mData.get(position).getJobType());
        holder.jobTime.setText(mData.get(position).getJobWorkingTime());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        private TextView jobTitle;
        private TextView jobSalary;
        private TextView jobDesc;
        private TextView jobType;
        private TextView jobTime;
        private TextView jobLocation;
        private LinearLayout jobList;
        private ImageView jobimg;

        public myViewHolder(View itemView) {
            super(itemView);

           jobList = (LinearLayout) itemView.findViewById(R.id.cardjoblist);
           jobTitle = (TextView) itemView.findViewById(R.id.cardjobname);
           jobLocation = (TextView) itemView.findViewById(R.id.cardjoblocation);
           jobSalary = (TextView) itemView.findViewById(R.id.cardjobSalary);
           jobDesc = (TextView) itemView.findViewById(R.id.cardjobDesc);
           jobType = (TextView) itemView.findViewById(R.id.cardjobType);
            jobTime = (TextView) itemView.findViewById(R.id.cardjobTime);
            jobimg = (ImageView) itemView.findViewById(R.id.jobicon);
        }
    }

}
