<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <c:import url="../template/boot.jsp"></c:import>
  <c:import url="../template/header.jsp"></c:import>
  
<div class="container">
  <h3>Right Aligned Navbar</h3>
  <p>The .navbar-right class is used to right-align navigation bar buttons.</p>
</div>

<!-- Nav -->	
<div class="container">
  <div class="jumbotron">
    <h1>Member Login</h1>      
    <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing responsive, mobile-first projects on the web.</p>
  </div>
     
</div>

	<div class="container">
		<div class="row">
			<form class="form-horizontal" action="./memberLogin" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="id">ID:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="id" placeholder="Enter ID" name="id">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="pw">Password:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pw" placeholder="Enter Password" name="pw">
					</div>
				</div>
				
						
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label><input type="checkbox" name="remember">Remember me</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Submit</button>
					</div>
				</div>
			</form>

		</div>
	</div>

