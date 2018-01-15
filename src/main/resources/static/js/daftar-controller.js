bukuApp.controller('DaftarController',
        function($scope, $http, $window) {

    $scope.daftarBuku = [];
    
    $scope.updateDaftar = function() {
        $http.get('/daftar-buku').then(sukses, gagal);

        function sukses(response) {
            console.log(response.data);
            $scope.daftarBuku = response.data;
        };

        function gagal(response) {};
    };

    $scope.tambah = function() {
        $window.location.href = "/tambah-ui";
    }

    $scope.edit = function(buku) {
        //console.log(buku.id);
        //console.log(buku.nama);
        //console.log(buku.judul_buku);
        $window.location.href = "edit-ui?id=" + buku.id +
                "&nama=" + buku.nama + 
                "&judul=" + buku.judul_buku;
    }

    $scope.hapus = function(buku) {
        $http.delete('/api/hapus/' + buku.id).then(sukses, gagal);

        function sukses(response) {
            $scope.updateDaftar();        
        }

        function gagal(response) {}
    }

    $scope.updateDaftar();

});