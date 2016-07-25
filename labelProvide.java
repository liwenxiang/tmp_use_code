package com.huawei.seq.table;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

//-------------文件名：TableViewerLabelProvider.java-------------------
//标签器。如果说内容器是对输入表格的数据集作处理，那么标签器则是对数据集中的单个实体对象
//进行处理和转化，由标签器来决定实体对象中的字段显示在表格的哪一列中
public class labelProvide implements ITableLabelProvider {
	//创建几个图像

	//由此方法决定数据记录在表格的每一列显示什么文字。 element参数是一个实体类对象
	//col是当前要设置的列的列号，0是第一列
	public String getColumnText(Object element, int col) {
		Person o = (Person) element; // 类型转换
		if (col == 0)// 第一列要显示什么数据
			return o.getName().toString();
		if (col == 1)
			return o.getSex();
		return null; // 方法可以返回空值
	}

	//getColumnText方法用于显示文字，本方法用于显示图片
	public Image getColumnImage(Object element, int col) {
		return null; // 方法可以返回空值
	}

	//当TableViewer对象被关闭时触发执行此方法
	public void dispose() {
		//别忘了SWT组件的原则：自己创建，自释放

	}

	//-------------以下方法很少使用,先不用管，让它们空实现-----------------
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void addListener(ILabelProviderListener listener) {}
	public void removeListener(ILabelProviderListener listener) {}
}
