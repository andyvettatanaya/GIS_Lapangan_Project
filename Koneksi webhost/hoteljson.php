<?php
    $server = "localhost";
    $username = "id16992561_lapangan";
    $password = "d{f7nIIMruqA(RjV";
    $dbname = "id16992561_lapangangis";

    //Koneksi hotel
    $conn = new mysqli($server,$username,$password,$dbname);

    //check koneksi
    if($conn->connect_error){
        die("Koneksi Gagal / Failed").$conn->connect_error;
    }
    $sql = "SELECT * FROM t_hotel";
    $result = $conn->query($sql);
    $data = array();

    if($result->num_rows>0){
        while ($row=$result->fetch_assoc()){
            $data[]=$row;
        }
    }else{
        echo "Data tidak ditemukan karena masih kosong";
    }
    echo json_encode($data);
    $conn->close();
?>
