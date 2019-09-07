<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <!--  <link rel="stylesheet" href="makercheckerlogin.css"> -->
  
  <%-- <link href="<c:url value="/View/makercheckerlogin.css" />" rel="stylesheet"/> --%>
<title> Update Customer Details</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="r" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="p" %>
<p:url value="resources/makercheckerlogin.css" var="k"/>
<link href="${k}" rel="stylesheet"/>
<%@include file="makerfunctions.jsp" %>

</head>
<body>

<div class="d-md-flex h-md-100 align-items-center">
<div class="col-md-6 p-0 bg-indigo h-md-100">
    <div class="text-white d-md-flex align-items-center h-100 p-5 text-center justify-content-center">
        <div class="logoarea pt-5 pb-5">
<center><h5 style="color:red">${flag}</h5></center>

 <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
 <div id="formFooter"  style="background-color:#e3f2fd; color:#5fbae9">
      <h1> Update Customer Details</h1>
    </div>
 
      ${date}
     
                
                <s:form action="updateCustomerDetails" modelAttribute="customer">
                
              <%--  <p:message code="label.customer.customerCode" var="code"> </p:message> --%> <s:input path="customerCode"/>
               <%--  <p:message code="label.customer.customerName" var="name"> </p:message> --%> <s:input path="customerName"/>
                 <%-- <p:message code="label.customer.address1" var="addrs1"> </p:message>  --%><s:input path="address1" />
                 <%--  <p:message code="label.customer.address2" var="addrs2" > </p:message>  --%><s:input path="address2" />
                  <%--  <p:message code="label.customer.pinCode" var="pin"> </p:message>  --%><s:input path="pinCode"/>
                   <%--  <p:message code="label.customer.email" var="email"> </p:message>  --%><s:input path="email"  />
                    <%--  <p:message code="label.customer.contactNo" var="no"> </p:message> --%> <s:input path="contactNo" />
                      <%-- <p:message code="label.customer.contactPerson" var="person"> </p:message> --%> <s:input path="contactPerson" />
                       <r:set var="createdate" value="${date}" />
                      <c:formatDate value="${createdate}" var="date1" pattern="dd/MM/yyyy" />
               <s:hidden path="createDate" value="${date1}" /> 
               <s:hidden path="createdBy" value="${by}" />
                <br>
                <br>
                 <!-- <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" name="flag">Active_Inactive_Flag
    <span class="caret"></span></button>
    <ul class="dropdown-menu" >
     
      <li data-value="A"><a href="#">Active</a></li>
      <li data-value ="I"><a href="#">Inactive</a></li>
     
      
    </ul>
  </div> -->
  
  <div class="dropdown">
  
  <s:select path="inactiveFlag" class="btn btn-default dropdown-toggle" data-toggle="dropdown" >
  
  <s:option value="A">Active</s:option>
  <s:option value="I">Inactive</s:option>
  
  </s:select>
  </div>
        <br>
      <br>
      
      
      <input type="submit" class="fadeIn fourth" name ="button" value="Update" >
   </s:form>
  

    <!-- Remind Passowrd -->
   
  </div>
</div> 
</div>
    </div>
</div>


<div class="col-md-6 p-0 bg-white h-md-100 loginarea">
    <div class="d-md-flex align-items-center h-md-100 p-5 justify-content-center">
        <br>
       <iframe width="550px" height="840px" src="displayall"></iframe> 
    </div>
</div>
    
</div>

</body>
</html>