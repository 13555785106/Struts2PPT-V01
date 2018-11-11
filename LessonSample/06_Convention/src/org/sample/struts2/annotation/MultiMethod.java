package org.sample.struts2.annotation;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;
@AllowedMethods({"func1","func2","func3"})
public class MultiMethod extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public String input() throws Exception {
		return super.input();
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	@Action("func1") 
	public String func1() throws Exception {
		return "func1";
	}
	@Action("func2") 
	public String func2() throws Exception {
		return "func2";
	}
	@Action(value="func3",results={
            @Result(name="success",location="func3-success.jsp"),
            @Result(name="input",location="func3-input.jsp"),
            @Result(name="error",location="func3-error.jsp")
    }) 
	public String func3() throws Exception {
		return "func3";
	}
}
