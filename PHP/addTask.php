<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Getting values
		$name = $_POST['name'];
		$desg = $_POST['desg'];
		$sal = $_POST['salary'];

		//Creating an sql query
		$sql = "INSERT INTO accounts (First_Name,Last_Name,Email) VALUES ('$name','$desg','$sal')";

		//Importing our db connection script
		require_once('db-connect.php');

		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Employee Added Successfully';
		}else{
			echo 'Could Not Add Employee';
		}

		//Closing the database
		mysqli_close($con);
	}
	?>