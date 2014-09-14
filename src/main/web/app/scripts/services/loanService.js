'use strict';

angular.module('BootstrapApplication.services')
    .factory('LoanService', ['$http', function($http) {
        var loanService = {};
        loanService.applyForLoan = function (job, successFn) {
            $http({
                url: '/api/loanApplication/123123123',
                dataType: 'json',
                method: 'PUT',
                data: '{"job":"'+job+'"}',
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
