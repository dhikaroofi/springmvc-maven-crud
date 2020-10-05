<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Data Customer - ${title}</title>
    <style>
    	td{
    		
    		padding:2px 1px;
    	}
    </style>
  </head>
  <body onload="window.print()">
 	<center>
  				<table border="1" cellspacing="0" cellpadding="0" width="80%" style="margin: 0 auto;" >
					  <thead class="thead-dark">
					    <tr>
					      <td scope="col" width="5%">#</td>
					      <td scope="col" width="20%">Nama</td>
					      <td scope="col" width="30%">Alamat</td>
					      <td scope="col" width="15%">Pendapatan</td>
				          <td scope="col" width="10%">Kota</td>
				      	
					    </tr>
					  </thead>
					  <tbody>
					
						<c:forEach var="customer" items="${listCustomer}" varStatus="status">
						
			        	<tr>
			        		<td>${status.index + 1}</td>
							<td>${customer.nama}</td>
							<td>${customer.alamat}</td>
							<td>${customer.pendapatan}</td>
							<td>${customer.nama_kota}</td>
													
			        	</tr>
			        	
						</c:forEach>	
					  </tbody>
					</table>
					
			</center>		

  </body>
</html>