/**
 * 
 */
package com.cloudpaas.admin.ui.studio.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudpaas.common.table.ButtonInfo;
import com.cloudpaas.common.table.FieldInfo;
import com.cloudpaas.common.table.SimpleTable;
import com.cloudpaas.common.table.UrlInfo;

/**
 * @author 大鱼
 *
 * @date 2019年9月5日 上午11:29:27
 */
@Service
public class StudioBiz {
	
	public SimpleTable getTable(){
		SimpleTable table = new SimpleTable();
		table.setId("oTable");
		table.setCellMinWidth(80);
		table.setUrl(getUrlInfo());
		table.setPage(true);
		table.setLimit(15);
		table.setCols(getCols());
		return table;
	}
	
	private UrlInfo getUrlInfo(){
		UrlInfo info = new UrlInfo();
		info.setUrl("/adminui/user/page.json");
		return info;
	}
	
	private List<FieldInfo> getCols(){
		List<FieldInfo> cols = new ArrayList<FieldInfo>();
		
		FieldInfo f1 = new FieldInfo();
		f1.setType("checkbox");
		f1.setFixed("left");
		f1.setFieldsort(1);
		cols.add(f1);
		
		FieldInfo f2 = new FieldInfo();
		f2.setField("id");
		f2.setTitle("ID");
		f2.setWidth("50");
		f2.setSort(true);
		f2.setFieldsort(2);
		cols.add(f2);
		
		FieldInfo f3 = new FieldInfo();
		f3.setField("username");
		f3.setTitle("用户名");
		f3.setWidth("80");
		f3.setSort(true);
		f3.setFieldsort(3);
		cols.add(f3);
		
		FieldInfo f4 = new FieldInfo();
		f4.setField("name");
		f4.setTitle("真实姓名");
		f4.setWidth("150");
		f4.setFieldsort(4);
		cols.add(f4);
		
		FieldInfo f5 = new FieldInfo();
		f5.setField("tel");
		f5.setTitle("手机号码");
		f5.setWidth("100");
		f5.setFieldsort(5);
		cols.add(f5);
		
		FieldInfo f6 = new FieldInfo();
		f6.setField("sex");
		f6.setTitle("性别");
		f6.setSort(true);
		f6.setTemplet("#sexType");
		f6.setFieldsort(6);
		cols.add(f6);
		
		FieldInfo f7 = new FieldInfo();
		f7.setField("createTime");
		f7.setTitle("注册时间");
		f7.setMinWidth("60");
		f7.setFieldsort(7);
		cols.add(f7);
		
		FieldInfo f8 = new FieldInfo();
		f8.setTitle("操作");
		f8.setFixed("right");
		f8.setMinWidth("60");
		f8.setAlign("center");
		f8.setTemplet("#oBar");
		f8.setFieldsort(8);
		cols.add(f8);
		
		return cols;
	}
	
	public List<ButtonInfo> getButtons(){
		List<ButtonInfo> buttons = new ArrayList<ButtonInfo>();
		/*
		ButtonInfo b0 = new ButtonInfo();
		b0.setName("搜索");
		b0.setEvent("data-search-btn");
		b0.setCss("");
		b0.setArea(1);
		b0.setSort(0);
		buttons.add(b0);*/
		
		ButtonInfo b1 = new ButtonInfo();
		b1.setName("添加");
		b1.setEvent("add");
		b1.setCss("layui-btn-normal");
		b1.setArea(1);
		b1.setSort(1);
		buttons.add(b1);
		
		ButtonInfo b2 = new ButtonInfo();
		b2.setName("批量删除");
		b2.setEvent("delBatch");
		b2.setCss("layui-btn-danger");
		b2.setArea(1);
		b2.setSort(1);
		buttons.add(b2);
		
		ButtonInfo b4 = new ButtonInfo();
		b4.setName("编辑");
		b4.setEvent("edit");
		b4.setCss("data-count-edit");
		b4.setArea(3);
		b4.setSort(1);
		buttons.add(b4);
		
		ButtonInfo b5 = new ButtonInfo();
		b5.setName("删除");
		b5.setEvent("delete");
		b5.setCss("layui-btn-danger data-count-delete");
		b5.setArea(3);
		b5.setSort(2);
		buttons.add(b5);
		
		ButtonInfo b6 = new ButtonInfo();
		b6.setName("获取选中行");
		b6.setEvent("checks");
		b6.setCss("layui-btn-sm");
		b6.setArea(2);
		b6.setSort(1);
		buttons.add(b6);
		
		return buttons;
	}
	

}
