package com.kuding.demo.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoAction {
	
	/***
	 * springmvc 接收页面参数有如下几种
	 * 1、在方法中增加与表单名字一致的参数，若名称不一致则需要通过@RequestParam指定名称， 参考reqParam
	 * 2、方法参数中注入Bean，要求bean属性名称与表单参数一致
	 * 3、@PathVariable 若参数与url中名称不一致，则需要通过@PathVariable指定名称, 参考pathVar方法
	 * 4、在方法中增加HttpServletRequest参数 参考show方法
	 * 
	 * springmvc向页面传递数据有如下几种
	 * 1、Action方法返回值为ModelAndView，将数据设置到ModelAndView的model中,参考show方法
	 * 2、Action方法中参数列表中添加ModelMap作为数据容器, 参考pathVar方法
	 * 3、使用@ModelAttribute @SessionAttribute 注解Action方法参数列表中的bean,参考passBean
	 */
	
	@RequestMapping("show")
	public ModelAndView show(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("demo/index");
		List<DemoFunction>  funList = new ArrayList<DemoFunction>();
		String basePath = req.getServletContext().getContextPath();
		init(funList,basePath);
		mv.getModel().put("funs", funList);
		return mv;
	}
	
	
	@RequestMapping("pathVar/{id}")
	public String pathVar(@PathVariable("id") String testId, ModelMap map ){
		map.addAttribute("testId", testId);
		return "demo/pathVar";
	}
	
	@RequestMapping("passBean")
	public String passBean(@ModelAttribute("demo") DemoFunction demo){
		demo.setKey("demo->key");
		demo.setUrl("http://www.baidu.com");
		return "demo/passBean";
	}
	
	@RequestMapping("submitBean")
	public ModelAndView submitBean(DemoFunction demo){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("demo/passBean");
		demo.setUrl("http://www.google.com");
		mv.addObject("demo", demo);
		return mv;
	}
	
	
	private void init(List<DemoFunction>  funList, String path){
		if(funList != null && !StringUtils.isEmpty(path)){
			funList.add(new DemoFunction("demo.show",path + "/" + "demo/show"));
			funList.add(new DemoFunction("demo.pathvariable",path + "/" + "demo/pathVar/" + System.currentTimeMillis()));
			funList.add(new DemoFunction("demo.passBean",path + "/" + "demo/passBean"));
		}
	}
	
	
	public static class DemoFunction{
		private String key;
		private String url;
		
		public DemoFunction(){
		}
		
		public DemoFunction(String key, String url){
			this.key = key;
			this.url = url;
		}
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
