package com.petclinic.demo.collections;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(types = DataClass.class)
public class ListDemo {

	private int selectIndex;

	public ListDemo(int selectIndex) {
		this.selectIndex = selectIndex;
	}

	@RequestMapping(value = "/owners", method = RequestMethod.GET)
	public void handler(DataClass data) throws IOException {
		List<String> inputs = new ArrayList<>();
		String dataStr = data.getInput();
		inputs.add(dataStr);
		inputs.add(data.getInput2());
		inputs.add("ls");

		Runtime.getRuntime().exec(inputs.get(selectIndex));
	}
}
