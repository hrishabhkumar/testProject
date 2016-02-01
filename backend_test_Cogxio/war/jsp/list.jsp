<%@page import="com.cogxio.services.Services"%>
<%@page import="com.cogxio.database.LocationJDO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	List<LocationJDO> locationJDOs = new Services().getLocationsList();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/bootstrap/css/bootstrap.css" rel='stylesheet' type='text/css'>
<title>Insert title here</title>
<style type="text/css">
	.body-container{
		padding-top: 60px;
	}
</style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search" id="search-form">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search" id="search-text">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid body-container">
	<h1>Total Count : <span id="count"></span></h1>
	<table class="table" id="location-table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Category</th>
				<th>Longitude</th>
				<th>Latitude</th>
				<th>Rating</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</div>

	<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			renderList();
			renderCount();
			function renderList()
			{
				$.ajax({
					url 		: "/api/list",
					dataType	: "json",
					success		: function(data){
						var htmlStr = '';
						for(var i in data)
						{
							var location = data[i];
							htmlStr += '<tr><td>'+location.id+'</td>';
							htmlStr 	+= '<td>'+location.name+'</td>';
							htmlStr 	+= '<td>'+location.category+'</td>';
							htmlStr 	+= '<td>'+location.longitude+'</td>';
							htmlStr 	+= '<td>'+location.latitude+'</td>';
							htmlStr 	+= '<td>'+location.rating+'</td></tr>';
							
						}
						$('#location-table> tbody').html(htmlStr);
					}
				});
			}
				
			function renderCount()
			{
				$.ajax({
					url 		: "/api/count",
					success		: function(data){
						$('#count').html(data);
					}
				});
			}
			$('#search-form').submit(function(e){
				e.preventDefault();
				var queryData = $('#search-text').val().trim();
				if(queryData)
				{
					$.ajax({
						url 		: "/api/search?q="+queryData,
						dataType	: "json",
						success		: function(data){
							var htmlStr = '';
							for(var i in data)
							{
								var location = data[i];
								htmlStr += '<tr><td>'+location.id+'</td>';
								htmlStr 	+= '<td>'+location.name+'</td>';
								htmlStr 	+= '<td>'+location.category+'</td>';
								htmlStr 	+= '<td>'+location.longitude+'</td>';
								htmlStr 	+= '<td>'+location.latitude+'</td>';
								htmlStr 	+= '<td>'+location.rating+'</td></tr>';
								
							}
							$('#location-table> tbody').html(htmlStr);
						}
					});
				}
				else
				{
					renderList();
					renderCount();
				}
				
			})
		});
	</script>
</body>
</html>