package com.example.jojo.databindingadapter.listview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiItemTypeAdapterListView<T, B extends ViewDataBinding> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas = new ArrayList<>();

    private ItemViewDelegateManagerListView mItemViewDelegateManagerListView;
    private ViewDataBinding viewDataBinding;


    public MultiItemTypeAdapterListView(Context context) {
        this.mContext = context;
        mItemViewDelegateManagerListView = new ItemViewDelegateManagerListView();
    }

    public MultiItemTypeAdapterListView addItemViewDelegate(ItemViewDelegateListView<T, B> itemViewDelegateListView) {
        mItemViewDelegateManagerListView.addDelegate(itemViewDelegateListView);
        return this;
    }

    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManagerListView.getItemViewDelegateCount() > 0;
    }

    @Override
    public int getViewTypeCount() {
        if (useItemViewDelegateManager())
            return mItemViewDelegateManagerListView.getItemViewDelegateCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (useItemViewDelegateManager()) {
            int viewType = mItemViewDelegateManagerListView.getItemViewType(mDatas.get(position), position);
            return viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewDelegateListView itemViewDelegateListView = mItemViewDelegateManagerListView.getItemViewDelegate(mDatas.get(position), position);
        int layoutId = itemViewDelegateListView.getItemViewLayoutId();
        ViewHolderListView viewHolder = null;
        if (convertView == null) {
//            View itemView = LayoutInflater.from(mContext).inflate(layoutId, parent,
//                    false);
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutId, parent, false);
            viewHolder = new ViewHolderListView(mContext, viewDataBinding.getRoot(), parent, position);
            viewHolder.mLayoutId = layoutId;
            onViewHolderCreated(viewHolder, viewHolder.getConvertView());
        } else {
            viewHolder = (ViewHolderListView) convertView.getTag();
            viewHolder.mPosition = position;
        }

        convert((B) viewDataBinding, viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    protected void convert(B viewDataBinding, ViewHolderListView viewHolder, T item, int position) {
        mItemViewDelegateManagerListView.convert(viewDataBinding, viewHolder, item, position);
        //!!!!!重要!!!!!      加一行，解决adapter加了databinding页面刷新闪烁的问题
        viewDataBinding.executePendingBindings();
    }

    public void onViewHolderCreated(ViewHolderListView holder, View itemView) {
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
