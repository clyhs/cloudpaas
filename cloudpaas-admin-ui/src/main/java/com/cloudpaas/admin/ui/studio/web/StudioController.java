/**
 * 
 */
package com.cloudpaas.admin.ui.studio.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudpaas.admin.ui.studio.biz.StudioBiz;
import com.cloudpaas.common.table.FieldInfo;
import com.cloudpaas.common.table.SimpleTable;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 大鱼
 *
 * @date 2019年9月5日 上午11:28:12
 */
@Slf4j
@Controller
@RequestMapping("studio")
public class StudioController {
	
	@Autowired
	StudioBiz studioBiz;

	@RequestMapping("/index.html")
	public String index(ModelMap modelMap){
		modelMap.addAttribute("table", studioBiz.getTable());
		
		modelMap.addAttribute("cols", cols());
		//cols();
		return "admin/studio/index";
	}
	
	private String cols(){
		
		List<FieldInfo> fields = new ArrayList<FieldInfo>();
		fields = studioBiz.getTable().getCols();
		StringBuffer sb = new StringBuffer();
		int i = 1;
		for(FieldInfo f : fields){
			if(f.getFieldsort() == i){
				sb.append("{");
				if(f.getType()!=null && i == 1){
					sb.append("type:").append("'").append(f.getType()).append("',")
					  .append("fixed:").append("'").append(f.getFixed()).append("'");
				}
				
				if(f.getType()==null && i!=1){
					sb.append("field:").append("'").append(f.getField()).append("',")
					  .append("title:").append("'").append(f.getTitle()).append("',");
					
					if(null!=f.getWidth()){
						sb.append("width:").append("").append(f.getWidth()).append(",");
					}
					
					if(null!=f.getMinWidth()){
						sb.append("minWidth:").append("").append(f.getMinWidth()).append(",");
					}
					
					if(null!=f.getSort() && f.getSort()){
						sb.append("sort:").append("").append("true").append(",");
					}
					
					if(null!=f.getAlign()){
						sb.append("align:").append("").append(f.getAlign()).append(",");
					}
					if(null!=f.getTemplet()){
						sb.append("templet:").append("").append(f.getTemplet()).append(",");
					}
				}
				
				if(i == fields.size()){
					sb.append("}\\r\\n");
				}else{
					sb.append("},\\r\\n");
				}
				
				
			}
			i++;
		}
		log.info(sb.toString());
		return sb.toString();
	}
	
	
}
