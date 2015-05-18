<%-- 
    Document   : index
    Created on : Nov 12, 2013, 10:09:00 PM
    Author     : Jing Lin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="newcss.css" type="text/css">
        <title>Submit Page</title>
    </head>
    <body>
        <h1>Please Enter the following information: </h1>
        <form method ="post" action="Submit1">
            <table border="1">
                <tbody>
                    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='orange';" onMouseOut="this.bgColor='#FFFFFF';">
                        <td>Name:</td>
                        <td><input type="text" name="name" value="user" size="50" /></td>
                    </tr>
                    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='orange';" onMouseOut="this.bgColor='#FFFFFF';">
                        <td>E-mail:</td>
                        <td><input type="text" name="email" value="user@email.com" size="50" /></td>
                    </tr>

                    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='orange';" onMouseOut="this.bgColor='#FFFFFF';">
                        <td></td>
                        <td><input type="submit" value="Submit" name="Submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
