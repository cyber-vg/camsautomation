package initBrowser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class startBrowser {
Playwright playwright;
Browser browser;
BrowserContext browserContext;	
static Page page;
Properties prop;
private static ThreadLocal<Browser> tlBrowser=new ThreadLocal<Browser>() ;
private static ThreadLocal<BrowserContext> tlBrowserContext=new ThreadLocal<BrowserContext>() ;
private static ThreadLocal<Page> tlPage=new ThreadLocal<Page>() ;
private static ThreadLocal<Playwright> tlPlaywright=new ThreadLocal<Playwright>();
private static Boolean headlessvalue=null;
public static Playwright getPlaywright() {
	return tlPlaywright.get();
}
public static Browser getBrowser() {
	return tlBrowser.get();
}
public static BrowserContext getBrowserContext() {
	return tlBrowserContext.get();
}
public static Page getPage() {
	return tlPage.get();
}
public Page initBrowserMember(Properties prop) {
	
	//to get screensize of device
	Dimension  screensize	= Toolkit.getDefaultToolkit().getScreenSize();
	int width=(int)screensize.getWidth();
	int height=(int)screensize.getHeight();	
	//for custom browser
	String browserName=  prop.getProperty("browser").trim();
	String headlessValueString=prop.getProperty("headless");
	System.out.println("Browser name is "+ browserName);
	tlPlaywright.set(Playwright.create());
	browseroption(browserName,headlessValueString);		
// 		code use while not use threadlocal concept		
//		browserContext=browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height));
//		page=browserContext.newPage();
//		page.navigate(prop.getProperty("url"));
	tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(width,height) .setRecordVideoDir(Paths.get("video/"))));
	tlPage.set(getBrowserContext().newPage());
	getPage().navigate(prop.getProperty("memberurl").trim());
	return getPage();
	}
	
public Page initBrowserAdmin(Properties prop) {
	//to get screensize of device
	Dimension  screensize	= Toolkit.getDefaultToolkit().getScreenSize();
	int width=(int)screensize.getWidth();
	int height=(int)screensize.getHeight();	
	//for custom browser
	String browserName=  prop.getProperty("browser").trim();
	String headlessValueString=prop.getProperty("headless");

	System.out.println("Browser name is "+ browserName);
	tlPlaywright.set(Playwright.create());
	browseroption(browserName,headlessValueString);		
	tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(width,height) .setRecordVideoDir(Paths.get("video/"))));
	tlPage.set(getBrowserContext().newPage());
	getPage().navigate(prop.getProperty("adminurl"));
	return getPage();
	}
public Page initBrowserSuper(Properties prop) {
	//to get screensize of device
	Dimension  screensize	= Toolkit.getDefaultToolkit().getScreenSize();
	int width=(int)screensize.getWidth();
	int height=(int)screensize.getHeight();	
	//for custom browser
	String browserName=  prop.getProperty("browser").trim();
	String headlessValueString=prop.getProperty("headless");
	System.out.println("Browser name is "+ browserName);
	tlPlaywright.set(Playwright.create());
	browseroption(browserName,headlessValueString);		
	tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(width,height) .setRecordVideoDir(Paths.get("video/"))));
	tlPage.set(getBrowserContext().newPage());
	getPage().navigate(prop.getProperty("superadminurl"));
	return getPage();
	}
//for read properties from config file 
public Properties init_prop() {
	try {
		FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
	prop=new Properties();
	prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
return prop;
}
//for browser options
public static void browseroption(String browserName ,String headlessValueString) {
	if(headlessValueString.toLowerCase().trim().equals("true")) {
		headlessvalue=true;
	}else if (headlessValueString.toLowerCase().trim().equals("false")) {
		headlessvalue=false;
		
	}
	switch (browserName.toLowerCase()) {
	case "chromium":
		tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessvalue)));
		break;
	case "firefox":
		tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessvalue)));
		break;
	case "safari":
		tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headlessvalue)));
		break;
	case "chrome":
		tlBrowser.set(
				getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(headlessvalue)));
		break;
	case "edge":
		tlBrowser.set(
				getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(headlessvalue)));
		break;	

	default:
		System.out.println("please pass the right browser name......");
		break;
	}
	
	
}

//for taking  screenshot
public static String takeScreenshot() {
	String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
	//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(false));
	byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
	String base64Path = Base64.getEncoder().encodeToString(buffer);
	return base64Path;
}
}
