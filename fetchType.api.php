<?php
    $dbServername = 'web1.netgiganten.dk';
    $dbUsername = 'damibfko_josj';
    $dbPassword = 'Glubben13!';
    $dbName = 'damibfko_DMA';
  
    // Create connection
    $conn = new mysqli($dbServername, $dbUsername, $dbPassword, $dbName); 

    $DumpsterID = $_GET['DumpsterID'];
    $UserID = $_GET['UserID'];
    $RequestURL = "https://solskov-jensen.dk/API/fetchType.api.php";
    $Date = date("d-m-Y H:i:s");
    $Response = 102;

    //https://solskov-jensen.dk/API/fetchType.php?UserID=1234&DumpsterID=1
    if(isset($UserID) && isset($DumpsterID)){
        if (mysqli_connect_errno()) {
            die('MySQL connection failed: ' . mysqli_connect_error());
        }

        $request = $conn->prepare('INSERT INTO DMA_Request (UserID, RequestURL,RequestTime, RequestResponse) VALUES (?, ?, ?, ?)');
        $request->bind_param('ssss', $UserID, $RequestURL, $Date, $Response);
        $request->execute();
        $request->close();
    
        $stmt = $conn->prepare('SELECT * FROM DMA_Dumpsters_Types WHERE Dumpster_ID = ?;');
        $stmt->bind_param('s', $DumpsterID);
        $stmt->execute();
        $stmt->bind_result($Dumpster_Type, $Dumpster_ID, $Dumpster_Full);
        $Dumpsters = array();
        
        while ($stmt->fetch()){
            $temp = array();
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