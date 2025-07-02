I am providing you here wth some insights, ideas, assumptions and resolutions regarding my Test Project.

I created one Base class.

I created ExtentFactory static class which was used for all classes that have test loggings.
Please note that I initialized Extent Report instances in every class where it was needed in @BeforeClass methods because I prefer to have everything about Extent Reports for each class in that particular class ( I undertand that everything except creating particular test for each class can be written in one common class)

My idea was to use Page Object Model concept and my test classes to be short and simple.
My test classes do not have loggings. All loggings are in other classes. It was my intention.


TASK 1
Please note that I used hard Assert and not Soft Assert. It was not emphasized so I decided to go with hard Assert.
I used Soft Assert in Task 5
Assumptions:
1)regarding step 4, my assumption was that YES button is supposed to be green and not red, so based on that the assertitation is failing

TASK 2
1) regarding step 3, I just verified that elements are present and I didn't check their functionality here because some of their functionalities are checked in other steps ( this is how I understand this task)

TASK 3
1) regarding step 2, my assumption was that I should filter employees on the web page itself ( I didn't get all data in Excel file and filter it on the excel)
2) regarding step 5, I performed verification of the employees based on 4 atributes because I aready had their xpaths. I just wanted to show my logic as I am in a hurry to resolve all tasks ( unfortunatelly, I didn't have more time due to other family commitments at the moment). I hope this works for you.

TASK 4
None

TASK 5
1) regarding step 2, my assumption was that MultiSelect field should be empty by default.
It is showing 2 sports when we open the page, so I presumed that these sports should be cleared before I choose my own favorite sports (football and tennis)
So I verified first if the field is empty.
2) regarding step 3, it is connected to previuos explanation - first I clear the field and I select 2 sports afterwards


