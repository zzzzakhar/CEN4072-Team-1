package systemTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddEmployeeTest.class, ApproveTSTest.class, CalcSalTest.class, LoginTest.class, LogoutTest.class,
		ModifyTSTest.class, SaveTSTest.class, SecurityQuestionTest.class, SubmitTSTest.class, ViewProfileTest.class })
public class AllTests {

}
