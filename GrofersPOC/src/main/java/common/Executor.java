package common;



import static io.restassured.RestAssured.given;
public class Executor implements Runnable{

	String path=null;
	Config conf=null;
	public Executor(Config conf,String path) {
		this.path=path;
		this.conf=conf;
	}
	
	public void run() {
		try {
			conf.log("Executing thread");
			if(given().get(path).getStatusCode()!=200){
				conf.log("Image is broken");
				conf.softAssert.assertTrue(false, "Image is broken:"+path);
				
			}else{
				conf.softAssert.assertTrue(true, "image is not broken");
				conf.log("Image is not broken :"+path);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
