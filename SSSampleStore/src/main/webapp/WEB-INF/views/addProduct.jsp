<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add new product</title>
</head>
<body>


<div>
<form:form method="POST" modelAttribute="newProduct"> 


<div>
<label>Product ID:</label>
<form:input path="productId" type="text" id="productId"/>
</div>

<div>
<label>Name:</label>
<form:input path="name" type="text" id="name"/>
</div>


<div>
<label>Description:</label>
<form:textarea path="description" type="text" id="description" row="2"/>
</div>



<div>
<label>Manufacturer:</label>
<form:input path="manufacturer" type="text" id="manufacturer"/>
</div>


<div>
<label>Category:</label>
<form:input path="category" type="text" id="category"/>
</div>




<div>
<label>Unit in Stock:</label>
<form:input path="unitsInStock" type="text" id="unitsInStock"/>
</div>


<div>
<label>Unit in Order:</label>
<form:textarea path="unitsInOrder" type="text" id="unitsInOrder"/>
</div>


<div>
<label>Discontinued:</label>
<form:checkbox path="discontinued" id="discontinued"/>
</div>

<div>
<label>Condition:</label>
<form:radiobutton path="condition" value="New" id="condition"/>New
<form:radiobutton path="condition" value="Old" id="condition"/>Old
<form:radiobutton path="condition" value="Refurbished" id="condition"/>Refurbished
</div>


<div>
<input type="submit" id="btnAdd" value="Add"/>
</div>


</form:form>
</div>
</body>
</html>