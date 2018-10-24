# DataBindingCommomAdapter
结合DataBinding的万能Adapter，使代码更简洁，快速提高开发效率的通用Adapter 

Add it in your root build.gradle at the end of repositories:

《一》添加依赖：<p>
(1)Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
(2） Add the dependency
```
dependencies {
	        implementation 'com.github.GraceJoJo:DataBindingBaseAdapter:1.0'
	}
```
《二》代码中使用：
(1)RecyclerView的Adapter

```
class ADA_RecyclerItem constructor(context: Context): CommonAdapter<DataBean, ItemLayoutBinding>(context) {
    override fun convert(viewBinding: ItemLayoutBinding?, holder: ViewHolder.BindingHolder?, bean: DataBean?, position: Int) {
        viewBinding!!.dataBean = bean
    }

    override fun itemLayoutId(): Int {
        return R.layout.item_layout
    }
```

(2)ListView或者GridView的Adapter
```
class ADA_ListItem constructor(context: Context): CommonAdapterListView<DataBean, ItemLayoutBinding>(context) {

    override fun convert(viewBinding: ItemLayoutBinding?, holder: ViewHolderListView?, bean: DataBean?, position: Int) {
        viewBinding!!.dataBean = bean
    }

    override fun itemLayoutId(): Int {
        return R.layout.item_layout
    }
}
```

《二》实现效果：<p>
![image](https://upload-images.jianshu.io/upload_images/3828835-b45fc454c54187ee.gif?imageMogr2/auto-orient/strip)<p>
《三》优点：<p>
(1)在布局中就把view的数据绑定工作全做好了。<p>
(2)在Adapter中可以省略大量重复的设置数据的代码<p>
(3)使得代码更加简洁，一目了然，易于维护。
