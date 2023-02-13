Feature: Festival API feature
  Verify frstival api business flows

  @Regression @ValidateEndPoint
  Scenario: Verify get festival valid endpoint
    Given Endpoint to get festivals
    Then User validates getFestival enpoint
      |/api/v1/festivals|

  @Regression @SchemaValidation
  Scenario: Verify get festival endpoint schema
    Given Endpoint to get festivals
    Then User validates the festival schema

  @Regression @ValidateSuccess
  Scenario: Verify get festival endpoint success response
    Given Endpoint to get festivals
    Then User triggers getfestival for success response

  @Regression @ValidateServerInHeader
  Scenario: Verify get festival endpoint response header
    Given Endpoint to get festivals
    Then User validates the server in response header

  @Regression @ValidateThrottled
  Scenario: Verify get festival Throttled response
    Given Endpoint to get festivals
    Then User triggers getfestival for Throttled response


#  @Regression @ValidateInvalidEndPoint
#  Scenario: Verify get festival invalid endpoint
#    Given Endpoint to get festivals
#    Then User triggers invalid endpoint and validates response




    #Verify get fest success response
  # verify get fest throttlked response
  # verify response headers
  # Neg - verify invalif request
  # validate response body
  # schema validation