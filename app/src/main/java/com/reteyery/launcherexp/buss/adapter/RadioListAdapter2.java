package com.reteyery.launcherexp.buss.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.reteyery.launcherexp.R;
import com.reteyery.launcherexp.base.BaseRvAdapter;
import com.reteyery.launcherexp.base.BaseRvHolder;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemClickListener;
import com.reteyery.launcherexp.base.interfaces.LauncherOnItemLongClickListener;

import fm.qingting.qtsdk.entity.ChannelProgram;

/**
 * 测试BaseRvAdapter和BaseRvHolder使用
 */
public class RadioListAdapter2 extends BaseRvAdapter<ChannelProgram, RadioListAdapter2.RadioListHolder>{

    @Override
    public void onBindViewData(RadioListHolder holder, int position, ChannelProgram entity) {
        holder.tvTitle.setText(entity.getTitle());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "onRvItemClick+++++++++++++++", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RadioListHolder createViewHolder(View view, Context context, LauncherOnItemClickListener itemClickListener, LauncherOnItemLongClickListener itemLongClickListener) {
        return new RadioListHolder(view, context, itemClickListener, itemLongClickListener);
    }

    @Override
    public int getLayout(ViewGroup parent, int viewType) {
        return R.layout.item_radio_list;
    }

    @Override
    public int getItemCount() {
        return getDatas().size();
    }

    static class RadioListHolder extends BaseRvHolder {
        TextView tvTitle;
        ConstraintLayout constraintLayout;
        public RadioListHolder(View itemView, Context context, LauncherOnItemClickListener itemClickListener, LauncherOnItemLongClickListener itemLongClickListener) {
            super(itemView, context, itemClickListener, itemLongClickListener);
            tvTitle = itemView.findViewById(R.id.tv_title);
            constraintLayout = itemView.findViewById(R.id.cl_layout);
        }
    }
}
