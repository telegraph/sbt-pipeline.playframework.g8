Feature: API Entry points

#  Example:
#  Scenario: Testing authentication of valid data in apple-news-auth
#    Given the service
#    When I invoke form POST '/apple-news-auth/authenticate' with payload 'emailId=srilu.pola%40telegraph.co.uk&password=telegraph123'
#    Then I should get a status code 200
#    And I should get a 'application/json' Content-Type

#  Scenario: Testing validation of valid token for apple-news-auth
#    Given the service
#    When I invoke GET '/apple-news-auth/validate?access_token=eyJjdHkiOiJhcHBsaWNhdGlvblwvanNvbiIsImFsZyI6Im5vbmUifQ.eyJ0b2tlbklkIjoiYmU3NTU5ZTItNjFkYi00N2I1LWFkOTMtYTdlYjU4NTg3YjQyIiwicHJvZmlsZVB1YmxpY0lkIjoiMmRhMDM4N2ItMmY1Zi00Y2U2LWEyOGMtZDZmMjE5MmUwMWE3IiwiY2xpZW50SWQiOiJ0Y3VrIiwiaWF0IjoiMjAxNy0wMy0zMFQxMDoxMzowMS44MjZaIn0.'
#    Then I should get a status code 200
#    And I should get a 'application/json' Content-Type
#    And the payload should match
#"""
#{
#  "status": "not_subscribed"
#}
#"""
#
#  Scenario: Testing validation of invalid token for apple-news-auth
#    Given the service
#    When I invoke GET '/apple-news-auth/validate?access_token=invalid'
#    Then I should get a status code 401
#    And I should get a 'text/plain; charset=utf-8' Content-Type
