# VisaControlApp_ProgrammingFundamentals_2

In this homework you are expected to implement an “Visa Control Application” in Java. You should fulfill the concepts of:

⚫ Inheritance

⚫ Polymorphism

⚫ CSV File I/O

⚫ Array Lists

In the Visa Control Application, according to given data, applicants will be accepted or rejected.

A file that contains different types of information about applicants is given: HW2_ApplicantsInfo.csv. The columns of this file are given in the following for each type of information:

Applicant Info: A, applicant ID, applicant name

Passport: S, applicant ID, passport number, expiration date

Photo: P, applicant ID, resolution, position

Financial Status: F, applicant ID, income, savings

Document: D, applicant ID, document type, duration in months(optional)

Note that every column has a prefix that states what type of information it is. The csv file is mixed and columns that are related with an applicant will not be in order. Therefore, you should relate every information with applicant ID.

There are 4 types of application, and it is given in the prefix of the applicant ID: {tourist 11, worker 23, educational 25, immigrant 30} (example ID of a tourist application: 110189)

There are 3 types of documents: {letter of acceptance LA, invitation letter IL, green card GC}

Acceptance Rules:

-Passport:

Passport number should have valid format:

It should have 10 characters, it should start with P, last three characters should be digits
(e.g., P34AST9G587)

Expiration date should be at least 6 months later.

-Photo:

The photo should be square

Resolution should be between 600x600 and 1200x1200

Accepted Positions are neutral face and natural smile

-Financial Status:

It depends on the application type:

For tourist: Income should be at least 2000$. Savings are related with the income, and it is as follows:

Income: 2000$-3000$ -> savings: 12000$

Income: 3000$-4000$ -> savings: 6000$

Income: Above 4000$ -> savings: Not necessary

For worker: No income needed. Savings should be at least 2000$.

For educational: Income should be at least 1000$. Savings are related with the income, and it is as follows:

Income: 1000$-2000$ -> savings: 6000$

Income: 2000$-3000$ -> savings: 3000$

Income: Above 3000$ -> savings: Not necessary

For immigrant: No income needed. Savings should be at least 4000$ with a green card; savings should be at least 50000$ without a green card.

-Document:

Note that, an applicant may have more than one document. It depends on the application type:

For tourist: It is not necessary to have a document, but if tourist application has an invitation letter, necessary income and savings are halved.

For worker: It is necessary to have a letter of acceptance.

For educational: It is necessary to have a letter of acceptance. If educational application has an invitation letter, necessary income and savings are halved.

For immigrant: It is not necessary to have a document, financial status criteria change according to having a green card. If immigrant application has an invitation letter, 
necessary income and savings are halved.

Visa Duration:

If an application is accepted, the duration of the visa will be as follows according to application type:

For tourist: The duration can be 6 months, 1 years, and 5 years. It will be according to duration constant (DC).

-Without invitation letter DC = ((Income – 2000) * 6 + savings)/12000

-With invitation letter DC = ((Income – 2000) * 6 + savings)/6000

DC: 1– 2 -> 6 months

DC: 2 – 4 -> 1 years

DC: 4 and above -> 5 years

If passport expiration date is before the calculated duration, duration of visa will be determined according to expiration date (e.g., calculated duration: 1 years, expiration date: 9 months, then duration of visa will be 6 months).

For worker: The duration can be 1 years, 2 years, and 5 years. It will be according to duration in the letter of acceptance and the passport expiration date. The visa duration should be higher than the duration in the letter of acceptance if applicant has a suitable passport expiration date (e.g., acceptance duration: 9 months, expiration date: 16 months, then duration of visa will be 1 years) (e.g., acceptance duration: 2 years, expiration date: 13 months, then duration of visa will be 1 years) (e.g., acceptance duration: 1 years, expiration date: 9 months, the application will be rejected).

For educational: The duration can be 1 years, 2 years, and 4 years. It will be according to duration in the letter of acceptance and the passport expiration date. The visa duration should be higher than the duration in the letter of acceptance if applicant has a suitable passport expiration date (e.g., acceptance duration: 9 months, expiration date: 16 months, then duration of visa will be 1 years) (e.g., acceptance duration: 2 years, expiration date: 13 months, then duration of visa will be 1 years) (e.g., acceptance duration: 1 years, expiration date: 9 months, the application will be rejected).

For immigrant: It will be permanent.

Rejection Reasons:

If an applicant is rejected, a rejection reason should be given. If an application has more than one reason for rejection, only one reason will be given, and the order of reasons are as follows:

-Applicant does not have a passport

-Passport is not valid

-Passport expiration date is not valid

-Applicant does not have a photo

-Resolution of photo is not valid

-Position in the photo is not valid

-Applicant does not have a financial status report

-Applicant does not have a stable financial status

-Applicant does not have a letter of acceptance

Expected Output Format:

Applicant ID: 110025, Name: Serhat Caner, Visa Type: Tourist, Status: Accepted, Visa Duration: 5 years

Applicant ID: 110603, Name: Lucas Meklit, Visa Type: Tourist, Status: Rejected, Reason: Applicant does not have a stable financial status

Applicant ID: 230253, Name: Endla Daliborka, Visa Type: Worker, Status: Accepted, Visa Duration: 2 years

Applicant ID: 250123, Name: King Ciara, Visa Type: Educational, Status: Accepted, Visa Duration: 4 years

Applicant ID: 258553, Name: Percival Balthazar, Visa Type: Educational, Status: Rejected, Reason: Applicant does not have a letter of acceptance

Applicant ID: 309603, Name: Heikki Tlaloc, Visa Type: Immigrant, Status: Accepted, Visa Duration: Permanent

.
.
.

Note that output should be sorted according to Applicant ID

Important Notes:

1. You can use standard java.io packages to read files. Do NOT use other 3rd party libraries.

2. You should use relative paths (e.g., Files/sample.csv) instead of absolute paths (e.g., C:\\user\\eclipse-workspace\\MyProject\\Files\\sample.csv).

3. To support Turkish characters, you may need to change your project’s text file encoding to UTF8: Right click on your project (in package explorer) → Properties → Text file encoding → Other → UTF8 → Apply.

4. You are expected to write clean, readable, and tester-friendly code. Please try to maximize reusability and prevent from redundancy in your methods.
