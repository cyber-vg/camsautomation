package superAdminSmokePages;

import com.microsoft.playwright.Page;

public class SuperAdminLogin {
	private	Page page;
	
public SuperAdminLogin(Page page) {
	this.page=page;
}

public String getHomePageTitle() {
	String title = page.title();
	System.out.println(title);
	return title;
	}

}
