<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/product.css" />
    <title>Product</title>
    
    <style>
    * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.top {
  height: 40vh;
  width: 100%;
  background-color: #eeeeee;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
}
.top h1 {
  font-size: 70px;
  color: #333333;
  font-weight: medium;
  margin-bottom: 20px;
}
.top p {
  font-size: 20px;
  color: #333333;
}
.bottom {
  height: 60vh;
  display: flex;
  justify-content: flex-start;
  width: 100%;
  max-width: 100%;
  align-items: center;
}
.box {
  border: 1px solid #333333;
  padding: 30px;
  /* flex: 0.3; */
  width: 30%;
  height: 65%;
  margin: 100px;
}
.box h2 {
  font-size: 30px;
  color: #333333;
  font-weight: normal;
  margin-bottom: 15px;
}
.box p {
  font-size: 19px;
  font-weight: normal;
  color: #333333;
  margin-bottom: 12px;
}
.btn {
  background-color: #428bca;
  color: white;
  font-size: 15px;
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 13px 21px;
  border-radius: 12px;
  cursor: pointer;
}
.box i {
  color: white;
  margin-right: 10px;
}
    
    </style>
    
    
</head>
<body>
    <div class="top">
        <h1>
            Products
        </h1>
        <p>All the available products in our store</p>
    </div>
    <c:forEach items="${products}" var="product">
    <div class="bottom">
        <div class="box">
            <h2>${product.name}</h2>
            <p>${product.description}</p>
            <p class="price">${product.unitPrice} USD</p>
            <p>Available ${product.unitsInStock}</p>
            <button class="btn">
            <i class="fas fa-info"></i> Details
            </button>
        </div>
    </div>
    </c:forEach>
    
</body>
</html>