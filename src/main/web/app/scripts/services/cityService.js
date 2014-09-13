'use strict';

angular.module('BootstrapApplication.services')
    .factory('LoanService', ['$http', function($http) {
        var loanService = {};
        loanService.findCity = function (lat, lon, successFn) {
            $http({
                url: '/loanApplication/'+lat+'/'+lon,
                dataType: 'json',
                method: 'PUT    ',
                data: '',
                headers: {
                    'Content-Type': 'application/vnd.pl.warsjawa.ui.v1+json'
                }
            }).success(function (data) {
                successFn(data);
            });
        };

        return loanService;
    }
]);
