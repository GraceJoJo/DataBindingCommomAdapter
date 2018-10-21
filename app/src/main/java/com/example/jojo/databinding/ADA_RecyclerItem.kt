package com.example.jojo.databinding

import android.content.Context
import com.example.jojo.databinding.databinding.ItemLayoutBinding
import com.example.jojo.databindingadapter.recyclerView.CommonAdapter
import com.example.jojo.databindingadapter.recyclerView.ViewHolder

/**
 * Created by JoJo on 2018/9/18.
 * wechat:18510829974
 * description:
 */
class ADA_RecyclerItem constructor(context: Context): CommonAdapter<DataBean, ItemLayoutBinding>(context) {
    override fun convert(viewBinding: ItemLayoutBinding?, holder: ViewHolder.BindingHolder?, bean: DataBean?, position: Int) {
        viewBinding!!.dataBean = bean
    }

    override fun itemLayoutId(): Int {
        return R.layout.item_layout
    }
}