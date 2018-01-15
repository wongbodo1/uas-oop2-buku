bukuApp.controller('TambahController', 
        function($scope, $http, $window) {
    
    $scope.simpan = function() {
        $http.post("/api/tambah", $scope.buku)
            .then(sukses, gagal);

        function sukses(response) {
            $window.location.href = "/";    
        };

        function gagal(response) {
            console.log(response);
        };
    };

    $scope.batal = function() {
        $window.location.href = "/";
    }

});