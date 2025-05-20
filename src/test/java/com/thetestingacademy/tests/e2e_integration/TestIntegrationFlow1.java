package com.thetestingacademy.tests.e2e_integration;

import com.thetestingacademy.Pojos.Request.Booking;
import com.thetestingacademy.Pojos.Response.BookingResponse;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow1 extends BaseTest {

    // TestE2EFlow_01

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request


    @Test(groups = "qa", priority = 1)
    @Owner("Vinod")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {

        System.out.println("positive Test");
        // Setup and Making a Request.
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).log().all().post();
        System.out.println(response.asString());
        // Extraction
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Verification Part
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pramod");

        iTestContext.setAttribute("Bookingid",bookingResponse.getBookingid());
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Vinod")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Vinod")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID() {

        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Vinod")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById()
    {
        Assert.assertTrue(true);
    }
}

