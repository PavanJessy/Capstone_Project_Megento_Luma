package hooks;

import base.BaseClass;
import io.cucumber.java.After;

public class Hooks {
	@After
	public void closeApplication() {
		BaseClass.closeBrowser();
	}
}
