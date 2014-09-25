'use strict';

angular.module('BootstrapApplication.services')
    .factory('LoanService', ['$http', function($http) {
        var loanService = {};
        loanService.applyForLoan = function (formData, successFn) {
            $http({
                url: '/api/loanApplication/123123123',
                dataType: 'json',
                method: 'PUT',
                data: '{"job":"'+formData.job+'","name":"'+formData.name+'","lastName":"'+formData.lastName+'","amount":"'+formData.amount+'"}',
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
