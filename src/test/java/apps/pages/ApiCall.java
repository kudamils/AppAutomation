package apps.pages;

import apps.data.APIData;
import apps.model.UserProfile;
import id.aldochristiaan.salad.util.LogUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static apps.MitraFactory.dotenv;

public class ApiCall {

    private Response response;
    private RequestSpecification requestSpecification;

    public ApiCall() {
        APIData.setApiUrl(dotenv.get("API_URL"));
        APIData.setAccountsUrl(dotenv.get("ACCOUNTS_URL"));
        APIData.setAppVersion(dotenv.get("API_APP_VERSION"));
        APIData.setUserAgent(dotenv.get("API_USER_AGENT"));
        APIData.setIdentity(dotenv.get("API_IDENTITY"));
    }

    private void setHeaders() {
        requestSpecification = RestAssured.given()
                .baseUri(APIData.getApiUrl())
                .contentType(APIData.getContentType())
                .header("Authorization", APIData.getAuthToken())
                .header("Bukalapak-App-Version", APIData.getAppVersion())
                .header("User-Agent", APIData.getUserAgent())
                .header("If-None-Match", "")
                .header("Identity", APIData.getIdentity())
                .header("Bukalapak-Identity", "identity-api-testing");
        APIData.setRequestSpecification(requestSpecification);
    }

    private void sendRequest(String method, String endpoint) {
        setHeaders();
        switch (method.toLowerCase()) {
            default:
            case "get":
                response = APIData.getRequestSpecification().get(endpoint);
                break;
            case "post":
                response = APIData.getRequestSpecification().post(endpoint);
                break;
            case "put":
                response = APIData.getRequestSpecification().put(endpoint);
                break;
            case "delete":
                response = APIData.getRequestSpecification().delete(endpoint);
                break;
            case "patch":
                response = APIData.getRequestSpecification().patch(endpoint);
                break;
        }

        APIData.setResponse(response);
    }

    private void sendRequestWithBody(String method, String endpoint, Object body) {
        setHeaders();
        switch (method.toLowerCase()) {
            default:
            case "get":
                response = APIData.getRequestSpecification().body(body).get(endpoint);
                break;
            case "post":
                response = APIData.getRequestSpecification().body(body).post(endpoint);
                break;
            case "put":
                response = APIData.getRequestSpecification().body(body).put(endpoint);
                break;
            case "delete":
                response = APIData.getRequestSpecification().body(body).delete(endpoint);
                break;
            case "patch":
                response = APIData.getRequestSpecification().body(body).patch(endpoint);
                break;
        }

        APIData.setResponse(response);
    }

    private Response getResponse() {
        return APIData.getResponse();
    }

    private ArrayList<String> getArrayResponsePath(String path) {
        return APIData.getResponse().path(path);
    }

    private String getResponsePath(String path) {
        return APIData.getResponse().path(path).toString();
    }

    private int getResponsePathSize(String path) {
        return APIData.getResponse().path(path + ".size()");
    }

    private HashMap getMapResponsePath(String path) {
        return APIData.getResponse().path(path);
    }

    public void setUserBearerToken(String userType) {
        userType = userType.replaceAll(" ", "_").toUpperCase();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("client_id", dotenv.get("API_CLIENT_ID"));
        jsonAsMap.put("client_secret", dotenv.get("API_CLIENT_SECRET"));
        jsonAsMap.put("grant_type", "password");
        jsonAsMap.put("scope", "public user store");
        jsonAsMap.put("username", dotenv.get(userType + "_USERNAME"));
        jsonAsMap.put("password", dotenv.get(userType + "_PASSWORD"));

        /*Identity berubah jika user mengaktifkan tfa*/
        if (jsonAsMap.get("username").equals("zuraqia2")) {
            requestSpecification = RestAssured.given()
                    .baseUri(APIData.getAccountsUrl())
                    .contentType(APIData.getContentType())
                    .body(jsonAsMap)
                    .header("Bukalapak-App-Version", APIData.getAppVersion())
                    .header("User-Agent", APIData.getUserAgent())
                    .header("Identity", APIData.getIdentity())
                    .header("Bukalapak-identity", dotenv.get("IDENTITY"));
        } else {
            requestSpecification = RestAssured.given()
                    .baseUri(APIData.getAccountsUrl())
                    .contentType(APIData.getContentType())
                    .body(jsonAsMap)
                    .header("Bukalapak-App-Version", APIData.getAppVersion())
                    .header("User-Agent", APIData.getUserAgent())
                    .header("Identity", APIData.getIdentity())
                    .header("Bukalapak-identity", "identity-api-testing");
        }
        response = requestSpecification.post("/oauth/token")
                .then()
                .statusCode(200).extract().response();


        String bearerToken = "Bearer " + response.path("access_token");
        LogUtil.info("Authorization API v4 : " + bearerToken);
        APIData.setAuthToken(bearerToken);
    }

