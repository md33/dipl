<?php

 	define('Host','localhost');
	define('User','root');
	define('Pass','');
	define('db','web');
	$con = mysqli_connect(Host,User,Pass,db) or die('Unable to Connect');
	
	?>