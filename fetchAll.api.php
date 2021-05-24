<?php
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 

    $UserID = $_GET['UserID'];
    $RequestURL = "https://solskov-jensen.dk/API/fetchAll.api.php";
    $Date = date("d-m-Y H:i:s");
    $Response = 102;

    //https://solskov-jensen.dk/API/fetchAll.php?UserID=1234231

    if(isset($UserID)){
        if (mysqli_connect_errno()) {
            die('MySQL connection failed: ' . mysqli_connect_error());
        }

        $request = $conn->prepare('INSERT INTO DMA_Request (UserID, RequestURL,RequestTime, RequestResponse) VALUES (?, ?, ?, ?)');
        $request->bind_param('ssss', $UserID, $RequestURL, $Date, $Response);
        $request->execute();
        $request->close();
    
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
            $Response = 200;
        }
        echo json_encode($Dumpsters);

        $update = $conn->prepare('UPDATE DMA_Request SET RequestResponse = ? WHERE UserID = ? AND RequestTime = ?');
        $update->bind_param('sss',$Response, $UserID, $Date);
        $update->execute();
        $update->close();
    }

?>