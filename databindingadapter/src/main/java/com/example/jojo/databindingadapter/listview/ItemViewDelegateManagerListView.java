package com.example.jojo.databindingadapter.listview;

import android.databinding.ViewDataBinding;
import android.support.v4.util.SparseArrayCompat;


/**
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManagerListView<T, B extends ViewDataBinding> {
    SparseArrayCompat<ItemViewDelegateListView<T, B>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount() {
        return delegates.size();
    }

    public ItemViewDelegateManagerListView<T, B> addDelegate(ItemViewDelegateListView<T, B> delegate) {
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManagerListView<T, B> addDelegate(int viewType, ItemViewDelegateListView<T, B> delegate) {
        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An ItemViewDelegateListView is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegateListView is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManagerListView<T, B> removeDelegate(ItemViewDelegateListView<T,B> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegateListView is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManagerListView<T, B> removeDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegateListView<T, B> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateListView added that matches position=" + position + " in data source");
    }

    public void convert(ViewDataBinding viewDataBinding, ViewHolderListView holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegateListView<T, B> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position)) {
                delegate.convert((B) viewDataBinding, holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManagerListView added that matches position=" + position + " in data source");
    }


    public int getItemViewLayoutId(int viewType) {
        return delegates.get(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegateListView itemViewDelegateListView) {
        return delegates.indexOfValue(itemViewDelegateListView);
    }

    public ItemViewDelegateListView getItemViewDelegate(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegateListView<T, B> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateListView added that matches position=" + position + " in data source");
    }

    public int getItemViewLayoutId(T item, int position) {
        return getItemViewDelegate(item, position).getItemViewLayoutId();
    }
}
