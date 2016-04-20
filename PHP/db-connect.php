<?php

	define('HOST','localhost');
	define('USER','cs370gro');
	define('PASS','password1');
	define('DB','cs370gro_accounts');

	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
	?>