    public void getMitraAddress() {
        sendRequest("get", "/general-trade/addresses");
        HashMap<String, Object> response = getResponse().path("data");
        UserProfile.setId(Integer.parseInt(response.get("id").toString()));
        UserProfile.setAddress(response.get("address").toString());
        UserProfile.setCity(response.get("city").toString());
        UserProfile.setPost_code(response.get("post_code").toString());
        UserProfile.setDistrict(response.get("district").toString());
        UserProfile.setArea(response.get("area").toString());
        UserProfile.setProvince(response.get("province").toString());
    }

    public void revertMitraAddress() {
        Map<String, Object> address = new HashMap<>();
        address.put("address", UserProfile.getAddress());
        address.put("city", UserProfile.getCity());
        address.put("province", UserProfile.getProvince());
        address.put("area", UserProfile.getArea());
        address.put("post_code", UserProfile.getPost_code());
        address.put("district", UserProfile.getDistrict());
        sendRequestWithBody("patch", "/general-trade/addresses/" + UserProfile.getId(), address);
    }

    public void removeAllMitraInventories() {
        sendRequest("get", "/_exclusive/cashiers/inventories");
        ArrayList idItems = getArrayResponsePath("data.id");
        if (idItems.size() > 0) {
            for (Object idItem : idItems) {
                sendRequest("delete", "/_exclusive/cashiers/inventories/" + idItem.toString());
                response.then().assertThat().statusCode(200);
                LogUtil.info("Inventory item with id : " + idItem.toString() + " has been deleted!");
            }
        } else {
            LogUtil.info("There is no inventory at this moment!");
        }
    }

    public void removeMitraCartItems() {
        sendRequest("get", "/general-trade/cart-items");
        ArrayList idItems = getArrayResponsePath("data.id");
        if (idItems.size() > 0) {
            for (Object idItem : idItems) {
                sendRequest("delete", "/general-trade/cart-items/" + idItem.toString());
                response.then().assertThat().statusCode(200);
                LogUtil.info("Cart item with id : " + idItem.toString() + " has been deleted!");
            }
        } else {
            LogUtil.info("There is no item on cart at this moment!");
        }
    }

    public void removeMitraSerbuSeruItems() {
        sendRequest("get", "/mitra-lucky-deals/cart-items");
        ArrayList idItems = getArrayResponsePath("data.id");
        if (idItems.size() > 0) {
            for (Object idItem : idItems) {
                sendRequest("delete", "/mitra-lucky-deals/cart-items/" + idItem.toString());
                response.then().assertThat().statusCode(202);
                LogUtil.info("Serbu seru item with id : " + idItem.toString() + " has been deleted!");
            }
        } else {
            LogUtil.info("There is no serbu seru on cart at this moment!");
        }
    }

    public void removeAllMitraCartItems() {
        sendRequest("get", "/_exclusive/cashiers/carts?id=742652");
        LogUtil.info(response.body().prettyPrint());
        sendRequest("delete", "/_exclusive/cashiers/carts");
        response.then().assertThat().statusCode(201);
    }

    public void addToCart() {
        Object payload = "{\n" +
                "  \"warehouse_product_id\": 83783,\n" +
                "  \"quantity\": 5\n" +
                "}";

        sendRequestWithBody("post", "/general-trade/cart-items/", payload);
    }

    public void cancelTopUp() {
        sendRequest("get", "/topups");
        ArrayList idTopUps = getArrayResponsePath("data.id");
        Object payload = "{\n" +
                "  \"state\": \"cancelled\"" +
                "}";
        LogUtil.info("Cancellation top up with id : " + idTopUps.get(0));
        sendRequestWithBody("patch", "/_exclusive/agents/topups/" + idTopUps.get(0) + "/status", payload);
        LogUtil.info("Top up with id : " + idTopUps.get(0) + " has been cancelled!");
    }
}
