package com.example.jojo.databinding_commonadapter

import android.content.Context
import com.example.jojo.databinding_commonadapter.databinding.ItemListBinding
import com.example.jojo.databindingadapter.recyclerView.CommonAdapter
import com.example.jojo.databindingadapter.recyclerView.ViewHolder

/**
 * Created by JoJo on 2018/9/18.
 * wechat:18510829974
 * description:
 */
class ADA_RecyclerItem constructor(context: Context): CommonAdapter<DataBean, ItemListBinding>(context) {
    override fun convert(viewBinding: ItemListBinding?, holder: ViewHolder.BindingHolder?, bean: DataBean?, position: Int) {
        viewBinding!!.rankBean = bean
    }

    override fun itemLayoutId(): Int {
        return R.layout.item_list
    }
}