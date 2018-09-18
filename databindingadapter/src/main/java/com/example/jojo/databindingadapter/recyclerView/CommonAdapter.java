package com.example.jojo.databindingadapter.recyclerView;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhy on 16/4/9.
 * 结合了DataBinding使用的RecyclerView的CommonAdapter-加上databinding页面删除数据，和刷新数据时，会出现闪烁的问题，还未解决
 * 备注：lv包下的是ListView装配时使用的通用Adapter，使用见lv包下的CommonAdapterListView的备注信息
 */
public abstract class CommonAdapter<T, B extends ViewDataBinding> extends MultiItemTypeAdapter<T, B> {
    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(context);
//        mLayoutId = layoutId;
//        mDatas = datas;
        //返回：MultiItemTypeAdapter
        addItemViewDelegate(new ItemViewDelegate<T, B>() {
            @Override
            public int getItemViewLayoutId() {
                return itemLayoutId();
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(B viewBinding, ViewHolder.BindingHolder holder, T t, int position) {
                CommonAdapter.this.convert(viewBinding, holder, t, position);
            }
        });
    }

    protected abstract int itemLayoutId();

    protected abstract void convert(B viewBinding, ViewHolder.BindingHolder holder, T bean, int position);

    /**
     * 设置适配器的数据，添加数据
     *
     * @param dataList
     */
    public void update(List<T> dataList) {
        if (dataList != null) {
            mDatas.addAll(dataList);
        }

        notifyDataSetChanged();
    }

    /**
     * 设置适配器数据
     *
     * @param dataList
     * @param isClear  是否需要清空list然后在加载数据
     */
    public void update(List<T> dataList, Boolean isClear) {
        if (isClear) {
            clear();
        }
        if (dataList != null) {
            mDatas.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    /**
     * 清除集合数据
     */
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mDatas != null && mDatas.size() > 0) {
            // 数据移除
            this.mDatas.remove(position);
            // 界面移除
            notifyItemRemoved(position - 1);
            // 刷新位置
            if (position != (mDatas.size())) { // 如果移除的是最后一个，忽略
                notifyItemRangeChanged(position - 1, getItemCount() - position);
            }
        }
    }
    public void removeItem(int position) {
        if (mDatas != null && mDatas.size() > 0) {
//            // 数据移除
//            this.mDatas.remove(position);
//            // 界面移除
//            notifyItemRemoved(position);
//            // 刷新位置
//            if (position != (mDatas.size())) { // 如果移除的是最后一个，忽略
//                notifyItemRangeChanged(position, getItemCount() - position);
//            }
            mDatas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mDatas.size());
        }
    }

    public List<T> getDataList() {
        return mDatas;
    }

    /**
     * 清空集合，设置适配器数据
     *
     * @param list
     */
    public void setDataList(Collection<T> list) {
        this.mDatas.clear();
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }
}
