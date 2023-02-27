package tests;

import org.testng.annotations.Test;
import runner.BaseTest;

public class MyTest extends BaseTest {
    @Test
    public void test1(){
        getDriver().get("https://stackoverflow.com/questions/50811759/what-is-the-difference-between-beforetest-and-beforemethod-in-testng");
    }


}
