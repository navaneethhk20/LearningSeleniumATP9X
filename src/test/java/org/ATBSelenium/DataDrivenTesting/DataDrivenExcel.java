package org.ATBSelenium.DataDrivenTesting;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenExcel {
    @Test(dataProvider = "getaData", dataProviderClass = UtilExcel.class)
    public void testVWOLogin(String email, String password){
        System.out.println("Email -" +email);
        System.out.println("Password -" +password);

    }
}
