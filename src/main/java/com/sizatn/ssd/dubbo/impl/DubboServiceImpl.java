package com.sizatn.ssd.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sizatn.ssd.dubbo.DubboService;

//@Service(group="dubboService", version = "1.0.0")
public class DubboServiceImpl implements DubboService {

	@Override
	public String getName() {
		return "Hello World!";
	}

}
