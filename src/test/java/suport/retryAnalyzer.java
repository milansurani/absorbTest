package suport;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class retryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 3;
    @Override
    public boolean retry(ITestResult iTestResult) {
       
    	 //Check if test not succeed
    	if (!iTestResult.isSuccess()) {                     
    		//Check if maxtry count is reached
    		if (count < maxTry) {                            
    			 //Increase the maxTry count by 1
    			count++;                                    
    			//Mark test as failed
    			iTestResult.setStatus(ITestResult.FAILURE);  
    			 //Tells TestNG to re-run the test
    			return true;                                
            } else {
            	//If maxCount reached,test marked as failed
            	iTestResult.setStatus(ITestResult.FAILURE);  
            }
        } else {
        	//If test passes, TestNG marks it as passed
        	iTestResult.setStatus(ITestResult.SUCCESS);      
        }
        return false;
    }
}
