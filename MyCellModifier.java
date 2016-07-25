package com.huawei.seq.table;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

public class MyCellModifier implements ICellModifier {
	
	private DataModel model;

	private TableViewer tv;

	public MyCellModifier(TableViewer tv, DataModel model) {
		this.tv = tv;
		this.model = model;
	}

	// 判断是否可以修改某条记录的某一字段。这里返回true表示都可以修改
	// 参数element是表格记录对象，也就是PeopleEntity对象
	// 参数property是列别名。该值不会有CellEditor为null的列，也就是说它不可能为id,createdate
	public boolean canModify(Object element, String property) {
		return true;
	}

	// 此方法决定当单击单元格出现CellEditor时应该显示什么值。参数说明参考canModify方法
	// 每种CellEditor要求返回的数据类型都是各不相同的，类型不对应就会出错
	public Object getValue(Object element, String property) {
		Person o = (Person) element;
		if (property.equals("sex")) {
			// ComboBoxCellEditor要求返回姓名在下拉框中的索引值
			return model.GetSexId(o.getSex());
		} else if (property.equals("name")) {
			// CheckboxCellEditor要求返回当前值对应的布尔值
			return o.getName();
		}
		throw new RuntimeException("错误的列别名:" + property);
	}

	// 从CellEditor取值得此单元格的值
	// 参数element是表格行对象TableItem，其getData()方法可取得PeopleEntity
	// 参数property是列别名
	// 参数value是修改后的新值。每种CellEditor的value的数据类型各不相同
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		Person o = (Person) item.getData();
		// 根据新的修改值更新PeopleEntity对象的数据
		if (property.equals("sex")) {
			// ComboBoxCellEditor的value是其索引值
			Integer comboIndex = (Integer) value;
			String newSex = model.GetSexByIndex(comboIndex);
			o.setSex(newSex);
		} else if (property.equals("name")) {
			// CheckboxCellEditor的value是布尔值
			o.setName((String)(value));
		} else {
			throw new RuntimeException("错误的列别名:" + property);
		}
		
		if (o.ismIsFake()) {
			o.setmIsFake(false);
			model.AddFakePerson();
			tv.refresh();
		} else {
			tv.update(o, null);
		}
	}

}
