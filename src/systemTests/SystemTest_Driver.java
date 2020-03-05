import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class SystemTest_Driver {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(LoginTest.class, SecurityQuestionTest.class,
				LogoutTest.class, CalcSalTest.class, ApproveTSTest.class, ModifyTSTest.class,
				SaveTSTest.class, AddEmployeeTest.class, SecurityQuestionTest.class, 
				ViewProfileTest.class);
		for (Failure fail : result.getFailures()) {
			System.out.println(fail.toString());
		}
		System.out.println(result.wasSuccessful());
	}
	
}
