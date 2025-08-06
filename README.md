# 🛍️ Amazon Cucumber-TestNG Hybrid Framework

This is a real-time automation testing framework built to automate Amazon product search scenarios using:

- ✅ Selenium WebDriver
- ✅ Cucumber BDD (Gherkin syntax)
- ✅ TestNG (as runner)
- ✅ Apache POI for Excel data
- ✅ Extent Reports with Screenshot Support
- ✅ Retry Analyzer for flaky test handling
- ✅ Tag-based test execution
- ✅ GitHub Integration

![Java](https://img.shields.io/badge/Java-11+-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![TestNG](https://img.shields.io/badge/TestNG-7.9-orange)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-yellow)

---

## 📁 Project Structure

HybridAmazonFramework/


├── features/                   
│   ├── AmazonSearch.feature
│   └── AmazonSearchExcel.feature
│

├── stepdefinitions/           
│   ├── SearchSteps.java
│   └── SearchExcelSteps.java
│

├── hooks/                     
│   ├── Hooks.java
│   └── ExtentReporterHooks.java
│

├── utils/                     
│   ├── ExcelUtil.java
│   ├── ScreenshotUtil.java
│   ├── StepLogger.java
│   ├── ConfigReader.java
│   └── RetryListener.java
│

├── testdata/                  
│   └── AmazonSearchData.xlsx
│

├── reports/                   
│   └── ExtentReport.html
│

├── screenshots/               
│   └── (failure screenshots generated at runtime)
│

├── runner/                    
│   └── TestNGCucumberRunner.java
│

├── testng.xml                 
├── pom.xml                    
└── README.md                  



---

## 🧪 How to Execute

### ▶️ From IDE (IntelliJ)
- Right-click `testng.xml` → **Run As → TestNG Suite**

### 🏷️ Tag Based Execution

You can filter tests using tags in the runner class:
```java
tags = "@ExcelSearch"

📸 Reporting & Logs
✅ Extent Report: reports/ExtentReport.html

✅ Cucumber HTML: target/cucumber-reports/index.html

✅ Failure Screenshots: screenshots/ScenarioName_Timestamp.png

📊 Data-Driven Testing
✅ Excel file: testdata/AmazonSearchData.xlsx

✅ Powered by: Apache POI

✅ Scenario: AmazonSearchExcel.feature

📌 Tags
Tag	Description
@ExcelSearch	Excel-driven test scenario

@Smoke	Smoke tests
@sanity Sanity tests

💻 Tech Stack
Layer	Technology
Language	Java 11+
Automation Tool	Selenium WebDriver
BDD Framework	Cucumber
Runner	TestNG
Build Tool	Maven
Reports	Extent Reports, Cucumber HTML
Data-Driven	Apache POI (Excel support)
VCS	Git + GitHub

👨‍💻 Author
👤 Name: vinayprasad HM

🌐 GitHub: Vinay_QA_Techie

📧 Email: vinayprasad.testy@gmail.com
🔗 LinkedIn: www.linkedin.com/in/vinay-prasad-00a88115a

🔮 Future Enhancements
✅ JSON Data Support

✅ Allure Reporting Integration

✅ Docker Grid Execution Support

✅ Page Factory / Fluent Wait Refactor


