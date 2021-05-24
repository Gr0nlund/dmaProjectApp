<?php
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 
    
    $Longitude = $_POST['longitude'];
    $Latitude = $_POST['latitude'];

    if (mysqli_connect_errno()) {
        die('MySQL connection failed: ' . mysqli_connect_error());
    }

    if(isset($Longitude) && isset($Latitude)){
        $request = $conn->prepare('INSERT INTO DMA_Dumpsters (Dumpster_Longtitude, Dumpster_Latitude) VALUES (?, ?)');
        $request->bind_param('ss', $Longitude, $Latitude);
        $request->execute();
        $request->close();
    }

    $conn->close();
    header("Location: ../index.php");
?>