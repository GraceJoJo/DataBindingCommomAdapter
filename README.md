# DataBindingCommomAdapter
结合DataBinding的万能Adapter，使代码更简洁，快速提高开发效率的通用Adapter 

使用方法：
（1）RecyclerView的Adapter

```
class ADA_ChapterFilter constructor(context: Context): CommonAdapter<DataBean, ItemLayoutBinding>(context) {
    override fun convert(viewBinding: ItemLayoutBinding?, holder: ViewHolder.BindingHolder?, bean: DataBean?, position: Int) {
        viewBinding!!.dataBean= bean
    }
    override fun itemLayoutId(): Int {
        return R.layout.item_layout
    }
}
```
