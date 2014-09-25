'use strict';

angular.module('BootstrapApplication.services')
    .factory('LoanService', ['$http', function($http) {
        var loanService = {};
        loanService.applyForLoan = function (formData, successFn) {
            $http({
                url: '/api/loanApplication',
                dataType: 'json',
                method: 'POST',
                data: '{"job":"'+formData.job+'","firstName":"'+formData.firstName+'","lastName":"'+formData.lastName+'","amount":"'+formData.amount+'"}',
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
