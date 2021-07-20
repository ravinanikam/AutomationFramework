package Automation.utils;

public enum TestCases {
    T1("Testing the authentication"),
    T2("Testing purchase of two items");

    private String testName;
    TestCases(String value){
        this.testName = value;
    }

    public String getTestName() {
        return testName;
    }
}
