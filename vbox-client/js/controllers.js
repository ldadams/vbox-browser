var vboxApp = angular.module('vboxApp', []);
var SERVER_URI = "http://localhost:8080/vbox/vm";

vboxApp.controller('HostController', function($scope, $http) {
    $scope.hosts = new Array();
    $scope.selectedHost = "";
    $scope.selectedMachine = "";

    $http({
        method: 'GET',
        url: SERVER_URI
    }).success(function(data) {
        $scope.hosts = data;
        $scope.selectedHost = data.length > 0 ? data[0] : "";
    });

    $('#modalCreds').on('hidden.bs.modal', function(e) {
        if ($scope.selectedMachine && $scope.creds.cancel === false) {
            var data = new Object();
            data.host = $scope.selectedHost.serviceUri;
            data.machineId = $scope.selectedMachine.id;
            data.username = $scope.creds.username;
            data.password = $scope.creds.password;

            if ($scope.selectedMachine.status.toUpperCase() === 'RUNNING') {
                $http.post(SERVER_URI + "/savestate", JSON.stringify(data))
                        .success(function(r) {
                            _.each($scope.hosts, function(host) {
                                _.each(host.virtualMachines, function(vm) {
                                    if (vm.id === r.id) {
                                        vm.status = r.status;
                                    }
                                });
                            });
                        });
            } else {
                $http.post(SERVER_URI + "/start", JSON.stringify(data))
                        .success(function(r) {
                            _.each($scope.hosts, function(host) {
                                _.each(host.virtualMachines, function(vm) {
                                    if (vm.id === r.id) {
                                        vm.status = r.status;
                                    }
                                });
                            });
                        });
            }
            data = new Object();
        }
    });
    
    function stop() {
        
    }
});

vboxApp.filter('host', function() {
    return function(hosts, scope) {
        if (!scope.search)
            return hosts;

        var f = scope.search.name;
        return _.filter(hosts, function(host) {
            return !f || _.find(host.virtualMachines, function(vm) {
                return vm.name.indexOf(f) > -1;
            });
        })
    };
});

vboxApp.filter('status', function() {
    return function(status) {
        return status.toUpperCase() === 'RUNNING' ? 'off' : 'play';
    };
});

vboxApp.filter('bytes', function() {
    return function(bytes) {
        if (isNaN(parseFloat(bytes)) || !isFinite(bytes))
            return '';
        
        return (bytes / 1024).toFixed(0) + ' ' + 'GB';
    }
});