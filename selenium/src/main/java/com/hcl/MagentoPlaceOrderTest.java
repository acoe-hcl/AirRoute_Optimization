import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class MagentoPlaceOrderTest {
  {
    "bugs_by_category": {
        "application_issues": [
            {
                "key": "ACATS-7196",
                "project": "ACATS",
                "summary": "Please create new Bitbucket repo for KYC project for ACATS Integration"
            },
            {
                "key": "ACATS-7166",
                "project": "ACATS",
                "summary": "Migration Cockpit running abnormally long for data conversion object "
            },
            {
                "key": "ACATS-5040",
                "project": "ACATS",
                "summary": "AC Wallet | Error EIP-0003 returned in capture flow"
            },
            {
                "key": "ACATS-4382",
                "project": "ACATS",
                "summary": "Browserstack issue - sikuli images does not get clicked when script is run from browserstack"
            },
            {
                "key": "ACATS-4050",
                "project": "ACATS",
                "summary": "Beyond Compare: Resolve the beyond compare issue on server "
            },
            {
                "key": "ACATS-4041",
                "project": "ACATS",
                "summary": "[IOS][Tablet]: User is unable to proceed further on booking flow, Page is getting disabled when user taps on choose depart icon in booking search screen"
            },
            {
                "key": "ACATS-4030",
                "project": "ACATS",
                "summary": "[IOS][IPad]: User is unable to proceed further on booking flow, Page is getting disabled when user taps on choose depart icon in booking search screen"
            },
            {
                "key": "ACATS-3437",
                "project": "ACATS",
                "summary": "HR POC - Status to be NA and disable download and view"
            },
            {
                "key": "ACATS-3239",
                "project": "ACATS",
                "summary": "[Desktop - Payment Page] - I accept, purchase button not working if the the gift card is added first"
            },
            {
                "key": "ACATS-3156",
                "project": "ACATS",
                "summary": "Need to change success Message - Create data Request"
            },
            {
                "key": "ACATS-3030",
                "project": "ACATS",
                "summary": "CL1D : Unable to pause the script of 5 mins during the Execution"
            },
            {
                "key": "ACATS-3029",
                "project": "ACATS",
                "summary": "UAT|Centralized Login : Close button X is displayed Additionally on Contact Information header for Traditional Chinese Edition"
            },
            {
                "key": "ACATS-2995",
                "project": "ACATS",
                "summary": "SFTP file upload show & hide"
            },
            {
                "key": "ACATS-2807",
                "project": "ACATS",
                "summary": "To fix Regression Issue "
            },
            {
                "key": "ACATS-2757",
                "project": "ACATS",
                "summary": "Cache issue in framework"
            },
            {
                "key": "ACATS-2543",
                "project": "ACATS",
                "summary": "XNONAIR_TD | Workflow name should be \"ACCRUAL-BATCH-XNONAIR\" instead of \"ACCRUAL-BATCH-XONAIR\""
            },
            {
                "key": "ACATS-2499",
                "project": "ACATS",
                "summary": "XNONAIR_TD | Duplicate output files are fetched when download option from Request dashboard is used"
            },
            {
                "key": "ACATS-2346",
                "project": "ACATS",
                "summary": "SIT | Unable to create branch from sit_release_branch"
            },
            {
                "key": "ACATS-1956",
                "project": "ACATS",
                "summary": "Retrieve Workflow sequence from DB"
            },
            {
                "key": "ACATS-1955",
                "project": "ACATS",
                "summary": "FF fails for YTH and CHD"
            },
            {
                "key": "ACATS-1931",
                "project": "ACATS",
                "summary": "UI Cosmetic issues"
            },
            {
                "key": "ACATS-1063",
                "project": "ACATS",
                "summary": "AC:PSSE2E:ECO:ACO: ECO-PNR Create : Error getting dsiplayed in rebooking fare review, unable to complete rebooking"
            }
        ],
        "compatibility": [
            {
                "key": "ACATS-5775",
                "project": "ACATS",
                "summary": "Getting error \"ERROR : Driver Error - Driver not initialized!\""
            },
            {
                "key": "ACATS-4421",
                "project": "ACATS",
                "summary": "Scripts are failing when running remotely on Browserstack"
            },
            {
                "key": "ACATS-4287",
                "project": "ACATS",
                "summary": "Browser Stack intermittent issue."
            },
            {
                "key": "ACATS-4235",
                "project": "ACATS",
                "summary": "[IOS][RTI Screen]:  User is unable to see the 3DS challenge page after taping on the accept and book button in browser stack "
            },
            {
                "key": "ACATS-4160",
                "project": "ACATS",
                "summary": "[Android] [Tablet] RTI screen : User is unable to proceed further on booking flow when user is adding the Second Payment card from RTI screen"
            },
            {
                "key": "ACATS-2072",
                "project": "ACATS",
                "summary": "[Android]RTI Payment billing Screen (Locator issue): Province label text attribute is returning empty value"
            },
            {
                "key": "ACATS-2071",
                "project": "ACATS",
                "summary": "[iOS]RTI Payment billing Screen (Locator issue): Street address and apartment labels are displaying with same label attribute as \"Street address, select to enter text\""
            }
        ],
        "data": [
            {
                "key": "ACATS-4095",
                "project": "ACATS",
                "summary": "Mismatched csv comparison not showing the data mismatched"
            },
            {
                "key": "ACATS-3443",
                "project": "ACATS",
                "summary": "Differences of Importance count is adding Baseline text as well "
            },
            {
                "key": "ACATS-2517",
                "project": "ACATS",
                "summary": "XNONAIR_TD | Excel workflow modification needed for XNONAIR_TD"
            },
            {
                "key": "ACATS-2500",
                "project": "ACATS",
                "summary": "Duplicate delivery sequence number "
            },
            {
                "key": "ACATS-2472",
                "project": "ACATS",
                "summary": "Data generator check for Output Optional fields is not honoring value %%%"
            },
            {
                "key": "ACATS-2467",
                "project": "ACATS",
                "summary": "TDM UI - User Module: Upload Option is missing"
            }
        ],
        "environmental": [
            {
                "key": "ACATS-7687",
                "project": "ACATS",
                "summary": "Test"
            },
            {
                "key": "ACATS-7241",
                "project": "ACATS",
                "summary": "Test"
            },
            {
                "key": "ACATS-4807",
                "project": "ACATS",
                "summary": "ETS_W application integration branch is not able to acccessable for me"
            },
            {
                "key": "ACATS-4455",
                "project": "ACATS",
                "summary": "Yml file is not available for NTP project"
            },
            {
                "key": "ACATS-4314",
                "project": "ACATS",
                "summary": "PSS-ARDWEB bitbucket branch access"
            },
            {
                "key": "ACATS-3578",
                "project": "ACATS",
                "summary": "Handle error for all API."
            },
            {
                "key": "ACATS-3305",
                "project": "ACATS",
                "summary": "Need help to handle to handle Windows Auth through ACATS "
            },
            {
                "key": "ACATS-2473",
                "project": "ACATS",
                "summary": "CLONE - CL1D - Bad Request is displayed while executing through ACATS, but working fine manually on BrowserStack"
            },
            {
                "key": "ACATS-2469",
                "project": "ACATS",
                "summary": "Unable to launch Eclipse-Marketplace and Install TestNG and Cucumber"
            },
            {
                "key": "ACATS-2451",
                "project": "ACATS",
                "summary": "CL1D - Bad Request is displayed while executing through ACATS, but working fine manually on BrowserStack"
            }
        ],
        "ui/ux": [
            {
                "key": "ACATS-3444",
                "project": "ACATS",
                "summary": "Source and Target Document(Baseline) documents are highlighted on Red color"
            },
            {
                "key": "ACATS-3063",
                "project": "ACATS",
                "summary": "UI - Healthcheck design integration comments"
            },
            {
                "key": "ACATS-3001",
                "project": "ACATS",
                "summary": "Unique key mapping to be displayed in UI"
            },
            {
                "key": "ACATS-2628",
                "project": "ACATS",
                "summary": "Tool Tip in the create and workflow for each field"
            }
        ],
        "uncategorized": [
            {
                "key": "ACATS-7776",
                "project": "ACATS",
                "summary": "Airport Field Contains Unwanted Data"
            },
            {
                "key": "ACATS-7775",
                "project": "ACATS",
                "summary": "Airport Field Validation Failed"
            },
            {
                "key": "ACATS-7229",
                "project": "ACATS",
                "summary": "test"
            },
            {
                "key": "ACATS-5791",
                "project": "ACATS",
                "summary": "Scripts are getting failed/skipped while running in my local machine using ACATS 3.1.7v for iTIMS project."
            },
            {
                "key": "ACATS-5575",
                "project": "ACATS",
                "summary": "Errors while upgrading to 3.1.6 "
            },
            {
                "key": "ACATS-4527",
                "project": "ACATS",
                "summary": "Access Change Request - Cargo One"
            },
            {
                "key": "ACATS-4437",
                "project": "ACATS",
                "summary": "All Environments | Login page failed to load for Desktop/Mobile/Tablet while executing via BrowserStack"
            },
            {
                "key": "ACATS-4051",
                "project": "ACATS",
                "summary": "Beyond Compare: Resolved the mounting issue."
            },
            {
                "key": "ACATS-3886",
                "project": "ACATS",
                "summary": "Testcomplete Log "
            },
            {
                "key": "ACATS-3622",
                "project": "ACATS",
                "summary": "Fix Configuration API"
            },
            {
                "key": "ACATS-3620",
                "project": "ACATS",
                "summary": "Modify Username for UAT environment"
            },
            {
                "key": "ACATS-3577",
                "project": "ACATS",
                "summary": "Handle error in redemption PNR"
            },
            {
                "key": "ACATS-3576",
                "project": "ACATS",
                "summary": "Unable to download files in QA Archival."
            },
            {
                "key": "ACATS-3550",
                "project": "ACATS",
                "summary": "Download folder from S3 bucket."
            },
            {
                "key": "ACATS-3165",
                "project": "ACATS",
                "summary": "Output file extension is showing null"
            },
            {
                "key": "ACATS-3129",
                "project": "ACATS",
                "summary": "Healthcheck dashboard graph should align center only for less record"
            },
            {
                "key": "ACATS-3007",
                "project": "ACATS",
                "summary": "Implement XNONAIR DCI partnerCode Data Generator"
            },
            {
                "key": "ACATS-3004",
                "project": "ACATS",
                "summary": "On Browser Refresh the Left menu options are not  available"
            },
            {
                "key": "ACATS-3003",
                "project": "ACATS",
                "summary": "Time Zone for Xpartref Handback"
            },
            {
                "key": "ACATS-3002",
                "project": "ACATS",
                "summary": "Utility to parse integer to characters"
            },
            {
                "key": "ACATS-3000",
                "project": "ACATS",
                "summary": "Store Header and Footer output using Unique Key Mapping"
            },
            {
                "key": "ACATS-2990",
                "project": "ACATS",
                "summary": "XNONAIR | Transaction Date logic should generate Current Date - 10 days"
            },
            {
                "key": "ACATS-2928",
                "project": "ACATS",
                "summary": "In file validation code for XPARTREF - TD"
            },
            {
                "key": "ACATS-2925",
                "project": "ACATS",
                "summary": "XPARTREF_CIBC | Batch Output file name should be cd.mftv.5730AIR.drct_mkt_ptr.ebc"
            },
            {
                "key": "ACATS-2913",
                "project": "ACATS",
                "summary": "Next Button is not enabled when creating Accrual Batch workflow"
            },
            {
                "key": "ACATS-2545",
                "project": "ACATS",
                "summary": "FFP_QA_Getting error as \"No data found for data source\" even when i am providing the data in \"FFP_TestData\" sheet."
            },
            {
                "key": "ACATS-2541",
                "project": "ACATS",
                "summary": "Dashboard Table , Add the Download Icon"
            },
            {
                "key": "ACATS-2519",
                "project": "ACATS",
                "summary": "Extra row is appeared for each file at end of record"
            },
            {
                "key": "ACATS-2466",
                "project": "ACATS",
                "summary": "XENROLL | Batch File Creation: Mismatch of workflow Data generator Keys and Data Generator Type ENUMS of Phone Zone and Phone Area code"
            },
            {
                "key": "ACATS-2465",
                "project": "ACATS",
                "summary": "XENROLL | Batch File Creation: First name and Surname list should be increased"
            },
            {
                "key": "ACATS-2460",
                "project": "ACATS",
                "summary": "XENROLL | Batch File Creation: Default Postal code is generated instead of expected code based on Municipality name"
            },
            {
                "key": "ACATS-2176",
                "project": "ACATS",
                "summary": "1941-Create Request Demo Comments "
            },
            {
                "key": "ACATS-2015",
                "project": "ACATS",
                "summary": "Fare Family code does not appear on Data Record dashboard"
            },
            {
                "key": "ACATS-2005",
                "project": "ACATS",
                "summary": "One way and Round tip scenarios not working with Infants ( INS & INF)"
            },
            {
                "key": "ACATS-2004",
                "project": "ACATS",
                "summary": "Workflow Dashboard : InputOutput sheet data is not cleared on tab out"
            },
            {
                "key": "ACATS-2003",
                "project": "ACATS",
                "summary": "Workflow Dashboard : Table scroll issue"
            },
            {
                "key": "ACATS-1953",
                "project": "ACATS",
                "summary": "Handle flight id and Flight fare family unavailability scenario"
            }
        ],
        "validation": [
            {
                "key": "ACATS-4248",
                "project": "ACATS",
                "summary": "Beyond Compare: Prod : Login page showing default login pop up when user credentials is incorrect  "
            },
            {
                "key": "ACATS-3479",
                "project": "ACATS",
                "summary": "DC cards logic on the NDREF110 file"
            },
            {
                "key": "ACATS-3198",
                "project": "ACATS",
                "summary": "Unable to upload the input file in Microsoft Edge browser"
            },
            {
                "key": "ACATS-2984",
                "project": "ACATS",
                "summary": "Journey String in TerraData is not being built for all ticket statuses  "
            },
            {
                "key": "ACATS-2523",
                "project": "ACATS",
                "summary": "XPARTREF_TD | Creation Date, Partner Club Code, Partner Product Type Code are fetched as null for XPARTREF"
            },
            {
                "key": "ACATS-2521",
                "project": "ACATS",
                "summary": "EarnAllAirlines : Footer field record type 3 fixed value as '03' but in output file generated as 3 with space"
            },
            {
                "key": "ACATS-2507",
                "project": "ACATS",
                "summary": "XNONAIR_TD | Transaction date, Partner Club Code, Partner Product Type Code, Partner Reference are fetched as null for XNONAIR "
            },
            {
                "key": "ACATS-2503",
                "project": "ACATS",
                "summary": "GENERIC | When 2nd upload is made for overriding previously uploaded INPUT_DATA file , \"Submit Request\" button is getting disabled"
            },
            {
                "key": "ACATS-2498",
                "project": "ACATS",
                "summary": "XENROLL | Batch File Creation: Data for Phone Zone should generate numeric code  and Phone number should generate with 7 digits"
            },
            {
                "key": "ACATS-2497",
                "project": "ACATS",
                "summary": "XPARTREF_TD | Batch File Creation: User should have an option to skip the values for Conditional Input Mandatory field like \"AUTH_USER_LOYALTY_NUMBER_KEY\""
            },
            {
                "key": "ACATS-2496",
                "project": "ACATS",
                "summary": "XPARTREF_TD | Batch File Creation: When a user gives \"%%%\" for only \"Customer First Name or Initial\" optional field in INPUT_DATA, it prints all the remaining Gigya API fetched values(like Customer Surname, Customer Municipality...etc)in the output file"
            },
            {
                "key": "ACATS-2495",
                "project": "ACATS",
                "summary": "XPARTREF_TD | Batch File Creation: Trailing Spaces are not added for MUNICIPALITY_CODE_KEY, POSTAL_CODE_KEY"
            },
            {
                "key": "ACATS-2468",
                "project": "ACATS",
                "summary": "EarnAllAirlines: Missing Mandatory Field-Sending Airline in row 3"
            },
            {
                "key": "ACATS-2014",
                "project": "ACATS",
                "summary": "Redemption PNR  Return trip does not honor return flight number"
            }
        ]
    },
    "status": "success"
}
}
