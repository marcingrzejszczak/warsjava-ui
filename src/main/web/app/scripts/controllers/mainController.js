'use strict';

/**
 * @ngdoc function
 * # MainCtrl
 */
angular.module('BootstrapApplication.controllers')
        .controller('MainCtrl', ['$scope', 'LoanService', function ($scope, LoanService) {
            $scope.awesomeThings = [
                'HTML5 Boilerplate',
                'AngularJS',
                'Karma'
            ];
            $scope.alerts = [];

            $scope.city = '';

            $scope.applyForLoan = function() {
                LoanService.applyForLoan($scope.job, function(data) {
                    $scope.city = data;
                });
            };

            $scope.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
        }]);
