package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.programmatic;

public class GreetingService2Impl implements GreetingService2 {

	@Override
	public void sayHello() {
		System.out.println("hello!");
	}

	@Override
	public void sayBye() {
		System.out.println("bye!");
	}

	@Override
	public void sayWord(String word) {
		System.out.println(word);
	}

}
