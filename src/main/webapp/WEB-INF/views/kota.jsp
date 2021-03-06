<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/springmvc" var="url" htmlEscape="true"/>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">Collega</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav ml-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="/springmvc/">Home <span class="sr-only">(current)</span></a>
	      </li>
	   
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Kelola Data
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	      	          <a class="dropdown-item" href="/springmvc/customer/">Customer</a>
	          <a class="dropdown-item" href="/springmvc/kota/">Kota</a>
	      
	      </li>
	  
	    </ul>
	    
	  </div>
	</nav>
	
  	<div class="container mt-5">
  		<div class="row">
  		
	  
	  		
			<c:if test="${error != null}">
				<div class="col-md-12  alert alert-warning alert-dismissible fade show" role="alert">
				  <strong>Alert</strong> 	${error}
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
			</c:if>
			
			<c:if test="${success != null}">
				<div class=" col-md-12 alert alert-success alert-dismissible fade show" role="alert">
				  <strong>Alert</strong> 	${success}
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
			</c:if>
  			
  		</div>
  		<div class="row">
  			<div class="col-md-12">
  				<!-- Button trigger modal -->
		<!-- Button trigger modal -->
				<button type="button" class="btn btn-info float-right" data-toggle="modal" data-target="#exampleModal">
				 Buat Data Kota
				</button>
  			
  			</div>
  		</div>
  		<div class="row mt-5">
  			<div class="col-md-12">
  				<table class="table table-bordered">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col" width="5%">#</th>
					      <th scope="col" >Nama</th>

				      	  <th scope="col" width="20%" class="text-center"> - </th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="kota" items="${kotaList}" varStatus="indexKota">
				        	<tr>
				        		<td>${indexKota.index + 1}</td>
								<td>${kota.nama}</td>
							
								<td class="text-center">
									<button class="btn btn-warning text-white btnUpdate" data-toggle="modal" data-target="#exampleModalUpdate" data-form="${kota.kota_id}&${kota.nama}">Rubah</button>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							
								
									<a class="btn btn-danger text-white" href="delete?id=${kota.kota_id}">Hapus</a>
								</td>
															
				        	</tr>
			        	</c:forEach>	
					  </tbody>
					</table>
					
					
					
  			</div>
  		</div>
  	</div>
 
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <div id="result"></div>
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        
      </div>
      <div class="modal-body">
      	<form:form action="create" method="post" modelAttribute="kotaModel" id="SaveForm">
      	  	 <div class="form-group">
			    <label for="exampleInputEmail1">Kota ID</label>
		   		<form:input path="kota_id" cssClass="form-control" maxlength="50" cssStyle="" />
			 </div>
	      	 <div class="form-group">
			    <label for="exampleInputEmail1">Nama Kota</label>
		   		<form:input path="nama" cssClass="form-control" maxlength="50" cssStyle="" />
			 </div>
			
			
			  <button type="submit" id="SubmitSave" class="btn btn-primary float-right" value="Save">Save</button>
			
							
	      	
      	</form:form>
      </div>
    
    </div>
  </div>
</div>
   
   <!-- Modal -->
<div class="modal fade" id="exampleModalUpdate" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <div id="result"></div>
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        
      </div>
      <div class="modal-body">
      	<form:form action="update" method="post" modelAttribute="kotaModel" id="SaveUpdate">
	      	 <div class="form-group">
			    <label for="exampleInputEmail1">Nama Kota</label>
		   		<form:input path="nama" id="nama" cssClass="form-control" maxlength="50" cssStyle="" />
			 </div>
			 
				<form:hidden path="kota_id" id="id"/>
			  <button type="submit" id="SubmitSave" class="btn btn-primary float-right" value="Save">Save</button>
			
							
	      	
      	</form:form>
      </div>
    
    </div>
  </div>
</div>
   
   
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
 	<script>
 	$(document).ready(function(){
 		  $(".btnUpdate").click(function(){
 		   var data = $(this).attr("data-form");
 			
 		  	var items = data.split('&');
 		
 		  	 var form = $("#SaveUpdate");
 		  	 	form.find("#id").val(items[0]);
		  		form.find("#nama").val(items[1]);
		  		
 		  });
 		
 		});
 	</script>
 
  </body>
</html>