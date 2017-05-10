Feature: Admin Entry points
  In order to have a service
  As a developer in tmg
  I must provide some default entry points

  Scenario: Testing the default internal health entry point for /$name$/
    Given the service
    When I invoke GET '/$name$/health'
    Then I should get a status code 200

  Scenario: Testing the internal health entry point for /$name$/ with cached data
    Given the service
    When I invoke GET '/$name$/health?cached=true'
    Then I should get a status code 200
    And I should get a 'application/json' Content-Type

  Scenario: Testing the internal health entry point for /$name$/ with fresh data
    Given the service
    When I invoke GET '/$name$/health?cached=false'
    Then I should get a status code 200
    And I should get a 'application/json' Content-Type

  Scenario: Testing external health entry point for /$name$/
    Given the service
    When I invoke GET '/health'
    Then I should get a status code 200

  Scenario: Testing config entry point for /$name$/
    Given the service
    When I invoke GET '/$name$/admin/conf'
    Then I should get a status code 401

  Scenario: Testing there is access to swagger.json
    Given the service
    When I invoke GET '/$name$/swagger.json'
    Then I should get a status code 200
