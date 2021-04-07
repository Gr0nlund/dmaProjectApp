<?php
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 

    if (mysqli_connect_errno()) {
        die('MySQL connection failed: ' . mysqli_connect_error());
    }
  
    $stmt = $conn->prepare('SELECT * FROM DMA_Dumpsters NATURAL JOIN DMA_Dumpsters_Types');
    $stmt->execute();
    $stmt->bind_result($Dumpster_ID, $Dumpster_Longtitude, $Dumpster_Latitude, $Dumpster_Type, $Dumpster_Full);
    $Dumpsters = array();
    
    while ($stmt->fetch()){
        $temp = array();
        $temp['Dumpster_ID'] = $Dumpster_ID;
        $temp['Dumpster_Longtitude'] = $Dumpster_Longtitude;
        $temp['Dumpster_Latitude'] = $Dumpster_Latitude;
        $temp['Dumpster_Type'] = $Dumpster_Type;
        $temp['Dumpster_Full'] = $Dumpster_Full;

        array_push($Dumpsters, $temp);
    }
    echo json_encode($Dumpsters);
?>