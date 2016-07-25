package com.huawei.seq.table;
//import javax.swing.table.TableColumn;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class TableViewerMain {
	public static void main(String[] args) {

		Display display = new Display ();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());

		TableViewer tv = new TableViewer(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL); 

		//第二步：通过表格内含的Table对象设置布局方式 
		Table table = tv.getTable(); 
		table.setHeaderVisible(true); // 显示表头 
		table.setLinesVisible(true); // 显示表格线 
		TableLayout layout = new TableLayout(); // 专用于表格的布局 
		table.setLayout(new TableLayout()); 
		//  第三步：用TableColumn类创建表格列 
		layout.addColumnData(new ColumnWeightData(13));// ID列宽13像素 
		new TableColumn(table, SWT.NONE).setText("姓名"); 
		layout.addColumnData(new ColumnWeightData(20)); 
		new TableColumn(table, SWT.NONE).setText("性别"); 


		tv.setContentProvider(new AppContentProvider());

		DataModel model = new DataModel(0);
		tv.setInput(model);
		tv.setLabelProvider(new labelProvide());
		

		table.setLayout(layout);


		tv.setColumnProperties(new String[] { "name", "sex" });
		// 设置每一列的单元格编辑组件CellEditor
		CellEditor[] cellEditor = new CellEditor[2];
		cellEditor[0] = new TextCellEditor(tv.getTable());
//		cellEditor[1] = new ComboBoxCellEditor(tv.getTable(), DataModel.SEX_KEYS, SWT.READ_ONLY);
		cellEditor[1] = new ComboBoxCellEditor(tv.getTable(), new String[]{}, SWT.READ_ONLY);
		tv.setCellEditors(cellEditor);
		tv.setCellModifier(new MyCellModifier(tv, model)); // 设置表格的修改器MyCellModifier
	
		shell.open ();

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}

		display.dispose ();
	}
}

class AppContentProvider implements IStructuredContentProvider {
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof DataModel)
			return ((DataModel)inputElement).getData().toArray();
		return null;
	}

	public void dispose() { }
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }
}