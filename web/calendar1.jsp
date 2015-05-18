<%-- 
    Document   : calendar1
    Created on : Nov 13, 2013, 6:44:16 PM
    Author     : Jing Lin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="newcss.css" type="text/css">
        <title>Calendar1</title>
    </head>
    <body>
        <h1>Welcome :  <%=session.getAttribute("name")%></h1>
        <h1>Choose a time for your event: </h1>

        <table border="1" width="400">
            <tbody>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td>Time</td>
                    <td>Event</td>
                    <td></td>
                </tr>

                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">

                    <td><a href="
                           addEvent?calendar_time=1">9am</a></td>
                    <td>
                        <%=request.getAttribute("title1")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=1">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=2">10am</a></td>
                    <td>
                        <%=request.getAttribute("title2")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=2">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=3">11am</a></td>
                    <td>
                        <%=request.getAttribute("title3")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=3">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=4">12pm</a></td>
                    <td>
                        <%=request.getAttribute("title4")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=4">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=5">1pm</a></td>
                    <td>
                        <%=request.getAttribute("title5")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=5">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=6">2pm</a></td>
                    <td>
                        <%=request.getAttribute("title6")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=6">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?email=<%=session.getAttribute("email")%>&calendar_time=7">3pm</a></td>
                    <td>
                        <%=request.getAttribute("title7")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=7">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=8">4pm</a></td>
                    <td>
                        <%=request.getAttribute("title8")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=8">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=9">5pm</a></td>
                    <td>
                        <%=request.getAttribute("title9")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=9">delete</a></td>
                </tr>
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = 'orange';" onMouseOut="this.bgColor = '#FFFFFF';">
                    <td><a href="
                           addEvent?calendar_time=10">6pm</a></td>
                    <td>
                        <%=request.getAttribute("title10")%>
                    </td>
                    <td><a href="deleteEvent?calendar_time=10">delete</a></td>
                </tr>

            </tbody>
        </table>
        <h4><a href="index.jsp">Exit</a></h4>
    </body>
</html>
