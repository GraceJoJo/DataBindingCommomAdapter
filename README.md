# DataBindingCommomAdapter
结合DataBinding的万能Adapter，使代码更简洁，快速提高开发效率的通用Adapter 

《一》使用方法：
(1)RecyclerView的Adapter

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
(2)ListView或者GridView的Adapter
```
class ADA_MineList constructor(context: Context) : CommonAdapterListView<MineBean>(context) {
    override fun convert(viewDataBinding: ViewDataBinding?, holder: ViewHolderListView?, bean: MineBean?, position: Int) {
        var viewBinding = viewDataBinding as ItemMineListBinding
        viewBinding.mineBean = bean
    }

    override fun itemLayoutId(): Int {
        return R.layout.item_mine_list
    }

}
```
《二》实现效果：
[example.gif](https://upload-images.jianshu.io/upload_images/3828835-b45fc454c54187ee.gif?imageMogr2/auto-orient/strip)
《三》优点：<p>
(1)在布局中即把view的数据绑定工作全做好了。<p>
(2)在Adapter中可以省略大量的代码，使得代码更加简洁，一目了然，易于维护。
