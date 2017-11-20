package com.example.dell.dictionary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dell on 2017/10/18.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mData;
    protected LayoutInflater mInflater;
    protected OnItemClickListener mOnClickListener;


    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mData = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        ViewHolder viewHolder = ViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }
    public abstract void convert(ViewHolder holder,T t);

    @Override
    public void onBindViewHolder(final ViewHolder holder,int position)
    {
        convert(holder,mData.get(position));

        if( mOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mOnClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View V){
                    mOnClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }



    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnClickListener = onItemClickListener; ;
    }

   /*public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
       // Collections.swap(mData,fromPosition,toPosition);
       // notifyItemMoved(fromPosition,toPosition);
    }*/

    public void onItemDissmiss(int position) {
        //移除数据
        /*mData.remove(position);
        notifyItemRemoved(position);*/
    }

  /* public  interface ItemTouchHelperAdapter {
       //数据交换
       void onItemMove(int fromPosition,int toPosition);
       //数据删除
       void onItemDissmiss(int position);
   }*/

}


