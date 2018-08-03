/**
 * HelloWorldController
 */
package com.rskt.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishijie
 * 
 */
// @RestController的意思就是controller里面的方法都以json格式输出，不用再写jackjson配置
@RestController
public class HelloWorldController {
	
	// http://localhost:8080/index
	@RequestMapping("/index")
	public String index() {
        return "这是SpringBoot样例程序。 <br/>BY RSKT";
    }
}
