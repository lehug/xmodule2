package com.penglecode.xmodule.springboot.examples.aop.autoproxy.restrpc;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import com.penglecode.xmodule.common.consts.ApplicationConstants;
import com.penglecode.xmodule.common.util.CollectionUtils;
import com.penglecode.xmodule.common.util.JsonUtils;

public class RestApiAutoProxyExample implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		ApplicationContext applicationContext = ApplicationConstants.APPLICATION_CONTEXT;
		
		Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
		
		JokeServiceRestApi jokeServiceRestApi = applicationContext.getBean(JokeServiceRestApi.class);
		JokeServiceRestApi myJokeServiceRestApi = (JokeServiceRestApi) applicationContext.getBean("myJokeServiceRestApi");
		System.out.println(jokeServiceRestApi);
		System.out.println(jokeServiceRestApi.hashCode());
		System.out.println(myJokeServiceRestApi);
		System.out.println(jokeServiceRestApi == myJokeServiceRestApi);
		System.out.println(jokeServiceRestApi.equals(myJokeServiceRestApi));
		ApiResult<List<Joke>> result = jokeServiceRestApi.getJokeList("images", 1, 20);
		System.out.println(result);
		if(!CollectionUtils.isEmpty(result.getResult())) {
			result.getResult().stream().map(JsonUtils::object2Json).forEach(System.out::println);
		}
	}

}
