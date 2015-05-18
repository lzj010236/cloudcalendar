This project let user add/modify/delete his/her calender events on one spercific day. 
    
Database structure:
It has two table: ACCOUNT and EVENTS
	Account has NAME and EMAIL, and EMAIL is unique
	EVENETS has TITLE, TIMESTAMP, and USEREMAIL, and USEREMAIL is unique

The project process as follow:
    index.jsp -> Submit1.java -> calendar1.jsp -> addEvent.java(or deleteEvent.java) -> updateDB.java -> calendar1.jsp
    user can exit to index.jsp at any page. 
index.jsp record user-entered NAME and EMAIL as session elements. And pass them to SUBMIT1. 
Submit1.java handle and insert NAME and EMAIL into database. 
	Then dispatch calendar1.jsp to display a table calendar to let user choose a time 	or delete an event. 
calendar1.jsp pass the time user chose to addEvent.java or redirect to deleteEvent.java. 
addEvent.java record and handle user-inputed TIME and TITLE into database. 
deleteEvent.java erase the TITLE according to the user-selected TIMESTAMP. 
user can exit to index.jsp at any page. 